package com.bank.backend.repository;

import com.bank.backend.entity.Deposit;
import com.bank.backend.entity.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    // Select ALl Categories
    @Query(value = "SELECT d.DEPOSIT_ID,u.UNIVERSITY_ID,b.BANK_ID,p.PERIOD_ID,d.DEPOSIT_NAME,b.CODE" +
            "b.BANK_NAME,d.ACCOUNT_NUMBER,p.PERIOD,us.USER_ID,s.STATUS_ID FROM DEPOSIT d" +
            "LEFT JOIN UNIVERSITIES u ON u.UNIVERSITY_ID = d.UNIVERSITY_ID " +
            "LEFT JOIN BANKS b ON b.BANK_ID = d.BANK_ID " +
            "LEFT JOIN PERIODS p ON p.PERIOD_ID = d.PERIOD_ID " +
            "LEFT JOIN STATUSES us ON us.STATUS_ID = d.STATUS_ID " +
            "WHERE LOWER(d.DEPOSIT_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pDepositName),'%')) " +
            "OR LOWER(b.CODE) LIKE LOWER(CONCAT(CONCAT('%', :pCode),'%')) " +
            "OR LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName),'%')) " +
            "OR LOWER(d.ACCOUNT_NUMBER) LIKE LOWER(CONCAT(CONCAT('%', :pAccountNumber),'%')) " +
            "OR LOWER(p.PERIOD) LIKE LOWER(CONCAT(CONCAT('%', :pPeriod),'%'))", countQuery = "SELECT COUNT(d.DEPOSIT_ID,u.UNIVERSITY_ID,b.BANK_ID,p.PERIOD_ID,d.DEPOSIT_NAME,b.CODE"
                    +
                    "b.BANK_NAME,d.ACCOUNT_NUMBER,p.PERIOD,us.USER_ID,s.STATUS_ID) FROM DEPOSIT d" +
                    "LEFT JOIN UNIVERSITIES u ON u.UNIVERSITY_ID = d.UNIVERSITY_ID " +
                    "LEFT JOIN BANKS b ON b.BANK_ID = d.BANK_ID " +
                    "LEFT JOIN PERIODS p ON p.PERIOD_ID = d.PERIOD_ID " +
                    "LEFT JOIN STATUSES us ON us.STATUS_ID = d.STATUS_ID " +
                    "WHERE LOWER(d.DEPOSIT_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pDepositName),'%')) " +
                    "OR LOWER(b.CODE) LIKE LOWER(CONCAT(CONCAT('%', :pCode),'%')) " +
                    "OR LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName),'%')) " +
                    "OR LOWER(d.ACCOUNT_NUMBER) LIKE LOWER(CONCAT(CONCAT('%', :pAccountNumber),'%')) " +
                    "OR LOWER(p.PERIOD) LIKE LOWER(CONCAT(CONCAT('%', :pPeriod),'%')) " +
                    "ORDER BY DEPOSIT_ID ASC", nativeQuery = true)
    Page<Deposit> findByAllCategories(@Param("pDepositName") String depositName,
            @Param("pCode") String code, @Param("pBankName") String bankName,
            @Param("pAccountNumber") String accountNumber, @Param("pPeriod") String period, Pageable paging);

    default Page<Deposit> getByAllCategories(String all, Pageable paging) {
        return findByAllCategories(all, all, all, all, all, paging);
    }

    Page<Deposit> findByStatus(Status status, Pageable page);
}
