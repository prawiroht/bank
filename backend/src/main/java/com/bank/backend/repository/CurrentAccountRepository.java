package com.bank.backend.repository;

import java.util.Date;

import com.bank.backend.entity.CurrentAccount;
import com.bank.backend.entity.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {
    // Select ALl Categories
    @Query(value = "SELECT c.CURRENT_ACCOUNT_ID,c.BANK_ID,c.UNIVERSITY_ID,u.UNIVERSITY_NAME,b.BANK_NAME,b.CODE,c.ACCOUNT_NUMBER, "
            +
            "c.ACCOUNT_TYPE_ID,c.INITIAL_BALANCE_DATE,c.INITIAL_BALANCE_ACCOUNT,c.STATUS_ID,c.USER_ID " +
            "FROM CURRENT_ACCOUNTS c LEFT JOIN BANKS b " +
            "ON c.BANK_ID = b.BANK_ID " +
            "LEFT JOIN USERS us ON c.USER_ID = us.USER_ID " +
            "LEFT JOIN STATUSES s ON c.STATUS_ID = s.STATUS_ID " +
            "LEFT JOIN UNIVERSITIES u ON c.UNIVERSITY_ID = u.UNIVERSITY_ID " +
            "LEFT JOIN ACCOUNT_TYPES a ON c.ACCOUNT_TYPE_ID = a.ACCOUNT_TYPE_ID " +
            "WHERE LOWER(u.UNIVERSITY_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pUniversityName),'%')) OR " +
            "LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName),'%')) OR " +
            "LOWER(b.CODE) LIKE LOWER(CONCAT(CONCAT('%', :pCode),'%')) OR " +
            "LOWER(c.ACCOUNT_NUMBER) LIKE LOWER(CONCAT(CONCAT('%', :pAccountNumber), '%')) OR " +
            "LOWER(c.INITIAL_BALANCE_DATE) LIKE LOWER(CONCAT(CONCAT('%', :pInitialBalanceDate), '%')) OR " +
            "LOWER(c.INITIAL_BALANCE_ACCOUNT) LIKE LOWER(CONCAT(CONCAT('%', :pInitialBalanceAccount),'%'))", countQuery = "SELECT COUNT(*) "
                    +
                    "FROM CURRENT_ACCOUNTS c LEFT JOIN BANKS b " +
                    "ON c.BANK_ID = b.BANK_ID " +
                    "LEFT JOIN USERS us ON us.USER_ID = c.USER_ID" +
                    "LEFT JOIN STATUS s ON s.STATUS_ID = s.STATUS_ID " +
                    "LEFT JOIN UNIVERSITIES u ON u.UNIVERSITY_ID = c.UNIVERSITY_ID " +
                    "LEFT JOIN ACCOUNT_TYPES a ON a.ACCOUNT_TYPE_ID = c.ACCOUNT_TYPE_ID " +
                    "WHERE LOWER(u.UNIVERSITY_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pUniversityName),'%')) OR " +
                    "LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName),'%')) OR " +
                    "LOWER(b.CODE) LIKE LOWER(CONCAT(CONCAT('%', :pCode),'%')) OR " +
                    "LOWER(c.ACCOUNT_NUMBER) LIKE LOWER(CONCAT(CONCAT('%', :pAccountNumber), '%')) OR " +
                    "LOWER(c.INITIAL_BALANCE_DATE) LIKE LOWER(CONCAT(CONCAT('%', :pInitialBalanceDate), '%')) OR " +
                    "LOWER(c.INITIAL_BALANCE_ACCOUNT) LIKE LOWER(CONCAT(CONCAT('%', :pInitialBalanceAccount),'%')) " +
                    "ORDER BY CURRENT_ACCOUNT_ID ASC", nativeQuery = true)
    Page<CurrentAccount> findByAllCategories(@Param("pUniversityName") String universityName,
            @Param("pBankName") String bankName, @Param("pCode") String code,
            @Param("pAccountNumber") String accountNumber,
            @Param("pInitialBalanceDate") String initialBalanceDate,
            @Param("pInitialBalanceAccount") String initialBalanceAccount, Pageable paging);

    default Page<CurrentAccount> getByAllCategories(String all, Pageable paging) {
        return findByAllCategories(all, all, all, all, all, all, paging);
    }

    Page<CurrentAccount> findByStatus(Status status, Pageable page);

    @Query("SELECT sum(initialBalanceAccount) FROM CurrentAccount where status.status_id = 2")
    public Long sumNominalWithStatusApprove();

    @Query("SELECT sum(nominal) FROM CurrentAccount where status.statusId = 2 AND ((startDate BETWEEN :pStartDate AND :pEndDate) OR (dueDate BETWEEN :pStartDate AND :pEndDate)) AND bank.bankId = :pBankId")
    public Long sumNominalWithStatusApproveAndParam(@Param("pStartDate") Date startDate,
            @Param("pEndDate") Date endDate, @Param("pBankId") Long bankId);
}
