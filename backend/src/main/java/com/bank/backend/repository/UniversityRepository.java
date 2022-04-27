package com.bank.backend.repository;

import com.bank.backend.entity.University;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {

    Page<University> findAll(Pageable page);
}
