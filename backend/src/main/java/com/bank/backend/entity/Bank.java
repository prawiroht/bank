package com.bank.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BANKS")
public class Bank {

    @Id
    @GeneratedValue(generator = "CURRENT_ACCOUNT_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CURRENT_ACCOUNT_GEN", sequenceName = "BANK_SEQ", allocationSize = 1)
    @Column(name = "BANK_ID")
    private Long bankId;
    @Column(name = "CODE")
    private String code;
    @Column(name = "BANK_NAME")
    private String bankName;

}
