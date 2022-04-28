package com.bank.backend.repository;

import com.bank.backend.entity.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long>{
    
}
