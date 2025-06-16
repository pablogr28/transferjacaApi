package com.transferjacaAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transferjacaAPI.model.Team;
import com.transferjacaAPI.repository.TeamRepository;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
    
    public boolean existsByNameAndCountryAndYearFundation(String name, String country, Integer yearFundation) {
        return teamRepository.existsByNameAndCountryAndYearFundation(name, country, yearFundation);
    }

}
