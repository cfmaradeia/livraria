package br.com.cfmaradeia.livraria.useCase.book;

import br.com.cfmaradeia.livraria.dto.BookRequestDTO;
import br.com.cfmaradeia.livraria.dto.BookResponseDTO;
import br.com.cfmaradeia.livraria.exception.BookNotFoundException;
import br.com.cfmaradeia.livraria.model.Author;
import br.com.cfmaradeia.livraria.model.Book;
import br.com.cfmaradeia.livraria.model.Subject;
import br.com.cfmaradeia.livraria.repository.BookRepository;
import br.com.cfmaradeia.livraria.useCase.author.ListAuthorUseCase;
import br.com.cfmaradeia.livraria.useCase.subject.ListSubjectUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateBookUseCaseTest {

    @Mock
    private BookRepository bookRepositoryMock;

    @Mock
    private BookMapper bookMapperMock;

    @Mock
    private ListAuthorUseCase listAuthorUseCaseMock;

    @Mock
    private ListSubjectUseCase listSubjectUseCaseMock;

    @InjectMocks
    private UpdateBookUseCase updateBookUseCase;

    @Test
    void shouldUpdateBookFromDTO_OK() {
        var id = 1;
        Set<Author> authors = Set.of(
            new Author(1, "Author 1")
        );
        Set<Subject> subjects = Set.of(
            new Subject(1, "Assunto 1")
        );
        BookRequestDTO bookRequestDTO = new BookRequestDTO("Titulo", "Editor", 1, "2000", Set.of(1), Set.of(1));
        BookResponseDTO bookResponseDTO = new BookResponseDTO(id, bookRequestDTO.title(), bookRequestDTO.editor(), bookRequestDTO.edition(), bookRequestDTO.publicationYear(), bookRequestDTO.authors(), bookRequestDTO.subjects());

        Optional<Book> book = Optional.of(new Book());
        when(bookRepositoryMock.findById(any())).thenReturn(book);
        when(listAuthorUseCaseMock.findAllById(any())).thenReturn(authors);
        when(listSubjectUseCaseMock.findAllById(any())).thenReturn(subjects);
        when(bookMapperMock.responseDTOFromModel(any())).thenReturn(bookResponseDTO);

        BookResponseDTO updateBookFromDTO = updateBookUseCase.updateBookFromDTO(bookRequestDTO, id);
        assertNotNull(updateBookFromDTO);
    }

    @Test
    void shouldThrowExceptionBookNotFound_Exception() {
        var id = 1;
        BookRequestDTO bookRequestDTO = new BookRequestDTO("Titulo", "Editor", 1, "2000", Set.of(1), Set.of(1));

        assertThrows(BookNotFoundException.class, () -> updateBookUseCase.updateBookFromDTO(bookRequestDTO, id));

    }
}