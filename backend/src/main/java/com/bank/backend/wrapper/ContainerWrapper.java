package com.bank.backend.wrapper;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContainerWrapper {
    
    private Long containerId;
    private Long universityId;
    private Long bankId;
    private String accountNumber;
    private String mutationId;
    private Date transactionDate;
    private Long value;
    private Long purchaseId;
    private Long accountTypeId;
    private Long fundId;
    private String description;
    private Long statusId;
    private Long userId;
    private String universityName;
    private String bankName;
    private String purchaseName;
    private String accountTypeName;
    private String fundName;
    private String username;

}
