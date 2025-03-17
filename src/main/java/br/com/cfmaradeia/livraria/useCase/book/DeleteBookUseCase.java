package br.com.cfmaradeia.livraria.useCase.book;

import br.com.cfmaradeia.livraria.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookUseCase {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public DeleteBookUseCase(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public void deleteBookFromId(int id) {
        bookRepository.deleteById(id);
    }
}
