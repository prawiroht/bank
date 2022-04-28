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
@Table (name = "MAINS")
public class Main {
    @Id
    @Column(name = "MAIN_ID")
    @GeneratedValue (generator = "MAIN_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator (name = "MAIN_GEN", sequenceName = "MAIN_SEQ", allocationSize = 1, initialValue = 2)
    private Long mainId;

    @ManyToOne
    @JoinColumn (name = "UNIVERSITY_ID")
    private University university;

    @ManyToOne
    @JoinColumn (name = "BANK_ID")
    private Bank bank;

    @Column (name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column (name = "MUTATION_ID")
    private String mutationId;

    @Column (name = "TRANSACTION_DATE")
    @Temporal (TemporalType.DATE)
    private Date transactionDate;

    @Column (name = "VALUE")
    private Long value;

    @ManyToOne
    @JoinColumn (name = "PURCHASE_ID")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn (name = "ACCOUNT_TYPE_ID")
    private AccountType accountType;

    @ManyToOne
    @JoinColumn (name = "FUND_ID")
    private Fund fund;

    @Column (name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn (name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn (name = "STATUS_ID")
    private Status status;
}