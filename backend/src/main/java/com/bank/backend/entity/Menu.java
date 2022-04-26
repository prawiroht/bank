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
@Table(name = "MENUS")
public class Menu {
    @Id
    @GeneratedValue(generator = "MENU_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "MENU_GEN", sequenceName = "MENU_SEQ", allocationSize = 1)
    @Column(name = "GROUP_ID")
    private Long menuId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ICON")
    private String icon;
    @Column(name = "URL")
    private String url;
}
