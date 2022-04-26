package com.bank.backend.wrapper;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrentAccountWrapper {
    private Long currentAccountId;
    private Long universityId;
    private String bankId;
    private String bankName;
    private Long depositId;
    private Long accountNumber;
    private Long accountTypeId;
    private String accountName;
    private Date initialBalanceDate;
    private Long initialBalanceAccount;
}
