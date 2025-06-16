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

import com.transferjacaAPI.model.Player;
import com.transferjacaAPI.model.PlayerDTO;
import com.transferjacaAPI.model.Team;
import com.transferjacaAPI.service.PlayerService;
import com.transferjacaAPI.service.TeamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    
    @Autowired
    private TeamService teamService;


    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "Player con ID " + id + " no encontrado."
                )
            );
        }
        return ResponseEntity.ok(player);
    }

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody @Valid PlayerDTO playerDto) {
        // Validar que el equipo existe
        Team team = teamService.getTeamById(playerDto.getTeamId());
        if (team == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 400,
                    "error", "Bad Request",
                    "message", "El equipo con ID " + playerDto.getTeamId() + " no existe."
                )
            );
        }

        // Validar que no existe un jugador con el mismo nombre + posición + equipo
        boolean exists = playerService.existsByNameAndPositionAndTeam(playerDto.getName(), playerDto.getPosition(), team);
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 409,
                    "error", "Conflict",
                    "message", "Ya existe un jugador con nombre '" + playerDto.getName() + "', posición '" + playerDto.getPosition() + "' en ese equipo."
                )
            );
        }

        // Mapear DTO a entidad Player
        Player player = new Player();
        player.setName(playerDto.getName());
        player.setPosition(playerDto.getPosition());
        player.setActive(playerDto.isActive());
        player.setAge(playerDto.getAge());
        player.setStature(playerDto.getStature());
        player.setMarketValue(playerDto.getMarketValue());
        player.setFootFavourite(playerDto.getFootFavourite());
        player.setTeam(team);

        // Guardar el jugador
        Player saved = playerService.savePlayer(player);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable Long id, @RequestBody @Valid PlayerDTO playerDto) {
        Player existing = playerService.getPlayerById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "No se encontró el Player con ID " + id
                )
            );
        }

        // Validar el equipo existe
        Team team = teamService.getTeamById(playerDto.getTeamId());
        if (team == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                    "message", "El equipo con ID " + playerDto.getTeamId() + " no existe."
                )
            );
        }

        // Actualizar datos del jugador
        existing.setName(playerDto.getName());
        existing.setPosition(playerDto.getPosition());
        existing.setActive(playerDto.isActive());
        existing.setAge(playerDto.getAge());
        existing.setStature(playerDto.getStature());
        existing.setMarketValue(playerDto.getMarketValue());
        existing.setFootFavourite(playerDto.getFootFavourite());
        existing.setTeam(team);

        Player updated = playerService.savePlayer(existing);

        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        Player existing = playerService.getPlayerById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "No se encontró el Player con ID " + id
                )
            );
        }

        playerService.deletePlayer(id);

        return ResponseEntity.ok(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 200,
                "message", "El jugador con ID " + id + " ha sido eliminado correctamente."
            )
        );
    }

}
