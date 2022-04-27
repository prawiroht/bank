package com.bank.backend.repository;

import com.bank.backend.entity.Fund;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund, Long>{
    
}
