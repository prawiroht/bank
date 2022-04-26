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
@Table(name = "INVESTATIONS")
public class Investation {
    @Id
    @GeneratedValue(generator = "INVESTATION_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "INVESTATION_GEN", sequenceName = "INVESTATION_SEQ", allocationSize = 1)
    private Long investationId;
    @ManyToOne
    @JoinColumn(name = "UNIVERSITY_ID")
    private Long universityId;
    @Column(name = "INVESTATION_NAME")
    private String investationName;
    @Column(name = "INITIAL_SAVING")
    private Long initialSaving;
    @Column(name = "INITIAL_UNIT")
    private Long initialUnit;
    @Column(name = "INITIAL_VALUE")
    private Long initialValue;
    @Column(name = "MARKET_SAVING")
    private Long marketSaving;
    @Column(name = "MARKET_UNIT")
    private Long marketUnit;
    @Column(name = "MARKET_VALUE")
    private Long marketValue;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
}
