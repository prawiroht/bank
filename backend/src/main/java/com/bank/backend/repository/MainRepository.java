package com.bank.backend.repository;


import com.bank.backend.entity.Main;
import com.bank.backend.entity.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MainRepository extends JpaRepository<Main, Long>{
    Page<Main> findAll(Pageable paging);
    
    @Query(value = "SELECT m.MAIN_ID, m.UNIVERSITY_ID, m.BANK_ID, m.TRANSACTION_DATE,"+ 
                        "m.PURCHASE_ID, m.ACCOUNT_TYPE_ID, m.ACCOUNT_NUMBER, m.MUTATION_ID,"+ 
                        "m.FUND_ID, m.VALUE, m.USER_ID, m.STATUS_ID,m.DESCRIPTION FROM MAINS m " +
                        "LEFT JOIN BANKS b ON b.BANK_ID = m.BANK_ID " +
                        "LEFT JOIN PURCHASES p ON p.PURCHASE_ID = m.PURCHASE_ID " +
                        "LEFT JOIN ACCOUNT_TYPES a ON a.ACCOUNT_TYPE_ID = m.ACCOUNT_TYPE_ID "+
                        "LEFT JOIN FUNDS f ON f.FUND_ID = m.FUND_ID "+
                        "LEFT JOIN STATUSES s ON s.STATUS_ID = m.STATUS_ID "+
                        "LEFT JOIN USERS ui ON ui.USER_ID = m.USER_ID "+
                        "WHERE LOWER(b.CODE) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pCode), '%')) OR LOWER(b.BANK_NAME) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pBankName), '%')) OR LOWER(p.PURCHASE_NAME) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pPurchaseName), '%')) OR LOWER(a.ACCOUNT_TYPE_NAME) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pAccountTypeName), '%')) OR LOWER(m.ACCOUNT_NUMBER) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pAccountNumber), '%')) OR LOWER(m.MUTATION_ID) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pMutationId), '%')) OR LOWER(f.FUND_NAME) "+
                        "LIKE LOWER(CONCAT(CONCAT('%',:pFundName),'%'))",
                        countQuery = "SELECT COUNT(*) FROM MAINS m " +
                        "LEFT JOIN BANKS b ON b.BANK_ID = m.BANK_ID " +
                        "LEFT JOIN PURCHASES p ON p.PURCHASE_ID = m.PURCHASE_ID " +
                        "LEFT JOIN ACCOUNT_TYPES a ON a.ACCOUNT_TYPE_ID = m.ACCOUNT_TYPE_ID "+
                        "LEFT JOIN FUNDS f ON f.FUND_ID = m.FUND_ID "+
                        "LEFT JOIN STATUSES s ON s.STATUS_ID = m.STATUS_ID "+
                        "LEFT JOIN USERS ui ON ui.USER_ID = m.USER_ID "+
                        "WHERE LOWER(b.CODE) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pCode), '%')) OR LOWER(b.BANK_NAME) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pBankName), '%')) OR LOWER(p.PURCHASE_NAME) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pPurchaseName), '%')) OR LOWER(a.ACCOUNT_TYPE_NAME) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pAccountTypeName), '%')) OR LOWER(m.ACCOUNT_NUMBER) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pAccountNumber), '%')) OR LOWER(m.MUTATION_ID) " +
                        "LIKE LOWER(CONCAT(CONCAT('%',:pMutationId), '%')) OR LOWER(f.FUND_NAME) "+
                        "LIKE LOWER(CONCAT(CONCAT('%',:pFundName),'%'))", nativeQuery = true)
        Page<Main> getByAllCategorie(@Param("pCode") String code,
                        @Param("pBankName") String bankName,
                        @Param("pPurchaseName") String purchaseName,
                        @Param("pAccountTypeName") String accountTypeName,
                        @Param("pAccountNumber") String accountNumber,
                        @Param("pMutationId") String mutationId, 
                        @Param("pFundName") String fundName,
                        Pageable page);

        default Page<Main> getByAllCategories(String all, Pageable page) {
                return getByAllCategorie(all, all, all, all, all, all, all, page);
        }

        Page<Main> findByStatus(Status status,Pageable page);
    

}
