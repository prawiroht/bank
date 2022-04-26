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
    private String accountNumber;
    private Long accountTypeId;
    private String accountTypeName;
    private Date initialBalanceDate;
    private Long initialBalanceAccount;
}
