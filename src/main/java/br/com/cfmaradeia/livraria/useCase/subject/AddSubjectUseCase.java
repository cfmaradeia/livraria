package br.com.cfmaradeia.livraria.useCase.subject;

import br.com.cfmaradeia.livraria.dto.SubjectRequestDTO;
import br.com.cfmaradeia.livraria.dto.SubjectResponseDTO;
import br.com.cfmaradeia.livraria.model.Subject;
import br.com.cfmaradeia.livraria.repository.SubjectRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddSubjectUseCase {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public AddSubjectUseCase(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    public SubjectResponseDTO createSubjectFromDTO(@Valid SubjectRequestDTO subjectRequestDTO) {
        log.info("Creating subject from SubjectDTO: {}", subjectRequestDTO);
        Subject subject = subjectMapper.subjectFromRequestDTO(subjectRequestDTO);

        return subjectMapper.responseDTOFromModel(subjectRepository.save(subject));
    }
}
