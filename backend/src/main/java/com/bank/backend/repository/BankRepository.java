package com.bank.backend.repository;

import com.bank.backend.entity.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Page<Bank> findAll(Pageable page);

    @Query (value =
            "SELECT b.BANK_ID, b.CODE, b.BANK_NAME FROM BANKS b "
            + "WHERE LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName), '%')) ", nativeQuery = true)
    Page<Bank> findByBankNameContainingAllIgnoreCasePagination(@Param("pBankName") String bankName, Pageable page);

    Page<Bank> findByBankName(String bankName, Pageable page);
}
