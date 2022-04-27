package com.bank.backend.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CONTAINERS")
public class Container {
    
    @Id
    @Column(name = "CONTAINER_ID")
    @GeneratedValue(generator = "CONTAINER_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CONTAINER_GEN", sequenceName = "CONTAINERS_SEQ", allocationSize = 1)
    
    private Long containerId;

    @ManyToOne
    @JoinColumn(name = "UNIVERSITY_ID")
    private University university;

    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "MUTATION_ID")
    private String mutationId;

    @Column(name = "TRANSACTION_DATE")
    @Temporal (TemporalType.DATE)
    private Date transactionDate;

    @Column(name = "VALUE")
    private Long value;

    @ManyToOne
    @JoinColumn(name = "PURCHASE_ID")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    private AccountType accountType;

    @OneToMany
    @JoinColumn(name = "FUND_ID")
    private List<Fund> fund;

    @Column(name = "DESCRIPTION")
    private String description;

}
