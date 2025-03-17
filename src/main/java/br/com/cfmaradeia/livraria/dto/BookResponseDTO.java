package br.com.cfmaradeia.livraria.dto;

import java.util.Set;

public record BookResponseDTO(
    Integer id,
    String title,
    String editor,
    Integer edition,
    String publicationYear,
    Set<Integer> authors,
    Set<Integer> subjects
) {
}
