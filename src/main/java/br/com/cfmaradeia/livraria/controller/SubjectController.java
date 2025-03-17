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
    private final ListSubjectUseCase listSubjectUseCase;

    public SubjectController(AddSubjectUseCase addSubjectUseCase, ListSubjectUseCase listSubjectUseCase) {
        this.addSubjectUseCase = addSubjectUseCase;
        this.listSubjectUseCase = listSubjectUseCase;
    }

    @GetMapping("/new")
    public String newSubject(SubjectRequestDTO subjectRequestDTO, Model model){
        log.info("Create new subject");
        model.addAttribute("allSubjects", listSubjectUseCase.findAll());
        return "subjects-add";
    }

    @PostMapping("/add")
    public String addSubject(@Valid SubjectRequestDTO subjectRequestDTO, BindingResult result, Model model){
        log.info("Creating new subject from {}", subjectRequestDTO);
        if(result.hasErrors()){
            model.addAttribute("allSubjects", listSubjectUseCase.findAll());
            log.error("Subject cannot be inserted. Has errors");
            return "subjects-add";
        }

        addSubjectUseCase.createSubjectFromDTO(subjectRequestDTO);
        return "redirect:new";
    }
}
