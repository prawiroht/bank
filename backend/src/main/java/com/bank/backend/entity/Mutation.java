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
@Table (name = "MUTATIONS")
public class Mutation {
    @Id
    @GeneratedValue (generator = "MUTATION_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator (name = "MUTATION_GEN", sequenceName = "MUTATION_SEQ", allocationSize = 1)
    @Column (name = "MUTATION_ID")
    private Long mutationId;
    @Column (name = "MUTATION_NAME")
    private String mutationName;
}
