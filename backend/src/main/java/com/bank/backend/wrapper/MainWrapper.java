package com.bank.backend.wrapper;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainWrapper {
    private Long mainId;
    private Long universityId;
    private String universityName;
    private Long bankId;
    private String code;
    private String bankName;
    private String accountNumber;
    private String mutationId;
    private Date transactionDate;
    private Long value;
    private Long purchaseId;
    private String purchaseName;
    private Long accountTypeId;
    private String accountTypeName;
    private Long fundId;
    private String fundName;
    private String description;
    private Long userId;
    private String username;
}
