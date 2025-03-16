package br.com.cfmaradeia.livraria.repository;

import br.com.cfmaradeia.livraria.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
