package com.bank.backend.repository;

import java.util.List;

import com.bank.backend.entity.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, String> {
    Page<Bank> findAllPagination(Pageable page);

    List<Bank> findByBankName(String bankName);

    Page<Bank> findByBankNamePagination(String bankName, Pageable page);
}
