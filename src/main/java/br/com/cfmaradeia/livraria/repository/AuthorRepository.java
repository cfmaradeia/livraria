package br.com.cfmaradeia.livraria.repository;

import br.com.cfmaradeia.livraria.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
