package com.bank.backend.repository;

import java.util.Optional;

import com.bank.backend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);
}
