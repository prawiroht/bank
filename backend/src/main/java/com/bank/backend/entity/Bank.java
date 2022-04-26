package com.bank.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BANKS")
public class Bank {
    
    @Id
    @Column(name = "BANK_ID")
    private String bankId;
    @Column(name = "BANK_NAME")
    private String bankName;

}
