package com.bank.backend.repository;

import com.bank.backend.entity.Deposit;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Page<Deposit> findAll(Pageable pageable);
}
