package com.bank.backend.repository;

import com.bank.backend.entity.Menu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long>{
    
}
