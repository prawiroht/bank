package com.bank.backend.repository;

import java.util.List;

import com.bank.backend.entity.Expenditure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long>{
    Page<Expenditure> findAll(Pageable page);
    
    List<Expenditure> findByBank(String bankId);

}
