package br.com.cfmaradeia.livraria.controller;


import br.com.cfmaradeia.livraria.dto.BookRequestDTO;
import br.com.cfmaradeia.livraria.dto.SubjectRequestDTO;
import br.com.cfmaradeia.livraria.useCase.author.ListAuthorUseCase;
import br.com.cfmaradeia.livraria.useCase.book.AddBookUseCase;
import br.com.cfmaradeia.livraria.useCase.book.ListBookUseCase;
import br.com.cfmaradeia.livraria.useCase.subject.ListSubjectUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    private final ListAuthorUseCase listAuthorUseCase;
    private final ListSubjectUseCase listSubjectUseCase;
    private final ListBookUseCase listBookUseCase;
    private final AddBookUseCase addBookUseCase;

    public BookController(ListAuthorUseCase listAuthorUseCase, ListSubjectUseCase listSubjectUseCase, ListBookUseCase listBookUseCase, AddBookUseCase addBookUseCase) {
        this.listAuthorUseCase = listAuthorUseCase;
        this.listSubjectUseCase = listSubjectUseCase;
        this.listBookUseCase = listBookUseCase;
        this.addBookUseCase = addBookUseCase;
    }

    @GetMapping("/new")
    public String newBook(BookRequestDTO bookRequestDTO, Model model){
        log.info("Create new book");
        model.addAttribute("books", listBookUseCase.findAll());
        model.addAttribute("subjects", listSubjectUseCase.findAll());
        model.addAttribute("authors", listAuthorUseCase.findAll());
        model.addAttribute("book", bookRequestDTO);
        return "books-add";
    }

    @PostMapping("/add")
    public String addBook(@Valid BookRequestDTO bookRequestDTO, BindingResult result, Model model){
        log.info("Creating new book from {}", bookRequestDTO);
        if(result.hasErrors()){
            log.error("Book cannot be inserted. Has errors");
            return "books-add";
        }

        addBookUseCase.createBookFromDTO(bookRequestDTO);
        return "redirect:new";
    }
}
