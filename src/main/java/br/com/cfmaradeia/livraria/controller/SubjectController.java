package br.com.cfmaradeia.livraria.controller;

import br.com.cfmaradeia.livraria.dto.SubjectRequestDTO;
import br.com.cfmaradeia.livraria.useCase.subject.AddSubjectUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/subject")
public class SubjectController {

    private final AddSubjectUseCase addSubjectUseCase;

    public SubjectController(AddSubjectUseCase addSubjectUseCase) {
        this.addSubjectUseCase = addSubjectUseCase;
    }

    @GetMapping("/new")
    public String newSubject(SubjectRequestDTO subjectRequestDTO, Model model){

        log.info("Create new subject");
        model.addAttribute("subjects", List.of());
        model.addAttribute("subject", subjectRequestDTO);
        return "subjects-add";
    }

    @PostMapping("/add")
    public String addSubject(@Valid SubjectRequestDTO subjectRequestDTO, BindingResult result, Model model){
        log.info("Creating new subject from {}", subjectRequestDTO);
        if(result.hasErrors()){
            log.error("Subject cannot be inserted. Has errors");
            return "subjects-add";
        }

        addSubjectUseCase.createSubjectFromDTO(subjectRequestDTO);
        return "redirect:new";
    }
}
