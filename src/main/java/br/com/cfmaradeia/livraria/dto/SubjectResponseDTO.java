package br.com.cfmaradeia.livraria.dto;

import java.util.Objects;

public record SubjectResponseDTO(
        Integer id,
        String description
){

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubjectResponseDTO that = (SubjectResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
