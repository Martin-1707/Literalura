package com.aluraChallen.literalura.Models.Records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Autor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer cumpleanios,
        @JsonAlias("death_year") Integer fechaFallecimiento) {
}
