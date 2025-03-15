package br.com.cfmaradeia.livraria.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public record SubjectRequestDTO(
        @NotEmpty
        @Size(min = 2, max = 20)
        String description
){

        @Override
        public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                SubjectRequestDTO that = (SubjectRequestDTO) o;
                return Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
                return Objects.hashCode(description);
        }
}
