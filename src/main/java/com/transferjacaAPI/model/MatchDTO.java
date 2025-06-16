package com.transferjacaAPI.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MatchDTO {

    @NotNull(message = "La fecha del partido es obligatoria")
    private LocalDate date;

    @NotNull(message = "Los goles del equipo local son obligatorios")
    @Min(value = 0, message = "Los goles del equipo local deben ser mínimo 0")
    private Integer goals_home;

    @NotNull(message = "Los goles del equipo visitante son obligatorios")
    @Min(value = 0, message = "Los goles del equipo visitante deben ser mínimo 0")
    private Integer goals_visit;

    @NotNull(message = "El ID del torneo es obligatorio")
    private Long tournamentId;

    @NotNull(message = "El ID del equipo local es obligatorio")
    private Long localTeamId;

    @NotNull(message = "El ID del equipo visitante es obligatorio")
    private Long visitTeamId;

    // Getters y setters

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getGoals_home() {
        return goals_home;
    }

    public void setGoals_home(Integer goals_home) {
        this.goals_home = goals_home;
    }

    public Integer getGoals_visit() {
        return goals_visit;
    }

    public void setGoals_visit(Integer goals_visit) {
        this.goals_visit = goals_visit;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getLocalTeamId() {
        return localTeamId;
    }

    public void setLocalTeamId(Long localTeamId) {
        this.localTeamId = localTeamId;
    }

    public Long getVisitTeamId() {
        return visitTeamId;
    }

    public void setVisitTeamId(Long visitTeamId) {
        this.visitTeamId = visitTeamId;
    }
}