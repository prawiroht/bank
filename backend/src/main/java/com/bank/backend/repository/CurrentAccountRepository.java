package com.bank.backend.repository;

import com.bank.backend.entity.CurrentAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {

}
