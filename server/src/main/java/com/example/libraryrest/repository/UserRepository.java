package com.example.libraryrest.repository;

import com.example.libraryrest.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);
}


