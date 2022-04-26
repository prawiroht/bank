package com.bank.backend.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuWrapper {
    private Long menuId;
    private String name;
    private String icon;
    private String url;
}
