package com.bank.backend.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountType {
    @Id
    @GeneratedValue(generator = "ACCOUNT_TYPE_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ACCOUNT_TYPE_GEN", sequenceName = "ACCOUNT_TYPE_SEQ", allocationSize = 1)
    private Long AccountTypeId;
    @Column(name = "ACCOUNT_NAME")
    private String accountName;
}