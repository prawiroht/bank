package com.bank.backend.repository;

import com.bank.backend.entity.Receiving;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivingRepository extends JpaRepository<Receiving, Long>{
    
}
