package com.bank.backend.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InboxWrapper {
    private Long inboxId;
    private Long userId;
    private String username;
    private String message;
    private Long transactionId;
    private String transactionName;
}
