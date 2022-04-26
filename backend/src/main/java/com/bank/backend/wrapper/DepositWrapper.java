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
    private String bankId;
    private String bankName;
    private Long accountNumber;
    private Long periodId;
    private String period;
    private Long nominal;
    private Long interest;
    private Long earningInterest;
    private Date startDate;
    private Date dueDate;
}
