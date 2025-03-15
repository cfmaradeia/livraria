package br.com.cfmaradeia.livraria.repository;

import br.com.cfmaradeia.livraria.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
