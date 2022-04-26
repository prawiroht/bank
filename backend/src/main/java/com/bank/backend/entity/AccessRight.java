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
@Table(name = "ACCESS_RIGHTS")
public class AccessRight {
    @Id
    @GeneratedValue(generator = "ACCESS_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ACCESS_GEN", sequenceName = "ACCESS_SEQ", allocationSize = 1)
    @Column(name = "ACCESS_ID")
    private Long accessRightId;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;
    @Column(name = "IS_ACTIVE")
    private Character isActive;
}
