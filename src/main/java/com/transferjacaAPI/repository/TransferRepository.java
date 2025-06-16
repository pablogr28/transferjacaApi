package com.transferjacaAPI.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferjacaAPI.model.Player;
import com.transferjacaAPI.model.Team;
import com.transferjacaAPI.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer,Long>{
	
	boolean existsByPlayerAndFromTeamAndToTeamAndDateTransfer(Player player, Team fromTeam, Team toTeam, LocalDate dateTransfer);


}
