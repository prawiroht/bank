package com.bank.backend.repository;

import com.bank.backend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    public User getByUsername(String username);

    public User getByEmail(String email);
}
