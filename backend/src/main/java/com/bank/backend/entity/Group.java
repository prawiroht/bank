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
@Table(name = "GROUPS")
public class Group {
    @Id
    @GeneratedValue(generator = "GROUP_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "GROUP_GEN", sequenceName = "GROUP_SEQ", allocationSize = 1)
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
}
