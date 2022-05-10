package com.bank.backend.repository;

import java.util.Date;

import com.bank.backend.entity.Investation;
import com.bank.backend.entity.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvestationRepository extends JpaRepository<Investation, Long> {
    @Query(value = "SELECT i.INVESTATION_ID,i.BANK_ID,i.STATUS_ID,b.BANK_NAME,i.INVESTATION_NAME, " +
            "s.STATUS_NAME,i.USER_ID,i.INITIAL_NAB,i.INITIAL_UNIT,i.INITIAL_VALUE,i.MARKET_NAB, " +
            "i.MARKET_UNIT,i.MARKET_VALUE,i.UNIVERSITY_ID,i.START_DATE FROM INVESTATIONS i JOIN BANKS b " +
            "ON i.BANK_ID = b.BANK_ID JOIN STATUSES s ON i.STATUS_ID = s.STATUS_ID " +
            "JOIN USERS us ON i.USER_ID = us.USER_ID JOIN UNIVERSITIES u ON i.UNIVERSITY_ID = u.UNIVERSITY_ID " +
            "WHERE LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName),'%')) " +
            "OR LOWER(i.INVESTATION_NAME)  LIKE LOWER(CONCAT(CONCAT('%',:pInvestationName),'%')) " +
            "OR LOWER(s.STATUS_NAME) LIKE LOWER(CONCAT(CONCAT('%',:pStatusName),'%'))", countQuery = "SELECT count(*) FROM INVESTATIONS i JOIN BANKS b "
                    +
                    "ON b.BANK_ID = i.BANK_ID JOIN STATUSES s ON s.STATUS_ID = i.STATUS_ID " +
                    "JOIN USERS us ON us.USER_ID = i.USER_ID JOIN UNIVERSITIES u ON u.UNIVERSITY_ID = i.UNIVERSITY_ID "
                    +
                    "WHERE LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName),'%')) " +
                    "OR LOWER(i.INVESTATION_NAME)  LIKE LOWER(CONCAT(CONCAT('%',:pInvestationName),'%')) " +
                    "OR LOWER(s.STATUS_NAME) LIKE LOWER(CONCAT(CONCAT('%',:pStatusName),'%')) " +
                    "ORDER BY INVESTATION_ID ASC", nativeQuery = true)
    Page<Investation> findByAllCategories(@Param("pBankName") String bankName,
            @Param("pInvestationName") String investationName, @Param("pStatusName") String statusName,
            Pageable paging);

    default Page<Investation> getByAllCategories(String all, Pageable paging) {
        return findByAllCategories(all, all, all, paging);
    }

    Page<Investation> findByStatus(Status status, Pageable page);

    @Query("SELECT sum(initialValue) FROM Investation where status.statusId = 2")
    public Long sumNominalWithStatusApprove();

    @Query("SELECT sum(initialValue) FROM Investation where status.statusId = 2 AND (startDate BETWEEN :pStartDate AND :pEndDate) AND bank.bankId = :pBankId")

    public Long sumNominalWithStatusApproveAndParam(@Param("pStartDate") Date startDate,
            @Param("pEndDate") Date endDate, @Param("pBankId") Long bankId);

}
