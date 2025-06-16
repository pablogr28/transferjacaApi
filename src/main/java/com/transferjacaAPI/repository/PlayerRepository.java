package com.transferjacaAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferjacaAPI.model.Player;
import com.transferjacaAPI.model.Team;

public interface PlayerRepository extends JpaRepository<Player,Long>{
	
Optional<Player> findByName(String name);
    
    List<Player> findByPosition(String position);
    
    List<Player> findByTeam(Team team);
    
    boolean existsByNameAndPositionAndTeam(String name, String position, Team team);



}
