package com.bank.backend.repository;

import java.util.List;

import com.bank.backend.entity.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group,Long>{
    @Query(value = "SELECT G.GROUP_ID, G.NAME"+
        " FROM GROUPS G"+
        " JOIN ACCESS_RIGHTS AR ON G.GROUP_ID = AR.GROUP_ID"+
        " JOIN USERS U ON AR.USER_ID = U.USER_ID"+
        " WHERE U.USER_ID = :pUserId and AR.IS_ACTIVE = 'Y'",
        nativeQuery = true)
    List<Group> findGroupByUserId(@Param("pUserId") Long userId);

    // Page<Group> findAll(Pageable paging);
}
