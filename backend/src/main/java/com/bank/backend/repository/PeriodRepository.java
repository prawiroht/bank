package com.bank.backend.repository;

import com.bank.backend.entity.Period;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepository extends JpaRepository<Period, Long> {

}
