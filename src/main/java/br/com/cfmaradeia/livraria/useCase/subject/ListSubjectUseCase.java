package br.com.cfmaradeia.livraria.useCase.subject;

import br.com.cfmaradeia.livraria.dto.SubjectResponseDTO;
import br.com.cfmaradeia.livraria.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ListSubjectUseCase {

    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;

    public ListSubjectUseCase(SubjectMapper subjectMapper, SubjectRepository subjectRepository) {
        this.subjectMapper = subjectMapper;
        this.subjectRepository = subjectRepository;
    }

    public Set<SubjectResponseDTO> findAll(){
        return subjectRepository.findAll()
            .stream()
            .map(subjectMapper::responseDTOFromModel)
            .collect(Collectors.toSet());
    }

}
