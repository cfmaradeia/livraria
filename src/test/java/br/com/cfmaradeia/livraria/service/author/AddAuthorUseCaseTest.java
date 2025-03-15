package br.com.cfmaradeia.livraria.service.author;

import br.com.cfmaradeia.livraria.dto.AuthorRequestDTO;
import br.com.cfmaradeia.livraria.dto.AuthorResponseDTO;
import br.com.cfmaradeia.livraria.model.Author;
import br.com.cfmaradeia.livraria.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddAuthorUseCaseTest {

    @Mock
    private AuthorRepository authorRepositoryMock;

    @Mock
    private AuthorMapper authorMapperMock;

    @InjectMocks
    private AddAuthorUseCase addAuthorUseCase;

    @Test
    void shouldReturnAuthorDTOSuccessfully() {
        AuthorRequestDTO authorRequestDTO = new AuthorRequestDTO("Test name");
        var author = new Author(1, authorRequestDTO.name());
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO(author.getId(), author.getName());
        when(authorMapperMock.authorFromRequestDTO(any())).thenReturn(author);
        when(authorMapperMock.responseDTOFromModel(any())).thenReturn(authorResponseDTO);

        AuthorResponseDTO responseDTO = addAuthorUseCase.createAuthorFromDTO(authorRequestDTO);
        assertNotNull(responseDTO);
        assertEquals(authorResponseDTO, responseDTO);
    }
}