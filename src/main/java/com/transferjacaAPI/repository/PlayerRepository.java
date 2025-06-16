package com.transferjacaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferjacaAPI.model.Player;

public interface PlayerRepository extends JpaRepository<Player,Long>{

}
