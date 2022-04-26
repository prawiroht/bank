package com.bank.backend.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccessRightWrapper {
    private Long accessRightId;
    private Long userId;
    private String username;
    private Long groupId;
    private String groupName;
    private Character isActive;
}
