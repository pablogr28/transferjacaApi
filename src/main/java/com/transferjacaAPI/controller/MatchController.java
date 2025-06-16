package com.transferjacaAPI.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.transferjacaAPI.model.Match;
import com.transferjacaAPI.model.MatchDTO;
import com.transferjacaAPI.model.Team;
import com.transferjacaAPI.model.Tournament;
import com.transferjacaAPI.service.MatchService;
import com.transferjacaAPI.service.TeamService;
import com.transferjacaAPI.service.TournamentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMatchById(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);
        if (match == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "Match con ID " + id + " no encontrado."
                )
            );
        }
        return ResponseEntity.ok(match);
    }

    @PostMapping
    public ResponseEntity<?> createMatch(@RequestBody @Valid MatchDTO matchDto) {
        Tournament tournament = tournamentService.getTournamentById(matchDto.getTournamentId());
        if (tournament == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("message", "El torneo con ID " + matchDto.getTournamentId() + " no existe.")
            );
        }

        Team localTeam = teamService.getTeamById(matchDto.getLocalTeamId());
        if (localTeam == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("message", "El equipo local con ID " + matchDto.getLocalTeamId() + " no existe.")
            );
        }

        Team visitTeam = teamService.getTeamById(matchDto.getVisitTeamId());
        if (visitTeam == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("message", "El equipo visitante con ID " + matchDto.getVisitTeamId() + " no existe.")
            );
        }

        // Construir el match provisional para validar duplicado
        Match match = new Match();
        match.setDate(matchDto.getDate());
        match.setGoalsHome(String.valueOf(matchDto.getGoals_home()));
        match.setGoalsVisit(String.valueOf(matchDto.getGoals_visit()));
        match.setTournamentMatch(tournament);
        match.setLocalTeam(localTeam);
        match.setVisitTeam(visitTeam);

        // Validar si ya existe uno igual
        if (matchService.existsMatch(match)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of("message", "Ya existe un partido con esos mismos datos.")
            );
        }

        // Guardar si no existe
        Match saved = matchService.saveMatch(match);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateMatch(@PathVariable Long id, @RequestBody @Valid MatchDTO matchDto) {
        Match existing = matchService.getMatchById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "No se encontró el Match con ID " + id
                )
            );
        }

        Tournament tournament = tournamentService.getTournamentById(matchDto.getTournamentId());
        if (tournament == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("message", "El torneo con ID " + matchDto.getTournamentId() + " no existe.")
            );
        }

        Team localTeam = teamService.getTeamById(matchDto.getLocalTeamId());
        if (localTeam == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("message", "El equipo local con ID " + matchDto.getLocalTeamId() + " no existe.")
            );
        }

        Team visitTeam = teamService.getTeamById(matchDto.getVisitTeamId());
        if (visitTeam == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("message", "El equipo visitante con ID " + matchDto.getVisitTeamId() + " no existe.")
            );
        }

        existing.setDate(matchDto.getDate());
        existing.setGoalsHome(String.valueOf(matchDto.getGoals_home()));
        existing.setGoalsVisit(String.valueOf(matchDto.getGoals_visit()));
        existing.setTournamentMatch(tournament);
        existing.setLocalTeam(localTeam);
        existing.setVisitTeam(visitTeam);

        Match updated = matchService.saveMatch(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMatch(@PathVariable Long id) {
        Match existing = matchService.getMatchById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "No se encontró el Match con ID " + id
                )
            );
        }
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
