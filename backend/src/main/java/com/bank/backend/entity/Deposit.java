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
@Table(name = "DEPOSITS")
public class Deposit {
    @Id
    @GeneratedValue(generator = "DEPOSIT_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "DEPOSIT_GEN", sequenceName = "DEPOSIT_SEQ", allocationSize = 1)
    @Column(name = "DEPOSIT_ID")
    private Long depositId;
    @ManyToOne
    @JoinColumn(name = "UNIVERSITY_ID")
    private University university;
    @Column(name = "DEPOSIT_NAME")
    private String depositName;
    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @ManyToOne
    @JoinColumn(name = "PERIOD_ID")
    private Period period;
    @Column(name = "NOMINAL")
    private Long nominal;
    @Column(name = "INTEREST")
    private Long interest;
    @Column(name = "EARNING_INTEREST")
    private Long earningInterest;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
