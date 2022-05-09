package com.bank.backend.repository;

import java.util.Date;

import com.bank.backend.entity.Deposit;
import com.bank.backend.entity.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    // Select ALl Categories
    @Query(value = "SELECT d.DEPOSIT_ID,d.UNIVERSITY_ID,d.BANK_ID,d.PERIOD_ID,d.DEPOSIT_NAME,b.CODE, " +
            "b.BANK_NAME,d.ACCOUNT_NUMBER,p.PERIOD,d.USER_ID,d.STATUS_ID,d.NOMINAL,d.INTEREST,d.EARNING_INTEREST, " +
            "d.START_DATE,d.DUE_DATE FROM DEPOSITS d " +
            "LEFT JOIN UNIVERSITIES u ON d.UNIVERSITY_ID = u.UNIVERSITY_ID " +
            "LEFT JOIN BANKS b ON d.BANK_ID = b.BANK_ID " +
            "LEFT JOIN PERIODS p ON d.PERIOD_ID = p.PERIOD_ID " +
            "LEFT JOIN STATUSES s ON d.STATUS_ID = s.STATUS_ID " +
            "LEFT JOIN USERS us ON d.USER_ID = us.USER_ID " +
            "WHERE LOWER(d.DEPOSIT_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pDepositName),'%')) " +
            "OR LOWER(b.CODE) LIKE LOWER(CONCAT(CONCAT('%', :pCode),'%')) " +
            "OR LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName),'%')) " +
            "OR LOWER(d.ACCOUNT_NUMBER) LIKE LOWER(CONCAT(CONCAT('%', :pAccountNumber),'%')) " +
            "OR LOWER(p.PERIOD) LIKE LOWER(CONCAT(CONCAT('%', :pPeriod),'%'))", countQuery = "SELECT COUNT(*) FROM DEPOSITS d "
                    +
                    "LEFT JOIN UNIVERSITIES u ON u.UNIVERSITY_ID = d.UNIVERSITY_ID " +
                    "LEFT JOIN BANKS b ON b.BANK_ID = d.BANK_ID " +
                    "LEFT JOIN PERIODS p ON p.PERIOD_ID = d.PERIOD_ID " +
                    "LEFT JOIN STATUSES s ON s.STATUS_ID = d.STATUS_ID " +
                    "LEFT JOIN USERS us ON us.USER_ID = us.USER_ID " +
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

    @Query("SELECT sum(nominal) FROM Deposit where status.statusId = 2")
    public Long sumNominalWithStatusApprove();

    @Query("SELECT sum(nominal) FROM Deposit where status.statusId = 2 AND ((startDate BETWEEN :pStartDate AND :pEndDate) OR (dueDate BETWEEN :pStartDate AND :pEndDate)) AND bank.bankId = :pBankId")
    public Long sumNominalWithStatusApproveAndParam(@Param("pStartDate") Date startDate,
            @Param("pEndDate") Date endDate, @Param("pBankId") Long bankId);
}
