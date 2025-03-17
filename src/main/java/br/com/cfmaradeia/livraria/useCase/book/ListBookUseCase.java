package br.com.cfmaradeia.livraria.useCase.book;

import br.com.cfmaradeia.livraria.dto.BookResponseDTO;
import br.com.cfmaradeia.livraria.exception.BookNotFoundException;
import br.com.cfmaradeia.livraria.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ListBookUseCase {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public ListBookUseCase(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public Set<BookResponseDTO> findAll() {
        return bookRepository.findAll()
            .stream()
            .map(bookMapper::responseDTOFromModel)
            .collect(Collectors.toSet());
    }

    public BookResponseDTO findById(Integer id) {
        return bookRepository.findById(id)
            .map(bookMapper::responseDTOFromModel).orElseThrow(BookNotFoundException::new);
    }
}
