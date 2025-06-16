package com.transferjacaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferjacaAPI.model.PlayerTournament;
import com.transferjacaAPI.model.PlayerTournamentId;

public interface PlayerTournamentRepository extends JpaRepository<PlayerTournament,PlayerTournamentId>{

}
