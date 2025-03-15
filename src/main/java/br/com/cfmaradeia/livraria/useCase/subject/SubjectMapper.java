package br.com.cfmaradeia.livraria.useCase.subject;

import br.com.cfmaradeia.livraria.dto.SubjectRequestDTO;
import br.com.cfmaradeia.livraria.dto.SubjectResponseDTO;
import br.com.cfmaradeia.livraria.model.Subject;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class SubjectMapper {

    private final ObjectMapper mapper;

    public SubjectMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Subject subjectFromRequestDTO(@Valid SubjectRequestDTO subjectRequestDTO) {
        return mapper.convertValue(subjectRequestDTO, Subject.class);
    }

    public SubjectResponseDTO responseDTOFromModel(Subject subject){
        return mapper.convertValue(subject, SubjectResponseDTO.class);
    }
}
