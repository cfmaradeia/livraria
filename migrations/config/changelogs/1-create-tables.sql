CREATE TABLE livros (
	cod serial NOT NULL,
	titulo varchar(40) NOT NULL,
	editora varchar(40) NOT NULL,
	edicao int NOT NULL,
	ano_publicacao varchar(4) NOT NULL,
	CONSTRAINT livros_pk PRIMARY KEY (cod)
);

CREATE TABLE assuntos (
	cod serial NOT NULL,
	descricao varchar(20) NULL,
	CONSTRAINT assuntos_pk PRIMARY KEY (cod)
);

CREATE TABLE autores (
	cod serial NOT NULL,
	nome varchar(40) NOT NULL,
	CONSTRAINT autores_pk PRIMARY KEY (cod)
);

CREATE TABLE livros_autores (
	cod_livro int NOT NULL,
	cod_autor int NOT NULL,
	CONSTRAINT livros_autores_livros_fk FOREIGN KEY (cod_livro) REFERENCES livros(cod) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT livros_autores_autores_fk FOREIGN KEY (cod_autor) REFERENCES autores(cod) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE livros_assuntos (
	cod_livro int NOT NULL,
	cod_assunto int NOT NULL,
	CONSTRAINT livros_assuntos_livros_fk FOREIGN KEY (cod_livro) REFERENCES livros(cod) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT livros_assuntos_assuntos_fk FOREIGN KEY (cod_assunto) REFERENCES assuntos(cod) ON DELETE CASCADE ON UPDATE CASCADE
);

