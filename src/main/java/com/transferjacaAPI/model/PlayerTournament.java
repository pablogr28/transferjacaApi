package com.transferjacaAPI.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugadores_torneos")
public class PlayerTournament {

	@EmbeddedId
    private PlayerTournamentId id;

    @ManyToOne
    @MapsId("player")
    @JoinColumn(name = "jugador_id")
    @JsonBackReference
    private Player player;

    @ManyToOne
    @MapsId("tournament")
    @JoinColumn(name = "torneo_id")
    @JsonBackReference
    private Tournament tournament;


    @Column(name="goles")
    private Integer goals;
    
    @Column(name="asistencias")
    private Integer assists;
    
    @Column(name="partidos_jugados")
    private Integer matchPlayed;

    public PlayerTournament() {}

	public PlayerTournament(Player player, Tournament tournament, Integer goals, Integer assists,
			Integer matchPlayed) {
		super();
		this.player = player;
		this.tournament = tournament;
		this.goals = goals;
		this.assists = assists;
		this.matchPlayed = matchPlayed;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
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
		PlayerTournament other = (PlayerTournament) obj;
		return Objects.equals(player, other.player) && Objects.equals(tournament, other.tournament);
	}

	

    
}
