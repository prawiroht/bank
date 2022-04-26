package com.bank.backend.repository;

import com.bank.backend.entity.Investation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestationRepository extends JpaRepository<Investation, Long> {
    Page<Investation> findAll(Pageable page);
}
