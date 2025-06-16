package com.transferjacaAPI.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transferjacaAPI.model.Match;
import com.transferjacaAPI.model.Team;
import com.transferjacaAPI.model.Tournament;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

	Optional<Match> findByDateAndGoalsHomeAndGoalsVisitAndTournamentMatchAndLocalTeamAndVisitTeam(
		    LocalDate date, String goalsHome, String goalsVisit, Tournament tournamentMatch, Team localTeam, Team visitTeam
		);


}

