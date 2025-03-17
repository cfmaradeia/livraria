package br.com.cfmaradeia.livraria.useCase.author;

import br.com.cfmaradeia.livraria.dto.AuthorResponseDTO;
import br.com.cfmaradeia.livraria.model.Author;
import br.com.cfmaradeia.livraria.model.Subject;
import br.com.cfmaradeia.livraria.repository.AuthorRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    public Set<Author> findAllById(@NotNull Set<Integer> authors) {
        return new HashSet<>(authorRepository.findAllById(authors));
    }
}
