package com.transferjacaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferjacaAPI.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer,Long>{

}
