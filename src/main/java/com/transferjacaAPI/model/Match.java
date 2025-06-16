package com.transferjacaAPI.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="partidos")
public class Match {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull(message="La fecha del partido debe ser obligatoria")
	@Column(name="fecha")
	private LocalDate date;
	
	@NotNull(message="El campo de los goles del equipo local debe ser obligatorio")
	@Min(value=0,message="Los goles del equipo local debe ser como mínimo 0")
	@Column(name="goles_local")
	private String goals_home;
	
	@NotNull(message="El campo de los goles del equipo visitante debe ser obligatorio")
	@Min(value=0,message="Los goles del equipo visitante debe ser como mínimo 0")
	@Column(name="goles_visitante")
	private String goals_visit;
	
	@NotNull(message="El torneo debe ser obligatorio")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="torneo_id")
	@JsonBackReference
	private Tournament tournamentMatch;

	@NotNull(message="El equipo local debe ser obligatorio")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="equipo_local_id")
	@JsonBackReference
	private Team localTeam;

	@NotNull(message="El equipo visitante debe ser obligatorio")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="equipo_visitante_id")
	@JsonBackReference
	private Team visitTeam;
	
	@Column(name="casa")
	private Integer home;

	public Match(Long id, LocalDate date, String goals_home, String goals_visit, Tournament tournament, Team localTeam,
			Team visitTeam) {
		super();
		this.id = id;
		this.date = date;
		this.goals_home = goals_home;
		this.goals_visit = goals_visit;
		this.tournamentMatch = tournament;
		this.localTeam = localTeam;
		this.visitTeam = visitTeam;
	}

	public Match() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getGoals_home() {
		return goals_home;
	}

	public void setGoals_home(String goals_home) {
		this.goals_home = goals_home;
	}

	public String getGoals_visit() {
		return goals_visit;
	}

	public void setGoals_visit(String goals_visit) {
		this.goals_visit = goals_visit;
	}

	public Tournament getTournamentMatch() {
		return tournamentMatch;
	}

	public void setTournamentMatch(Tournament tournamentMatch) {
		this.tournamentMatch = tournamentMatch;
	}

	public Team getLocalTeam() {
		return localTeam;
	}

	public void setLocalTeam(Team localTeam) {
		this.localTeam = localTeam;
	}

	public Team getVisitTeam() {
		return visitTeam;
	}

	public void setVisitTeam(Team visitTeam) {
		this.visitTeam = visitTeam;
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
		Match other = (Match) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
