package com.transferjacaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferjacaAPI.model.Match;

public interface MatchRepository extends JpaRepository<Match,Long>{

}
