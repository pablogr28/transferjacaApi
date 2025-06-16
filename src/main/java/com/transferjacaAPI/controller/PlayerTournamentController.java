package com.transferjacaAPI.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.transferjacaAPI.model.Player;
import com.transferjacaAPI.model.PlayerTournament;
import com.transferjacaAPI.model.PlayerTournamentDTO;
import com.transferjacaAPI.model.PlayerTournamentId;
import com.transferjacaAPI.model.Tournament;
import com.transferjacaAPI.service.PlayerService;
import com.transferjacaAPI.service.PlayerTournamentService;
import com.transferjacaAPI.service.TournamentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/player-tournaments")
public class PlayerTournamentController {

    @Autowired
    private PlayerTournamentService playerTournamentService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<PlayerTournament> getAllPlayerTournaments() {
        return playerTournamentService.getAllPlayerTournaments();
    }

    @GetMapping("/{playerId}/{tournamentId}")
    public ResponseEntity<?> getByIds(@PathVariable Long playerId, @PathVariable Long tournamentId) {
        PlayerTournamentId id = new PlayerTournamentId(playerId, tournamentId);
        PlayerTournament record = playerTournamentService.getPlayerTournamentById(id);
        if (record == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("timestamp", LocalDateTime.now(),
                       "status", 404,
                       "error", "Not Found",
                       "message", "No se encontró la relación jugador-torneo.")
            );
        }
        return ResponseEntity.ok(record);
    }

    @PostMapping
    public ResponseEntity<?> createPlayerTournament(@RequestBody @Valid PlayerTournamentDTO dto) {
        Player player = playerService.getPlayerById(dto.getPlayerId());
        if (player == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Jugador con ID " + dto.getPlayerId() + " no existe."));
        }

        Tournament tournament = tournamentService.getTournamentById(dto.getTournamentId());
        if (tournament == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Torneo con ID " + dto.getTournamentId() + " no existe."));
        }

        PlayerTournamentId id = new PlayerTournamentId(dto.getPlayerId(), dto.getTournamentId());
        if (playerTournamentService.getPlayerTournamentById(id) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Ya existe esta relación jugador-torneo."));
        }

        PlayerTournament pt = new PlayerTournament();
        pt.setPlayer(player);
        pt.setTournament(tournament);
        pt.setGoals(dto.getGoals());
        pt.setAssists(dto.getAssists());
        pt.setMatchPlayed(dto.getMatchPlayed());

        PlayerTournament saved = playerTournamentService.savePlayerTournament(pt);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{playerId}/{tournamentId}")
    public ResponseEntity<?> updatePlayerTournament(
        @PathVariable Long playerId,
        @PathVariable Long tournamentId,
        @RequestBody @Valid PlayerTournamentDTO dto) {

        PlayerTournamentId id = new PlayerTournamentId(playerId, tournamentId);
        PlayerTournament existing = playerTournamentService.getPlayerTournamentById(id);

        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No existe esta relación jugador-torneo."));
        }

        existing.setGoals(dto.getGoals());
        existing.setAssists(dto.getAssists());
        existing.setMatchPlayed(dto.getMatchPlayed());

        PlayerTournament updated = playerTournamentService.savePlayerTournament(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{playerId}/{tournamentId}")
    public ResponseEntity<?> deletePlayerTournament(@PathVariable Long playerId, @PathVariable Long tournamentId) {
        PlayerTournamentId id = new PlayerTournamentId(playerId, tournamentId);
        PlayerTournament existing = playerTournamentService.getPlayerTournamentById(id);

        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No existe esta relación jugador-torneo."));
        }

        playerTournamentService.deletePlayerTournament(id);
        return ResponseEntity.noContent().build();
    }
}
