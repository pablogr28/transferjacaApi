package com.transferjacaAPI.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transferjacaAPI.model.Team;
import com.transferjacaAPI.model.TeamDTO;
import com.transferjacaAPI.service.TeamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> findAll() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Team result = teamService.getTeamById(id);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "Team con ID " + id + " no encontrado."
                )
            );
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid TeamDTO teamDTO) {
        if (teamService.existsByNameAndCountryAndYearFundation(teamDTO.getName(), teamDTO.getCountry(), teamDTO.getYearFundation())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 409,
                    "error", "Conflict",
                    "message", "Ya existe un equipo con el mismo nombre, país y año de fundación."
                )
            );
        }

        Team team = new Team();
        team.setName(teamDTO.getName());
        team.setCountry(teamDTO.getCountry());
        team.setYearFundation(teamDTO.getYearFundation());

        Team saved = teamService.saveTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody @Valid TeamDTO teamDTO, @PathVariable Long id) {
        Team existing = teamService.getTeamById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "No se encontró el Team con ID " + id
                )
            );
        }

        existing.setName(teamDTO.getName());
        existing.setCountry(teamDTO.getCountry());
        existing.setYearFundation(teamDTO.getYearFundation());

        return ResponseEntity.ok(teamService.saveTeam(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Team existing = teamService.getTeamById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "No se encontró el Team con ID " + id
                )
            );
        }
        teamService.deleteTeam(id);
        return ResponseEntity.ok(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 200,
                "message", "Equipo con ID " + id + " eliminado correctamente."
            )
        );
    }

}
