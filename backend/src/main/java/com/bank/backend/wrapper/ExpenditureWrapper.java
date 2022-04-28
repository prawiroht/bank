package com.bank.backend.wrapper;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpenditureWrapper {
    private Long expenditureId;
    private Long bankId;
    private String code;
    private String bankName;
    private Long universityId;
    private String universityName;
    private String accountNumber;
    private String mutationId;
    private Date transactionDate;
    private Long value;
    private Long purchaseId;
    private String purchaseAlias;
    private String purchaseName;
    private Long accountTypeId;
    private String accountTypeName;
    private Long fundId;
    private String fundAlias;
    private String fundName;
    private String description;
    private Long statusId;
    private String statusName;
    private Long userId;
    private String userName;
}
