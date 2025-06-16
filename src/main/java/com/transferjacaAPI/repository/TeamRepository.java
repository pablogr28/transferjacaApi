package com.transferjacaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferjacaAPI.model.Team;

public interface TeamRepository extends JpaRepository<Team,Long>{
	
	boolean existsByNameAndCountryAndYearFundation(String name, String country, Integer yearFundation);


}
