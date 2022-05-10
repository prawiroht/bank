package com.bank.backend.repository;

import com.bank.backend.entity.Mutation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MutationRepository extends JpaRepository<Mutation, String>{
    
}
