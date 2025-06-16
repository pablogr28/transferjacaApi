package com.transferjacaAPI.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.transferjacaAPI.model.Player;
import com.transferjacaAPI.model.Team;
import com.transferjacaAPI.model.Transfer;
import com.transferjacaAPI.model.TransferDTO;
import com.transferjacaAPI.service.PlayerService;
import com.transferjacaAPI.service.TeamService;
import com.transferjacaAPI.service.TransferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Transfer> getAllTransfers() {
        return transferService.getAllTransfers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransferById(@PathVariable Long id) {
        Transfer transfer = transferService.getTransferById(id);
        if (transfer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "Transfer con ID " + id + " no encontrado."
                )
            );
        }
        return ResponseEntity.ok(transfer);
    }

    @PostMapping
    public ResponseEntity<?> createTransfer(@RequestBody @Valid TransferDTO transferDto) {

        Player player = playerService.getPlayerById(transferDto.getPlayerId());
        if (player == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("message", "El jugador con ID " + transferDto.getPlayerId() + " no existe.")
            );
        }

        Team fromTeam = null;
        if (transferDto.getFromTeamId() != null) {
            fromTeam = teamService.getTeamById(transferDto.getFromTeamId());
            if (fromTeam == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("message", "El equipo origen con ID " + transferDto.getFromTeamId() + " no existe.")
                );
            }
        }

        Team toTeam = teamService.getTeamById(transferDto.getToTeamId());
        if (toTeam == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("message", "El equipo destino con ID " + transferDto.getToTeamId() + " no existe.")
            );
        }

        // Validar duplicado sin usar id
        if (transferService.existsByPlayerAndFromTeamAndToTeamAndDateTransfer(player, fromTeam, toTeam, transferDto.getDateTransfer())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 409,
                    "error", "Conflict",
                    "message", "Ya existe un traspaso con los mismos datos: jugador, equipos y fecha."
                )
            );
        }

        Transfer transfer = new Transfer();
        transfer.setPlayer(player);
        transfer.setFromTeam(fromTeam);
        transfer.setToTeam(toTeam);
        transfer.setDateTransfer(transferDto.getDateTransfer());
        transfer.setFee(transferDto.getFee());

        Transfer saved = transferService.saveTransfer(transfer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransfer(@PathVariable Long id, @RequestBody @Valid TransferDTO transferDto) {
        Transfer existing = transferService.getTransferById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "No se encontró el Transfer con ID " + id
                )
            );
        }

        // Validar player existe
        Player player = playerService.getPlayerById(transferDto.getPlayerId());
        if (player == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                    "message", "El jugador con ID " + transferDto.getPlayerId() + " no existe."
                )
            );
        }

        // Validar equipos existen
        Team fromTeam = null;
        if (transferDto.getFromTeamId() != null) {
            fromTeam = teamService.getTeamById(transferDto.getFromTeamId());
            if (fromTeam == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                        "message", "El equipo origen con ID " + transferDto.getFromTeamId() + " no existe."
                    )
                );
            }
        }

        Team toTeam = teamService.getTeamById(transferDto.getToTeamId());
        if (toTeam == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                    "message", "El equipo destino con ID " + transferDto.getToTeamId() + " no existe."
                )
            );
        }

        // Actualizar datos
        existing.setPlayer(player);
        existing.setFromTeam(fromTeam);
        existing.setToTeam(toTeam);
        existing.setDateTransfer(transferDto.getDateTransfer());
        existing.setFee(transferDto.getFee());

        Transfer updated = transferService.saveTransfer(existing);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransfer(@PathVariable Long id) {
        Transfer existing = transferService.getTransferById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 404,
                    "error", "Not Found",
                    "message", "No se encontró el Transfer con ID " + id
                )
            );
        }
        transferService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }
}
