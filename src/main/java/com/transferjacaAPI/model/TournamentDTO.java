package com.transferjacaAPI.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class TournamentDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El país es obligatorio")
    @Pattern(regexp = "^[^\\d]+$", message = "El país no debe contener números")
    private String country;

    @NotNull(message = "El año es obligatorio")
    @Positive(message = "El año debe ser positivo")
    private Integer year;

    // Getters y setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
