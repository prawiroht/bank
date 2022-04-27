package com.bank.backend.repository;

import com.bank.backend.entity.AccountType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    
    Page<AccountType> findAll(Pageable page);

    Page<AccountType> findByAccountTypeNameContainingAllIgnoreCase(String accountName, Pageable page);
    default Page<AccountType> findByAllCategories (String all, Pageable page){
        return findByAccountTypeNameContainingAllIgnoreCase(all, page);
    }
}
