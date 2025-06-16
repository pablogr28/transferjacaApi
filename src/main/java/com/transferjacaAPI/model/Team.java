package com.transferjacaAPI.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "equipos")
public class Team {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message="El nombre es obligatorio")
	@Column(name="nombre")
	private String name;
	
	@NotBlank(message="El país es obligatorio")
	@Pattern(regexp = "^[^\\d]+$", message = "El nombre no debe contener números")
	@Column(name="pais")
	private String country;
	
	@NotNull(message="El año de fundación es obligatorio")
	@Positive(message = "El año de fundación debe ser positivo")
	@Column(name="anio_fundacion")
	private Integer yearFundation;
	
	@OneToMany(mappedBy="team")
	@JsonManagedReference
	private List<Player> players;
	
	@OneToMany(mappedBy="fromTeam")
	@JsonManagedReference
	private List<Transfer> transferOut;
	
	@OneToMany(mappedBy="toTeam")
	@JsonManagedReference
	private List<Transfer> transferIn;
	
	@OneToMany(mappedBy = "localTeam")
	@JsonManagedReference
	private List<Match> matchesAsLocal;

	@OneToMany(mappedBy = "visitTeam")
	@JsonManagedReference
	private List<Match> matchesAsVisit;



	public Team(Long id, String name, String country, Integer yearFundation) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.yearFundation = yearFundation;
	}

	public Team() {
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

	public Integer getYearFundation() {
		return yearFundation;
	}

	public void setYearFundation(Integer yearFundation) {
		this.yearFundation = yearFundation;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Transfer> getTransferOut() {
		return transferOut;
	}

	public void setTransferOut(List<Transfer> transferOut) {
		this.transferOut = transferOut;
	}

	public List<Transfer> getTransferIn() {
		return transferIn;
	}

	public void setTransferIn(List<Transfer> transferIn) {
		this.transferIn = transferIn;
	}
	
	public List<Match> getMatchesAsLocal() {
		return matchesAsLocal;
	}

	public void setMatchesAsLocal(List<Match> matchesAsLocal) {
		this.matchesAsLocal = matchesAsLocal;
	}

	public List<Match> getMatchesAsVisit() {
		return matchesAsVisit;
	}

	public void setMatchesAsVisit(List<Match> matchesAsVisit) {
		this.matchesAsVisit = matchesAsVisit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, id, name, yearFundation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		return Objects.equals(country, other.country) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(yearFundation, other.yearFundation);
	}
	
	
	
	

}
