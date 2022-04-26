package com.bank.backend.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupMenuWrapper {
    private Long groupMenuId;
    private Long groupId;
    private String groupName;
    private Long menuId;
    private String menuName;
    private Character isActive;
}
