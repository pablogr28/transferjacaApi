package com.transferjacaAPI.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PlayerTournamentDTO {

    @NotNull(message = "El ID del jugador es obligatorio")
    private Long playerId;

    @NotNull(message = "El ID del torneo es obligatorio")
    private Long tournamentId;

    @Min(value = 0, message = "Los goles deben ser mínimo 0")
    private Integer goals;

    @Min(value = 0, message = "Las asistencias deben ser mínimo 0")
    private Integer assists;

    @Min(value = 0, message = "Los partidos jugados deben ser mínimo 0")
    private Integer matchPlayed;

    // Getters y Setters

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getMatchPlayed() {
        return matchPlayed;
    }

    public void setMatchPlayed(Integer matchPlayed) {
        this.matchPlayed = matchPlayed;
    }
}

