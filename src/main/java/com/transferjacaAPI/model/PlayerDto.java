package com.transferjacaAPI.model;


import java.util.List;

import jakarta.validation.constraints.*;

public class PlayerDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "^[^\\d]+$", message = "El nombre no debe contener números")
    private String name;

    @NotBlank(message = "La posición es obligatoria")
    private String position;

    private boolean active;

    @Min(value = 15, message = "La edad mínima es de 15 años")
    @NotNull(message = "La edad es obligatoria")
    private Integer age;

    @DecimalMin(value = "0.0", inclusive = false, message = "La estatura debe ser mayor a 0")
    @NotNull(message = "La estatura es obligatoria")
    private Double stature;

    @Positive(message = "El valor de mercado debe ser positivo")
    @NotNull(message = "El valor de mercado es obligatorio")
    private Double marketValue;

    @NotBlank(message = "La pierna fuerte es obligatoria")
    private String footFavourite;

    @NotNull(message="El equipo es obligatorio")
    private Long teamId;

    // Getters y setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getStature() {
        return stature;
    }

    public void setStature(Double stature) {
        this.stature = stature;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public String getFootFavourite() {
        return footFavourite;
    }

    public void setFootFavourite(String footFavourite) {
        this.footFavourite = footFavourite;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}

