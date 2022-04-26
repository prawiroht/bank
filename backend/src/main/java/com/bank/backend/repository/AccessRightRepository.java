package com.bank.backend.repository;

import com.bank.backend.entity.AccessRight;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRightRepository extends JpaRepository<AccessRight,Long>{
    
}
