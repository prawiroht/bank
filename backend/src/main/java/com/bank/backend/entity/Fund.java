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
@Table(name = "FUNDS")
public class Fund {

    @Id
    @Column(name = "FUND_ID")
    @GeneratedValue(generator = "FUND_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "FUND_GEN", sequenceName = "FUNDS_SEQ", allocationSize = 1)
    private Long fundId;
    @Column(name = "ALIAS")
    private String alias;
    @Column(name = "FUND_NAME")
    private String fundName;
}
