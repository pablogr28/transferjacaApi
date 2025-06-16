package com.transferjacaAPI.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransferDTO {

    @NotNull(message = "El ID del jugador es obligatorio")
    private Long playerId;

    private Long fromTeamId; // puede ser null si es primer traspaso

    @NotNull(message = "El ID del equipo destino es obligatorio")
    private Long toTeamId;

    private LocalDate dateTransfer;

    @Positive(message = "El precio del traspaso debe ser positivo")
    private Double fee;

    // Getters y setters

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getFromTeamId() {
        return fromTeamId;
    }

    public void setFromTeamId(Long fromTeamId) {
        this.fromTeamId = fromTeamId;
    }

    public Long getToTeamId() {
        return toTeamId;
    }

    public void setToTeamId(Long toTeamId) {
        this.toTeamId = toTeamId;
    }

    public LocalDate getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(LocalDate dateTransfer) {
        this.dateTransfer = dateTransfer;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
