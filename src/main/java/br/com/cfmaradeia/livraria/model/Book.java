package br.com.cfmaradeia.livraria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod")
    private int id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "editora")
    private String editor;

    @Column(name = "edicao")
    private int edition;

    @Column(name = "ano_publicacao")
    private String publicationYear;

    @ManyToMany
    @JoinTable(name = "livros_autores",
            joinColumns = @JoinColumn(name = "cod_livro"),
            inverseJoinColumns = @JoinColumn(name = "cod_autor"))
    private Set<Author> authors;

    @ManyToMany
    @JoinTable(name = "livros_assuntos",
            joinColumns = @JoinColumn(name = "cod_livro"),
            inverseJoinColumns = @JoinColumn(name = "cod_assunto"))
    private Set<Subject> subjects;

}
