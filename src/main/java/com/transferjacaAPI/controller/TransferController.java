package com.transferjacaAPI.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.transferjacaAPI.model.Transfer;
import com.transferjacaAPI.service.TransferService;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

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
    public ResponseEntity<?> createTransfer(@RequestBody Transfer transfer) {
        Transfer saved = transferService.saveTransfer(transfer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransfer(@PathVariable Long id, @RequestBody Transfer transfer) {
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
        transfer.setId(id);
        return ResponseEntity.ok(transferService.saveTransfer(transfer));
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
