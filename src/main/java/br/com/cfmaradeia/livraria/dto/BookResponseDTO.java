package br.com.cfmaradeia.livraria.dto;

public record BookResponseDTO(
    Integer id,
    String title,
    String editor,
    Integer edition,
    String publicationYear
) {
}
