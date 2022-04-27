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
    private Long initialSaving;
    private Long initialUnit;
    private Long initialValue;
    private Long marketSaving;
    private Long marketUnit;
    private Long marketValue;
    private Date startDate;
    private String status;
}
