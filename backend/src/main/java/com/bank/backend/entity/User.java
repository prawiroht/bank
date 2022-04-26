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
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(generator = "USER_GEN",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_GEN", sequenceName = "USER_SEQ")
    @Column(name="USER_ID")
    private Long userId;
    @Column(name="USERNAME")
    private String username;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="NAME")
    private String name;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="EMAIL")
    private String email;
    @Column(name="PHONE")
    private String phone;
}
