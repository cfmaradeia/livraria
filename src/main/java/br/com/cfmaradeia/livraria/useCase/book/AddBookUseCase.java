package br.com.cfmaradeia.livraria.useCase.book;

import br.com.cfmaradeia.livraria.dto.BookRequestDTO;
import br.com.cfmaradeia.livraria.dto.BookResponseDTO;
import br.com.cfmaradeia.livraria.model.Book;
import br.com.cfmaradeia.livraria.repository.BookRepository;
import br.com.cfmaradeia.livraria.useCase.author.ListAuthorUseCase;
import br.com.cfmaradeia.livraria.useCase.subject.ListSubjectUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddBookUseCase {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final ListAuthorUseCase listAuthorUseCase;
    private final ListSubjectUseCase listSubjectUseCase;

    public AddBookUseCase(BookRepository bookRepository, BookMapper bookMapper, ListAuthorUseCase listAuthorUseCase, ListSubjectUseCase listSubjectUseCase) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.listAuthorUseCase = listAuthorUseCase;
        this.listSubjectUseCase = listSubjectUseCase;
    }

    public BookResponseDTO createBookFromDTO(BookRequestDTO bookRequestDTO){
        log.info("Creating author from BookRequestDTO: {}", bookRequestDTO);

        Book book = bookMapper.bookFromRequestDTO(bookRequestDTO, listAuthorUseCase.findAllById(bookRequestDTO.authors()));

        return bookMapper.responseDTOFromModel(bookRepository.save(book));
    }
}
