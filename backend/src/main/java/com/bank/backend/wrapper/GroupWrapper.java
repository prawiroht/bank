package com.bank.backend.wrapper;

import java.util.List;

import com.bank.backend.entity.Menu;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupWrapper {
    private Long groupId;
    private String name;
    private String description;
    private List<Menu> menus;
}
