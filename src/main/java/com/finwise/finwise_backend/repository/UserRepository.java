package com.finwise.finwise_backend.repository;

import com.finwise.finwise_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}