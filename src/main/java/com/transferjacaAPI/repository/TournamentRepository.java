package com.transferjacaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferjacaAPI.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament,Long>{
	
	boolean existsByNameAndCountryAndYear(String name, String country, Integer year);


}
