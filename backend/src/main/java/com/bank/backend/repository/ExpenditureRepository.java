package com.bank.backend.repository;

import java.util.Date;

import com.bank.backend.entity.Expenditure;
import com.bank.backend.entity.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long>{
    Page<Expenditure> findAll(Pageable page);
    
    @Query (value =
    "SELECT e.EXPENDITURE_ID, e.BANK_ID, b.BANK_NAME, e.UNIVERSITY_ID, u.UNIVERSITY_NAME, e.ACCOUNT_NUMBER, "+ 
    "e.MUTATION_ID, e.TRANSACTION_DATE, e.VALUE, e.PURCHASE_ID, p.PURCHASE_NAME, e.ACCOUNT_TYPE_ID, at.ACCOUNT_TYPE_NAME, "+
    "e.FUND_ID, f.FUND_NAME, e.DESCRIPTION, e.STATUS_ID, s.STATUS_NAME, e.USER_ID, us.USERNAME FROM EXPENDITURES e "+
    "LEFT JOIN BANKS b ON e.BANK_ID = b.BANK_ID "+
    "LEFT JOIN UNIVERSITIES u ON e.UNIVERSITY_ID = u.UNIVERSITY_ID "+
    "LEFT JOIN PURCHASES p ON e.PURCHASE_ID = p.PURCHASE_ID "+
    "LEFT JOIN ACCOUNT_TYPES at ON e.ACCOUNT_TYPE_ID = at.ACCOUNT_TYPE_ID "+
    "LEFT JOIN FUNDS f ON e.FUND_ID = f.FUND_ID "+
    "LEFT JOIN STATUSES s ON e.STATUS_ID = s.STATUS_ID "+
    "LEFT JOIN USERS us ON e.USER_ID = us.USER_ID "+
    "WHERE LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName), '%')) OR "+
    "LOWER(u.UNIVERSITY_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pUniversityName), '%')) OR "+
    "LOWER(e.ACCOUNT_NUMBER) LIKE LOWER(CONCAT(CONCAT('%', :pAccountNumber), '%')) OR "+
    "LOWER(e.MUTATION_ID) LIKE LOWER(CONCAT(CONCAT('%', :pMutationId), '%')) OR "+
    "LOWER(p.PURCHASE_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pPurchaseName), '%')) OR "+
    "LOWER(at.ACCOUNT_TYPE_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pAccountTypeName), '%')) OR "+
    "LOWER(f.FUND_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pFundName), '%')) OR "+
    "LOWER(e.DESCRIPTION) LIKE LOWER(CONCAT(CONCAT('%', :pDescription), '%'))",
    countQuery = "SELECT COUNT(*) FROM EXPENDITURES e "+
    "LEFT JOIN BANKS b ON e.BANK_ID = b.BANK_ID "+
    "LEFT JOIN UNIVERSITIES u ON e.UNIVERSITY_ID = u.UNIVERSITY_ID "+
    "LEFT JOIN PURCHASES p ON e.PURCHASE_ID = p.PURCHASE_ID "+
    "LEFT JOIN ACCOUNT_TYPES at ON e.ACCOUNT_TYPE_ID = at.ACCOUNT_TYPE_ID "+
    "LEFT JOIN FUNDS f ON e.FUND_ID = f.FUND_ID "+
    "LEFT JOIN STATUSES s ON e.STATUS_ID = s.STATUS_ID "+
    "LEFT JOIN USERS us ON e.USER_ID = us.USER_ID "+
    "WHERE LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName), '%')) OR "+
    "LOWER(u.UNIVERSITY_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pUniversityName), '%')) OR "+
    "LOWER(e.ACCOUNT_NUMBER) LIKE LOWER(CONCAT(CONCAT('%', :pAccountNumber), '%')) OR "+
    "LOWER(e.MUTATION_ID) LIKE LOWER(CONCAT(CONCAT('%', :pMutationId), '%')) OR "+
    "LOWER(p.PURCHASE_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pPurchaseName), '%')) OR "+
    "LOWER(at.ACCOUNT_TYPE_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pAccountTypeName), '%')) OR "+
    "LOWER(f.FUND_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pFundName), '%')) OR "+
    "LOWER(e.DESCRIPTION) LIKE LOWER(CONCAT(CONCAT('%', :pDescription), '%'))", nativeQuery = true
    )
    Page<Expenditure> findByAllCategoriesWithIndexedQueryParam(
        @Param("pBankName") String bankName,
        @Param("pUniversityName") String universityName,
        @Param("pAccountNumber") String accountNumber,
        @Param("pMutationId") String mutationId,
        @Param("pPurchaseName") String purchaseName,
        @Param("pAccountTypeName") String accountTypeName,
        @Param("pFundName") String fundName,
        @Param("pDescription") String description,
        Pageable page);
    default Page<Expenditure> findByAllCategories (String all, Pageable page){
        return findByAllCategoriesWithIndexedQueryParam(
            all, all, all, all, all, all, all, all, page);
    }

    // @Query (value = 
    // "SELECT e.EXPENDITURE_ID, e.BANK_ID, e.UNIVERSITY_ID, e.ACCOUNT_NUMBER, "+ 
    // "e.MUTATION_ID, e.TRANSACTION_DATE, e.VALUE, e.PURCHASE_ID, e.ACCOUNT_TYPE_ID, "+
    // "e.FUND_ID, e.DESCRIPTION, e.STATUS_ID, e.USER_ID FROM EXPENDITURES e "+
    // "WHERE e.STATUS_ID = 1", nativeQuery = true)
    // Page<Expenditure> findAllWithRequestStatus(Pageable page);

    Page<Expenditure> findByStatus(Status status, Pageable page);

    @Query("SELECT sum(value) from Expenditure where status.statusId = 2 and (transactionDate BETWEEN :pStartDate AND :pEndDate) and bank.bankId = :pBankId")
    public Long sumValueWithStatusApprovedAndParam(@Param("pStartDate") Date startDate, @Param("pEndDate") Date endDate, @Param("pBankId") Long bankId);
}
