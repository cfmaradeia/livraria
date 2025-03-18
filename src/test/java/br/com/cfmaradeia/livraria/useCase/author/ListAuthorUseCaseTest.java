package br.com.cfmaradeia.livraria.useCase.author;

import br.com.cfmaradeia.livraria.dto.AuthorResponseDTO;
import br.com.cfmaradeia.livraria.model.Author;
import br.com.cfmaradeia.livraria.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListAuthorUseCaseTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private AuthorRepository authorRepositoryMock;

    @Mock
    private AuthorMapper authorMapperMock;

    @InjectMocks
    private ListAuthorUseCase listAuthorUseCase;

    @Test
    void shouldReturnAllAuthors_Ok() {
        List<Author> authors = getAuthors();

        List<AuthorResponseDTO> authorResponseDTOS = List.of(
            new AuthorResponseDTO(1, "Teste 1"),
            new AuthorResponseDTO(2, "Teste 2")
        );

        when(authorRepositoryMock.findAll()).thenReturn(authors);
        when(authorMapperMock.responseDTOFromModel(authors.get(0))).thenReturn(new AuthorResponseDTO(1, "Teste 1"));
        when(authorMapperMock.responseDTOFromModel(authors.get(1))).thenReturn(new AuthorResponseDTO(2, "Teste 2"));

        Set<AuthorResponseDTO> allAuthors = listAuthorUseCase.findAll();
        assertFalse(allAuthors.isEmpty());
        assertEquals(2, allAuthors.size());
    }

    @Test
    void shouldReturnNoAuthors_Ok() {

        when(authorRepositoryMock.findAll()).thenReturn(List.of());

        Set<AuthorResponseDTO> allAuthors = listAuthorUseCase.findAll();
        assertTrue(allAuthors.isEmpty());
    }

    @Test
    void shouldReturnAllById() {
        var ids =  Set.of(1,2);
        List<Author> authors = getAuthors();
        when(authorRepositoryMock.findAllById(any())).thenReturn(authors);

        Set<Author> allAuthorsById = listAuthorUseCase.findAllById(ids);
        assertFalse(allAuthorsById.isEmpty());
        assertEquals(2, allAuthorsById.size());
    }

    private static List<Author> getAuthors() {
        return List.of(
            new Author(1, "Teste 1"),
            new Author(2, "Teste 2")
        );
    }
}