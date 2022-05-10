package com.bank.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table (name = "MUTATIONS")
public class Mutation {
    @Id
    @Column (name = "MUTATION_ID")
    private String mutationId;
    @Column (name = "MUTATION_NAME")
    private String mutationName;
}
