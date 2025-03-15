package br.com.cfmaradeia.livraria.dto;

import java.util.Objects;

public record AuthorResponseDTO(
        Integer id,
        String name
){

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthorResponseDTO that = (AuthorResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
