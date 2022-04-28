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
@Table(name="INBOX")
public class Inbox {
    @Id
    @GeneratedValue(generator = "INBOX_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "INBOX_GEN", sequenceName = "INBOX_SEQ",allocationSize = 1)
    @Column(name = "INBOX_ID")
    private Long inboxId;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(name = "MESSAGE")
    private String message;
    @ManyToOne
    @JoinColumn(name = "TRANSACTION_ID")
    private Transaction transaction;
}
