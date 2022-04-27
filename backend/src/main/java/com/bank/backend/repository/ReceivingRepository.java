package com.bank.backend.repository;

import com.bank.backend.entity.Receiving;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivingRepository extends JpaRepository<Receiving, Long>{
        
    Page<Receiving> findAll(Pageable page);

    Page<Receiving> findByReceivingNameContainingAllIgnoreCase(String receivingName, Pageable page);
    default Page<Receiving> findByAllCategories (String all, Pageable page){
        return findByReceivingNameContainingAllIgnoreCase(all, page);
    }
}
