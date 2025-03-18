package br.com.cfmaradeia.livraria.controller;


import br.com.cfmaradeia.livraria.dto.BookRequestDTO;
import br.com.cfmaradeia.livraria.dto.BookResponseDTO;
import br.com.cfmaradeia.livraria.useCase.author.ListAuthorUseCase;
import br.com.cfmaradeia.livraria.useCase.book.AddBookUseCase;
import br.com.cfmaradeia.livraria.useCase.book.DeleteBookUseCase;
import br.com.cfmaradeia.livraria.useCase.book.ListBookUseCase;
import br.com.cfmaradeia.livraria.useCase.book.UpdateBookUseCase;
import br.com.cfmaradeia.livraria.useCase.subject.ListSubjectUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    private final ListAuthorUseCase listAuthorUseCase;
    private final ListSubjectUseCase listSubjectUseCase;
    private final ListBookUseCase listBookUseCase;
    private final AddBookUseCase addBookUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;

    public BookController(ListAuthorUseCase listAuthorUseCase, ListSubjectUseCase listSubjectUseCase, ListBookUseCase listBookUseCase, AddBookUseCase addBookUseCase, UpdateBookUseCase updateBookUseCase, DeleteBookUseCase deleteBookUseCase) {
        this.listAuthorUseCase = listAuthorUseCase;
        this.listSubjectUseCase = listSubjectUseCase;
        this.listBookUseCase = listBookUseCase;
        this.addBookUseCase = addBookUseCase;
        this.updateBookUseCase = updateBookUseCase;
        this.deleteBookUseCase = deleteBookUseCase;
    }

    @GetMapping("/new")
    public String newBook(BookRequestDTO bookRequestDTO, Model model){
        log.info("Create new book");
        model.addAttribute("allAuthors", listAuthorUseCase.findAll());
        model.addAttribute("allSubjects", listSubjectUseCase.findAll());
        model.addAttribute("books", listBookUseCase.findAll());
        return "books-add";
    }

    @PostMapping("/add")
    public String addBook(@Valid BookRequestDTO bookRequestDTO, BindingResult result, Model model){
        log.info("Creating new book from {}", bookRequestDTO);
        if(result.hasErrors()){
            model.addAttribute("allAuthors", listAuthorUseCase.findAll());
            model.addAttribute("allSubjects", listSubjectUseCase.findAll());
            model.addAttribute("books", listBookUseCase.findAll());
            log.error("Book cannot be inserted. Has errors");
            return "books-add";
        }

        addBookUseCase.createBookFromDTO(bookRequestDTO);
        return "redirect:new";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") long id, Model model){
        log.info("Open to edit book from {}", id);
        BookResponseDTO bookResponseDTO = listBookUseCase.findById((int) id);

        model.addAttribute("id", id);
        model.addAttribute("allAuthors", listAuthorUseCase.findAll());
        model.addAttribute("allSubjects", listSubjectUseCase.findAll());
        model.addAttribute("books", listBookUseCase.findAll());
        model.addAttribute("book", bookResponseDTO);
        return "books-edit";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid BookRequestDTO bookRequestDTO, BindingResult result, Model model){
        log.info("Update book from {} - {}", id, bookRequestDTO);
        if(result.hasErrors()){
            model.addAttribute("id", id);
            model.addAttribute("allAuthors", listAuthorUseCase.findAll());
            model.addAttribute("allSubjects", listSubjectUseCase.findAll());
            model.addAttribute("books", listBookUseCase.findAll());
            model.addAttribute("book", bookRequestDTO);
            log.error("Book cannot be updated. Has errors");
            return "books-edit";
        }

        updateBookUseCase.updateBookFromDTO(bookRequestDTO, (int) id);
        return "redirect:/book/new";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        log.info("Delete book from {}", id);

        deleteBookUseCase.deleteBookFromId((int) id);
        return "redirect:/book/new";
    }
}
