package com.bank.backend.repository;

import com.bank.backend.entity.AccessRight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccessRightRepository extends JpaRepository<AccessRight,Long>{
    
    @Query(value = "SELECT * FROM ACCESS_RIGHTS AR WHERE AR.GROUP_ID = ?1 AND AR.USER_ID = ?2",
        nativeQuery = true
    )
    AccessRight getByGroupIdAndUserId(Long groupId,Long userId);
}
