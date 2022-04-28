package com.bank.backend.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionWrapper {
    private Long transactionId;
    private String name;
}
