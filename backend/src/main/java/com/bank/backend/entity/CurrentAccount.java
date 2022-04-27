package com.bank.backend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CURRENT_ACCOUNTS")
public class CurrentAccount {
    @Id
    @GeneratedValue(generator = "CURRENT_ACCOUNT_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CURRENT_ACCOUNT_GEN", sequenceName = "CURACC_SEQ", allocationSize = 1)
    @Column(name = "CURRENT_ACCOUNT_ID")
    private Long currentAccountId;
    @ManyToOne
    @JoinColumn(name = "UNIVERSITY_ID")
    private University university;
    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    private AccountType accountType;
    @Column(name = "INITIAL_BALANCE_DATE")
    @Temporal(TemporalType.DATE)
    private Date initialBalanceDate;
    @Column(name = "INITIAL_BALANCE_ACCOUNT")
    private Long initialBalanceAccount;
    @Column(name = "STATUS")
    private String status;
}
