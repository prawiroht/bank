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
@Table(name = "GROUP_MENUS")
public class GroupMenu {
    @Id
    @GeneratedValue(generator = "GM_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "GM_GEN", sequenceName = "GROUP_MENU_SEQ", allocationSize = 1)
    @Column(name = "GROUP_MENU_ID")
    private Long groupMenuId;
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;
    @Column(name = "IS_ACTIVE")
    private Character isActive;
}
