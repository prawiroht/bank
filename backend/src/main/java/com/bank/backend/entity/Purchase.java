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
@Table(name = "PURCHASES")
public class Purchase {
    @Id
    @Column(name = "PURCHASE_ID")
    @GeneratedValue(generator = "PURCHASES_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PURCHASES_GEN", sequenceName = "PURCHASES_SEQ", allocationSize = 1, initialValue = 1)
    private Long purchaseId;
    @Column(name = "ALIAS")
    private String alias;
    @Column(name = "PURCHASE_NAME")
    private String purchaseName;

}
