package br.com.cfmaradeia.livraria.useCase.book;

import br.com.cfmaradeia.livraria.dto.BookRequestDTO;
import br.com.cfmaradeia.livraria.dto.BookResponseDTO;
import br.com.cfmaradeia.livraria.model.Author;
import br.com.cfmaradeia.livraria.model.Subject;
import br.com.cfmaradeia.livraria.repository.BookRepository;
import br.com.cfmaradeia.livraria.useCase.author.ListAuthorUseCase;
import br.com.cfmaradeia.livraria.useCase.subject.ListSubjectUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddBookUseCaseTest {

    @Mock
    private BookRepository bookRepositoryMock;

    @Mock
    private BookMapper bookMapperMock;

    @Mock
    private ListAuthorUseCase listAuthorUseCaseMock;

    @Mock
    private ListSubjectUseCase listSubjectUseCaseMock;

    @InjectMocks
    private AddBookUseCase addBookUseCase;

    @Test
    void shouldCreateBookFromDTO_OK(){
        Set<Author> authors = Set.of(
            new Author(1, "Author 1")
        );
        Set<Subject> subjects = Set.of(
            new Subject(1, "Assunto 1")
        );
        BookRequestDTO bookRequestDTO = new BookRequestDTO("Titulo", "Editor", 1, "2000", Set.of(1), Set.of(1));
        BookResponseDTO bookResponseDTO = new BookResponseDTO(1, bookRequestDTO.title(), bookRequestDTO.editor(), bookRequestDTO.edition(), bookRequestDTO.publicationYear(), bookRequestDTO.authors(), bookRequestDTO.subjects());

        when(listAuthorUseCaseMock.findAllById(any())).thenReturn(authors);
        when(listSubjectUseCaseMock.findAllById(any())).thenReturn(subjects);
        when(bookMapperMock.responseDTOFromModel(any())).thenReturn(bookResponseDTO);

        BookResponseDTO book = addBookUseCase.createBookFromDTO(bookRequestDTO);
        assertNotNull(book);
        assertEquals(bookRequestDTO.title(), book.title());
        assertEquals(bookRequestDTO.editor(), book.editor());
        assertEquals(bookRequestDTO.edition(), book.edition());
        assertEquals(bookRequestDTO.publicationYear(), book.publicationYear());
        assertEquals(1, book.authors().size());
        assertEquals(1, book.subjects().size());
    }



}