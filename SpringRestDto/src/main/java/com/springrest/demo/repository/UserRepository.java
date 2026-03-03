package com.springrest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springrest.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}