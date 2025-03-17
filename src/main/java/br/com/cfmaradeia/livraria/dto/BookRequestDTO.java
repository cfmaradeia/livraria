package br.com.cfmaradeia.livraria.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record  BookRequestDTO(
    @NotEmpty
    @Size(min = 2, max = 40)
    String title,

    @NotEmpty
    @Size(min = 2, max = 40)
    String editor,

    @NotNull
    @Min(1)
    Integer edition,

    @NotEmpty
    @Size(min = 4, max = 4)
    String publicationYear,

    @NotEmpty
    @NotNull
    Set<Integer> authors,

    @NotEmpty
    @NotNull
    Set<Integer> subjects
) {
}
