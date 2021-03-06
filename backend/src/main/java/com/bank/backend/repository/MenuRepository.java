package com.bank.backend.repository;

import java.util.List;

import com.bank.backend.entity.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<Menu,Long>{
    @Query(value = "SELECT M.MENU_ID, M.NAME"+
        " FROM MENUS M"+
        " JOIN GROUP_MENUS GM ON M.MENU_ID = GM.MENU_ID"+
        " JOIN GROUPS G ON GM.GROUP_ID = G.GROUP_ID"+
        " WHERE G.GROUP_ID = :pGroupId AND GM.IS_ACTIVE = 'Y'"+
        " ORDER BY M.MENU_ID ASC",
        nativeQuery = true)
    List<Menu> findMenuByGroupId(@Param("pGroupId") Long groupId);

    @Query(value = "SELECT m.menu_ID, m.NAME"+
        " FROM menus m"+
        " JOIN group_menuS gm ON m.menu_ID = gm.menu_ID"+
        " JOIN groups g ON gm.group_ID = g.group_ID"+
        " JOIN ACCESS_RIGHTS AR ON AR.group_ID = g.group_ID"+
        " JOIN USERS U ON U.USER_ID = AR.USER_ID"+
        " WHERE u.user_ID = :pUserId and gm.IS_ACTIVE = 'Y' and ar.IS_ACTIVE = 'Y'"+
        " ORDER BY M.MENU_ID ASC", 
        nativeQuery = true)
    List<Menu> findMenuByUserId(@Param("pUserId") Long userId);
}
