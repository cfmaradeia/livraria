package br.com.cfmaradeia.livraria.controller;

import br.com.cfmaradeia.livraria.dto.SubjectRequestDTO;
import br.com.cfmaradeia.livraria.useCase.subject.AddSubjectUseCase;
import br.com.cfmaradeia.livraria.useCase.subject.ListSubjectUseCase;
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
@RequestMapping("/subject")
public class SubjectController {

    private final AddSubjectUseCase addSubjectUseCase;
    private final ListSubjectUseCase listubjectUseCase;

    public SubjectController(AddSubjectUseCase addSubjectUseCase, ListSubjectUseCase listubjectUseCase) {
        this.addSubjectUseCase = addSubjectUseCase;
        this.listubjectUseCase = listubjectUseCase;
    }

    @GetMapping("/new")
    public String newSubject(SubjectRequestDTO subjectRequestDTO, Model model){

        log.info("Create new subject");
        model.addAttribute("subjects", listubjectUseCase.findAll());
        model.addAttribute("subject", subjectRequestDTO);
        return "subjects-add";
    }

    @PostMapping("/add")
    public String addSubject(@Valid SubjectRequestDTO subjectRequestDTO, BindingResult result){
        log.info("Creating new subject from {}", subjectRequestDTO);
        if(result.hasErrors()){
            log.error("Subject cannot be inserted. Has errors");
            return "subjects-add";
        }

        addSubjectUseCase.createSubjectFromDTO(subjectRequestDTO);
        return "redirect:new";
    }
}
