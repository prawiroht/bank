package com.bank.backend.wrapper;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrentAccountWrapper {
    private Long currentAccountId;
    private Long universityId;
    private String universityName;
    private Long bankId;
    private String code;
    private String bankName;
    private String accountNumber;
    private Long accountTypeId;
    private String accountTypeName;
    private Date initialBalanceDate;
    private Long initialBalanceAccount;
    private Long statusId;
    private String statusName;
    private Long userId;
    private String userName;
}
