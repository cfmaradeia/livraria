package br.com.cfmaradeia.livraria.useCase.author;

import br.com.cfmaradeia.livraria.dto.AuthorRequestDTO;
import br.com.cfmaradeia.livraria.dto.AuthorResponseDTO;
import br.com.cfmaradeia.livraria.model.Author;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthorMapper {

    private final ObjectMapper mapper;

    public AuthorMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Author authorFromRequestDTO(AuthorRequestDTO authorRequestDTO){
        return mapper.convertValue(authorRequestDTO, Author.class);
    }

    public AuthorResponseDTO responseDTOFromModel(Author author){
        return mapper.convertValue(author, AuthorResponseDTO.class);
    }
}
