package com.bank.backend.repository;

import com.bank.backend.entity.GroupMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupMenuRepository extends JpaRepository<GroupMenu,Long>{
    @Query(value = "SELECT * FROM GROUP_MENUS GM WHERE GM.GROUP_ID = ?1 AND GM.MENU_ID = ?2",
        nativeQuery = true
    )
    GroupMenu findByGroupIdAndMenuId(Long groupId,Long menuId);
}
