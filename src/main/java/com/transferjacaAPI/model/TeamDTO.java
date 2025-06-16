package com.transferjacaAPI.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;

public class TeamDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El país es obligatorio")
    @Pattern(regexp = "^[^\\d]+$", message = "El nombre no debe contener números")
    private String country;

    @NotNull(message = "El año de fundación es obligatorio")
    @Positive(message = "El año de fundación debe ser positivo")
    private Integer yearFundation;

    public TeamDTO() {
    }

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

    public Integer getYearFundation() {
        return yearFundation;
    }

    public void setYearFundation(Integer yearFundation) {
        this.yearFundation = yearFundation;
    }
}
