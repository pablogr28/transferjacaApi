package com.transferjacaAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transferjacaAPI.model.PlayerTournament;
import com.transferjacaAPI.model.PlayerTournamentId;
import com.transferjacaAPI.repository.PlayerTournamentRepository;

@Service
public class PlayerTournamentService {

    private final PlayerTournamentRepository playerTournamentRepository;

    @Autowired
    public PlayerTournamentService(PlayerTournamentRepository playerTournamentRepository) {
        this.playerTournamentRepository = playerTournamentRepository;
    }

    public List<PlayerTournament> getAllPlayerTournaments() {
        return playerTournamentRepository.findAll();
    }

    public PlayerTournament getPlayerTournamentById(PlayerTournamentId id) {
        return playerTournamentRepository.findById(id).orElse(null);
    }

    public PlayerTournament savePlayerTournament(PlayerTournament playerTournament) {
        return playerTournamentRepository.save(playerTournament);
    }

    public void deletePlayerTournament(PlayerTournamentId id) {
        playerTournamentRepository.deleteById(id);
    }
}
