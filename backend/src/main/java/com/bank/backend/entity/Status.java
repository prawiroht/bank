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
@Table (name = "STATUSES")
public class Status {
    
    @Id
    @Column (name = "STATUS_ID")
    @GeneratedValue (generator = "STATUS_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator (name = "STATUS_GEN", sequenceName = "STATUS_SEQ", allocationSize = 1, initialValue = 1)
    private Long statusId;

    @Column (name = "STATUS_NAME")
    private String statusName;
    
}
