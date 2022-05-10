package com.bank.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table (name = "MUTATION_RIGHTS")
public class MutationRight {
    @Id
    @GeneratedValue (generator = "MUTATION_RIGHT_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator (name = "MUTATION_RIGHT_GEN", sequenceName = "MUTATION_RIGHT_SEQ", allocationSize = 1)
    @Column (name = "MUTATION_RIGHT_ID")
    private Long mutationRightId;
    @ManyToOne
    @JoinColumn (name = "BANK_ID")
    private Bank bank;
    @ManyToOne
    @JoinColumn (name = "MUTATION_ID")
    private Mutation mutation;
}
