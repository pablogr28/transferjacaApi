package com.transferjacaAPI.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="traspasos")
public class Transfer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JoinColumn(name="jugador_id")
	@JsonBackReference
	private Player player;
	
	@ManyToOne
	@JoinColumn(name="equipo_origen_id")
	@JsonBackReference
	private Team fromTeam;
	
	@NotNull(message="El equipo trapasado debe ser obligatorio")
	@ManyToOne
	@JoinColumn(name="equipo_destino_id")
	@JsonBackReference
	private Team toTeam;
	
	@Column(name="fecha_traspaso")
	private LocalDate dateTransfer;
	
	@Positive(message="El precio del trapaso debe ser positivo")
	@Column(name="precio")
	private Double fee;
	
	

	public Transfer(Long id, Player player, Team fromTeam, Team toTeam, LocalDate dateTransfer, Double fee) {
		super();
		this.id = id;
		this.player = player;
		this.fromTeam = fromTeam;
		this.toTeam = toTeam;
		this.dateTransfer = dateTransfer;
		this.fee = fee;
	}

	public Transfer() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Team getFromTeam() {
		return fromTeam;
	}

	public void setFromTeam(Team fromTeam) {
		this.fromTeam = fromTeam;
	}

	public Team getToTeam() {
		return toTeam;
	}

	public void setToTeam(Team toTeam) {
		this.toTeam = toTeam;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transfer other = (Transfer) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
