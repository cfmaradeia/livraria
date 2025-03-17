package br.com.cfmaradeia.livraria.useCase.book;

import br.com.cfmaradeia.livraria.dto.BookRequestDTO;
import br.com.cfmaradeia.livraria.dto.BookResponseDTO;
import br.com.cfmaradeia.livraria.model.Author;
import br.com.cfmaradeia.livraria.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    private final ObjectMapper mapper;

    public BookMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Book bookFromRequestDTO(BookRequestDTO bookRequestDTO, Set<Author> authors) {
        Book book = new Book();
        book.setTitle(bookRequestDTO.title());
        book.setEditor(bookRequestDTO.editor());
        book.setEdition(bookRequestDTO.edition());
        book.setPublicationYear(bookRequestDTO.publicationYear());
        book.setAuthors(authors);
        book.setSubjects(null);
        return book;
    }

    public BookResponseDTO responseDTOFromModel(Book book) {
        return new BookResponseDTO(
            book.getId(),
            book.getTitle(),
            book.getEditor(),
            book.getEdition(),
            book.getPublicationYear(),
            book.getAuthors().stream().map(Author::getId).collect(Collectors.toSet())
        );
    }
}
