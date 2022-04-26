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
@Table(name = "UNIVERSITIES")
public class University {
    @Id
    @GeneratedValue(generator = "PERIOD_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PERIOD_GEN", sequenceName = "PERIOD_SEQ", allocationSize = 1)
    private Long universityId;
    @Column(name = "UNIVERSITY_NAME")
    private String universityName;
}
