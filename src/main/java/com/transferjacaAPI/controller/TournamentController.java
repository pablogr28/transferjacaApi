package com.transferjacaAPI.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.transferjacaAPI.model.Tournament;
import com.transferjacaAPI.model.TournamentDTO;
import com.transferjacaAPI.service.TournamentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTournamentById(@PathVariable Long id) {
        Tournament tournament = tournamentService.getTournamentById(id);
        if (tournament == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "Tournament con ID " + id + " no encontrado."
                )
            );
        }
        return ResponseEntity.ok(tournament);
    }

    @PostMapping
    public ResponseEntity<?> createTournament(@RequestBody @Valid TournamentDTO tournamentDto) {
        if (tournamentService.existsByNameAndCountryAndYear(tournamentDto.getName(), tournamentDto.getCountry(), tournamentDto.getYear())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 409,
                    "error", "Conflict",
                    "message", "Ya existe un torneo con el mismo nombre, país y año."
                )
            );
        }

        Tournament tournament = new Tournament();
        tournament.setName(tournamentDto.getName());
        tournament.setCountry(tournamentDto.getCountry());
        tournament.setYear(tournamentDto.getYear());

        Tournament saved = tournamentService.saveTournament(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTournament(@PathVariable Long id, @RequestBody @Valid TournamentDTO tournamentDto) {
        Tournament existing = tournamentService.getTournamentById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "No se encontró el Tournament con ID " + id
                )
            );
        }

        existing.setName(tournamentDto.getName());
        existing.setCountry(tournamentDto.getCountry());
        existing.setYear(tournamentDto.getYear());

        Tournament updated = tournamentService.saveTournament(existing);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTournament(@PathVariable Long id) {
        Tournament existing = tournamentService.getTournamentById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "No se encontró el Tournament con ID " + id
                )
            );
        }

        tournamentService.deleteTournament(id);

        return ResponseEntity.ok(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 200,
                "message", "El torneo con ID " + id + " ha sido eliminado correctamente."
            )
        );
    }
}
