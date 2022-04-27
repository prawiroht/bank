package com.bank.backend.wrapper;

import java.util.Calendar;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {
    private Long userId;
    private String username;
    private String password;
    private String name;
    private String address;
    private String email;
    private String phone;
    private Character isActive;
    private Calendar lastLogin;
    private List<GroupWrapper> groups;
}
