package com.bank.backend.wrapper;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvestationWrapper {
    private Long investationId;
    private Long universityId;
    private String investationName;
    private Long initialNAB;
    private Long initialUnit;
    private Long initialValue;
    private Long marketNAB;
    private Long marketUnit;
    private Long marketValue;
    private Date startDate;
    private Long bankId;
    private String bankName;
    private Long statusId;
    private String statusName;
    private Long userId;
    private String userName;
}
