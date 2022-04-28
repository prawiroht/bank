package com.bank.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
    @Id
    @GeneratedValue(generator = "TRANSACTION_GEN",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "TRANSACTION_GEN",sequenceName = "TRANSACTION_SEQ",allocationSize = 1)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
    @Column(name = "NAME")
    private String name;
}
