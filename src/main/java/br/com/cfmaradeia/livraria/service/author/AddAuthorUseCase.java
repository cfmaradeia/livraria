package br.com.cfmaradeia.livraria.service.author;

import br.com.cfmaradeia.livraria.dto.AuthorRequestDTO;
import br.com.cfmaradeia.livraria.dto.AuthorResponseDTO;
import br.com.cfmaradeia.livraria.model.Author;
import br.com.cfmaradeia.livraria.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddAuthorUseCase {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    public AddAuthorUseCase(AuthorMapper authorMapper, AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    public AuthorResponseDTO createAuthorFromDTO(AuthorRequestDTO authorRequestDTO){
        log.info("Creating author from AuthorDTO: {}", authorRequestDTO);
        Author author = authorMapper.authorFromRequestDTO(authorRequestDTO);

        return authorMapper.responseDTOFromModel(authorRepository.save(author));
    }
}
