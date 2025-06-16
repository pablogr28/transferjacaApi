package com.transferjacaAPI.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PlayerTournamentId implements Serializable{
	
	@Column(name="jugador_id")
	private Long player;
	
	@Column(name="torneo_id")
    private Long tournament;

    public PlayerTournamentId() {}

	public PlayerTournamentId(Long player, Long tournament) {
		super();
		this.player = player;
		this.tournament = tournament;
	}

	@Override
	public int hashCode() {
		return Objects.hash(player, tournament);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerTournamentId other = (PlayerTournamentId) obj;
		return Objects.equals(player, other.player) && Objects.equals(tournament, other.tournament);
	}

	public Long getPlayer() {
		return player;
	}

	public void setPlayer(Long player) {
		this.player = player;
	}

	public Long getTournament() {
		return tournament;
	}

	public void setTournament(Long tournament) {
		this.tournament = tournament;
	}
	

 
}
