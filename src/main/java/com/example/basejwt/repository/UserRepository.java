package com.example.basejwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basejwt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  public User findByUsername(String username);
}
