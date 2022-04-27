package com.bank.backend.repository;

import com.bank.backend.entity.Purchase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
    
    Page<Purchase> findAll(Pageable page);

    Page<Purchase> findByAliasContainingOrPurchaseNameContainingAllIgnoreCase(String purchaseName, String alias, Pageable page);
    default Page<Purchase> findByAllCategories (String all, Pageable page){
        return findByAliasContainingOrPurchaseNameContainingAllIgnoreCase(all, all, page);
    }

}
