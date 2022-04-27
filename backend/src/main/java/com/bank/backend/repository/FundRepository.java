package com.bank.backend.repository;

import com.bank.backend.entity.Fund;

<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund,Long> {
    Page<Fund> findAll(Pageable paging);

    Page<Fund> findByAliasContainingOrFundNameContainingAllIgnoreCase(String alias,String fundName,Pageable page);
    default Page<Fund> findByAllCategories (String all, Pageable page){
        return findByAliasContainingOrFundNameContainingAllIgnoreCase(all, all, page);
}
=======
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund, Long>{
    
>>>>>>> abcd347c5cf9241014c7f5f5251d06779ec22518
}
