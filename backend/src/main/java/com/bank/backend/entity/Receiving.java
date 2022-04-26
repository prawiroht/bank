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
@Table (name = "RECEIVINGS")
public class Receiving {
    @Id
    @Column (name = "RECEIVING_ID")
    @GeneratedValue (generator = "RECEIVING_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator (name = "RECEIVING_GEN", sequenceName = "RECEIVING_SEQ", allocationSize = 1, initialValue = 1)
    private Long receivingId;

    @Column (name = "RECEIVING_NAME")
    private String receivingName;
}
