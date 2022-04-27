package com.bank.backend.repository;

import java.util.List;

import com.bank.backend.entity.Main;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepository extends JpaRepository<Main, Long>{    
    List<Main> findByBank(String bankName);

}
