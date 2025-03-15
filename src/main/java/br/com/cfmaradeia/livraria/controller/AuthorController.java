package br.com.cfmaradeia.livraria.controller;

import br.com.cfmaradeia.livraria.dto.AuthorRequestDTO;
import br.com.cfmaradeia.livraria.useCase.author.AddAuthorUseCase;
import br.com.cfmaradeia.livraria.useCase.author.ListAuthorUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AddAuthorUseCase addAuthorUseCase;
    private final ListAuthorUseCase listAuthorUseCase;

    public AuthorController(AddAuthorUseCase addAuthorUseCase, ListAuthorUseCase listAuthorUseCase) {
        this.addAuthorUseCase = addAuthorUseCase;
        this.listAuthorUseCase = listAuthorUseCase;
    }

    @GetMapping("/new")
    public String newAuthor(AuthorRequestDTO authorRequestDTO, Model model){
        log.info("Create new author");
        model.addAttribute("authors", listAuthorUseCase.findAll());
        model.addAttribute("author", authorRequestDTO);
        return "authors-add";
    }

    @PostMapping("/add")
    public String addAuthor(@Valid AuthorRequestDTO authorRequestDTO, BindingResult result){
        log.info("Creating new author from {}", authorRequestDTO);
        if(result.hasErrors()){
            log.error("Author cannot be inserted. Has errors");
            return "authors-add";
        }

        addAuthorUseCase.createAuthorFromDTO(authorRequestDTO);
        return "redirect:new";
    }

}
