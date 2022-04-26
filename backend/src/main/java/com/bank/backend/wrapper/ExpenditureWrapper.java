package com.bank.backend.wrapper;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpenditureWrapper {
    private Long expenditureId;
    private String bankId;
    private String bankName;
    private Long universityId;
    private String universityName;
    private String accountNumber;
    private String mutationId;
    private Date transactionDate;
    private Long value;
    private Long purchaseId;
    private String purchaseName;
    private Long accountId;
    private String accountName;
    private Long fundId;
    private String fundName;
    private String description;
}
