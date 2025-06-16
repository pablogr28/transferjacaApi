package com.transferjacaAPI.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="torneos")
public class Tournament {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Column(name="nombre")
	private String name;
	
	@NotBlank(message = "La localización es obligatorio")
	@Pattern(regexp = "^[^\\d]+$", message = "La localización no debe contener números")
	@Column(name="pais")
	private String country;
	
	@NotNull(message="El año de fundación es obligatorio")
	@Positive(message = "El año de fundación debe ser positivo")
	@Column(name="anio")
	private Integer year;
	
	@OneToMany(mappedBy="tournament")
	@JsonManagedReference
	private List<PlayerTournament> playerTournament;
	
	@OneToMany(mappedBy="tournamentMatch", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Match> matches;


	public Tournament(Long id, String name, String country, Integer year) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.year = year;
	}

	public Tournament() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<PlayerTournament> getPlayerTournament() {
		return playerTournament;
	}

	public void setPlayerTournament(List<PlayerTournament> playerTournament) {
		this.playerTournament = playerTournament;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
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
		Tournament other = (Tournament) obj;
		return Objects.equals(id, other.id);
	}

	
	

}
