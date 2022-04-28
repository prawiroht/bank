package com.bank.backend.repository;

import java.util.List;

import com.bank.backend.entity.Container;
import com.bank.backend.entity.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContainerRepository extends JpaRepository<Container, Long>{
    
    Page<Container> findAll(Pageable page);

    @Query (value =
    "SELECT c.CONTAINER_ID, c.UNIVERSITY_ID, u.UNIVERSITY_NAME, c.BANK_ID, b.BANK_NAME, c.ACCOUNT_NUMBER, "+ 
    "c.MUTATION_ID, c.TRANSACTION_DATE, c.VALUE, c.PURCHASE_ID, p.PURCHASE_NAME, c.ACCOUNT_TYPE_ID, at.ACCOUNT_TYPE_NAME, "+
    "c.FUND_ID, f.FUND_NAME, c.DESCRIPTION, c.USER_ID, us.USERNAME, c.STATUS_ID, s.STATUS_NAME FROM CONTAINERS c "+
    "LEFT JOIN UNIVERSITIES u ON c.UNIVERSITY_ID = u.UNIVERSITY_ID "+
    "LEFT JOIN BANKS b ON c.BANK_ID = b.BANK_ID "+
    "LEFT JOIN PURCHASES p ON c.PURCHASE_ID = p.PURCHASE_ID "+
    "LEFT JOIN ACCOUNT_TYPES at ON c.ACCOUNT_TYPE_ID = at.ACCOUNT_TYPE_ID "+
    "LEFT JOIN FUNDS f ON c.FUND_ID = f.FUND_ID "+
    "LEFT JOIN USERS us ON c.USER_ID = us.USER_ID "+
    "LEFT JOIN STATUSES s ON c.STATUS_ID = s.STATUS_ID "+
    "WHERE LOWER(u.UNIVERSITY_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pUniversityName), '%')) OR "+
    "LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName), '%')) OR "+
    "LOWER(c.ACCOUNT_NUMBER) LIKE LOWER(CONCAT(CONCAT('%', :pAccountNumber), '%')) OR "+
    "LOWER(c.MUTATION_ID) LIKE LOWER(CONCAT(CONCAT('%', :pMutationId), '%')) OR "+
    "LOWER(p.PURCHASE_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pPurchaseName), '%')) OR "+
    "LOWER(at.ACCOUNT_TYPE_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pAccountTypeName), '%')) OR "+
    "LOWER(f.FUND_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pFundName), '%')) OR "+
    "LOWER(c.DESCRIPTION) LIKE LOWER(CONCAT(CONCAT('%', :pDescription), '%')) OR "+
    "LOWER(us.USERNAME) LIKE LOWER(CONCAT(CONCAT('%', :pUsername), '%')) OR "+
    "LOWER(s.STATUS_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pStatusname), '%'))",
    countQuery = "SELECT c.CONTAINER_ID, c.UNIVERSITY_ID, u.UNIVERSITY_NAME, c.BANK_ID, b.BANK_NAME, c.ACCOUNT_NUMBER, "+ 
    "c.MUTATION_ID, c.TRANSACTION_DATE, c.VALUE, c.PURCHASE_ID, p.PURCHASE_NAME, c.ACCOUNT_TYPE_ID, at.ACCOUNT_TYPE_NAME, "+
    "c.FUND_ID, f.FUND_NAME, c.DESCRIPTION, c.USER_ID, us.USERNAME, c.STATUS_ID, s.STATUS_NAME FROM CONTAINERS c "+
    "LEFT JOIN UNIVERSITIES u ON c.UNIVERSITY_ID = u.UNIVERSITY_ID "+
    "LEFT JOIN BANKS b ON c.BANK_ID = b.BANK_ID "+
    "LEFT JOIN PURCHASES p ON c.PURCHASE_ID = p.PURCHASE_ID "+
    "LEFT JOIN ACCOUNT_TYPES at ON c.ACCOUNT_TYPE_ID = at.ACCOUNT_TYPE_ID "+
    "LEFT JOIN FUNDS f ON c.FUND_ID = f.FUND_ID "+
    "LEFT JOIN USERS us ON c.USER_ID = us.USER_ID "+
    "LEFT JOIN STATUSES s ON c.STATUS_ID = s.STATUS_ID "+
    "WHERE LOWER(u.UNIVERSITY_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pUniversityName), '%')) OR "+
    "LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName), '%')) OR "+
    "LOWER(c.ACCOUNT_NUMBER) LIKE LOWER(CONCAT(CONCAT('%', :pAccountNumber), '%')) OR "+
    "LOWER(c.MUTATION_ID) LIKE LOWER(CONCAT(CONCAT('%', :pMutationId), '%')) OR "+
    "LOWER(p.PURCHASE_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pPurchaseName), '%')) OR "+
    "LOWER(at.ACCOUNT_TYPE_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pAccountTypeName), '%')) OR "+
    "LOWER(f.FUND_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pFundName), '%')) OR "+
    "LOWER(c.DESCRIPTION) LIKE LOWER(CONCAT(CONCAT('%', :pDescription), '%')) OR "+
    "LOWER(us.USERNAME) LIKE LOWER(CONCAT(CONCAT('%', :pUsername), '%')) OR "+
    "LOWER(s.STATUS_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pStatusName), '%'))", nativeQuery = true
    )
    Page<Container> findByAllCategoriesWithIndexedQueryParam(
        @Param("pUniversityName") String universityName,
        @Param("pBankName") String bankName,
        @Param("pAccountNumber") String accountNumber,
        @Param("pMutationId") String mutationId,
        @Param("pPurchaseName") String purchaseName,
        @Param("pAccountTypeName") String accountTypeName,
        @Param("pFundName") String fundName,
        @Param("pDescription") String description, 
        @Param("pUsername") String userName,
        @Param("pStatusname") String statusName,
        Pageable page);
    default Page<Container> findByAllCategories (String all, Pageable page){
        return findByAllCategoriesWithIndexedQueryParam(
            all, all, all, all, all, all, all, all, all, all, page);
    }

    @Query(value = "SELECT * FROM CONTAINERS C WHERE LOWER(STATUS) = :pStatus", nativeQuery = true)
    Page<Container> findAllByStatus(@Param("pStatus") String status, Pageable page);    

    Page<Container> findByStatus(Status Status, Pageable page);
}
