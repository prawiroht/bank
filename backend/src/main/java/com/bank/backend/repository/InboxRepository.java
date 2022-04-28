package com.bank.backend.repository;

import java.util.List;

import com.bank.backend.entity.Inbox;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InboxRepository extends JpaRepository<Inbox, Long>{
    
    @Query(value = "SELECT * FROM INBOX I WHERE I.USER_ID = ?1", nativeQuery = true)
    public Page<Inbox> getByUserId(Long userId,Pageable paging);

    @Query(value = "SELECT * FROM INBOX I WHERE I.USER_ID = ?1", nativeQuery = true)
    public List<Inbox> findByUserId(Long userId);
}
