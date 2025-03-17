package br.com.cfmaradeia.livraria.useCase.book;

import br.com.cfmaradeia.livraria.dto.BookRequestDTO;
import br.com.cfmaradeia.livraria.dto.BookResponseDTO;
import br.com.cfmaradeia.livraria.exception.BookNotFoundException;
import br.com.cfmaradeia.livraria.model.Book;
import br.com.cfmaradeia.livraria.repository.BookRepository;
import br.com.cfmaradeia.livraria.useCase.author.ListAuthorUseCase;
import br.com.cfmaradeia.livraria.useCase.subject.ListSubjectUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UpdateBookUseCase {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final ListAuthorUseCase listAuthorUseCase;
    private final ListSubjectUseCase listSubjectUseCase;

    public UpdateBookUseCase(BookRepository bookRepository, BookMapper bookMapper, ListAuthorUseCase listAuthorUseCase, ListSubjectUseCase listSubjectUseCase) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.listAuthorUseCase = listAuthorUseCase;
        this.listSubjectUseCase = listSubjectUseCase;
    }

    public BookResponseDTO updateBookFromDTO(@Valid BookRequestDTO bookRequestDTO, Integer id) {
        log.info("Updating book from BookRequestDTO: {}", bookRequestDTO);

        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isEmpty()){
            throw new BookNotFoundException();
        }

        Book book = bookMapper.bookFromRequestDTO(bookRequestDTO, listAuthorUseCase.findAllById(bookRequestDTO.authors()), listSubjectUseCase.findAllById(bookRequestDTO.subjects()));
        book.setId(optionalBook.get().getId());

        return bookMapper.responseDTOFromModel(bookRepository.save(book));
    }
}
