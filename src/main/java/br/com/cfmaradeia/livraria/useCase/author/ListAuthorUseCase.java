package br.com.cfmaradeia.livraria.useCase.author;

import br.com.cfmaradeia.livraria.dto.AuthorResponseDTO;
import br.com.cfmaradeia.livraria.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ListAuthorUseCase {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    public ListAuthorUseCase(AuthorMapper authorMapper, AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    public Set<AuthorResponseDTO> findAll(){
        return authorRepository.findAll()
            .stream()
            .map(authorMapper::responseDTOFromModel)
            .collect(Collectors.toSet());
    }

}
