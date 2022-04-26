package com.bank.backend.repository;

import com.bank.backend.entity.AccountType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

}
