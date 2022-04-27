package com.bank.backend.wrapper;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContainerWrapper {
    
    private Long containerId;
    private Long universityId;
    private String bankId;
    private String accountNumber;
    private String mutationId;
    private Date transactionDate;
    private Long value;
    private String purchaseId;
    private Long accountTypeId;
    private String fundId;
    private String description;

}
