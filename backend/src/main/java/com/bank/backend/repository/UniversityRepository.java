package com.bank.backend.repository;

import com.bank.backend.entity.University;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {

    Page<University> findAll(Pageable page);

    Page<University> findByUniversityNameContainingIgnoreCase(String universityName, Pageable page);
    default Page<University> findByAllCategories (String all, Pageable page){
        return findByUniversityNameContainingIgnoreCase(all, page);
    }
}
