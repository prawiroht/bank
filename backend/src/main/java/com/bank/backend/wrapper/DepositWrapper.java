package com.bank.backend.wrapper;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepositWrapper {
    private Long depositId;
    private Long universityId;
    private String depositName;
    private Long bankId;
    private String code;
    private String bankName;
    private String accountNumber;
    private Long periodId;
    private String period;
    private Long nominal;
    private Long interest;
    private Long earningInterest;
    private Date startDate;
    private Date dueDate;
    private Long statusId;
    private String statusName;
    private Long userId;
    private String userName;
}
