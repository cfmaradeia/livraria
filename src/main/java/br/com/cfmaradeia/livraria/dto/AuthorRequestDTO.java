package br.com.cfmaradeia.livraria.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public record AuthorRequestDTO(
        @NotEmpty
        @Size(min = 2, max = 40)
        String name
){

        @Override
        public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                AuthorRequestDTO that = (AuthorRequestDTO) o;
                return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
                return Objects.hashCode(name);
        }
}
