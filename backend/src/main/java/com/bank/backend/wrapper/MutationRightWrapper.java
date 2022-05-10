package com.bank.backend.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MutationRightWrapper {
    private Long mutationRightId;
    private Long bankId;
    private String bankName;
    private String mutationId;
    private String mutationName;
    
}
