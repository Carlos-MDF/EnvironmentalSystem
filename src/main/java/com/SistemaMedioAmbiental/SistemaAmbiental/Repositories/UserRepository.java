package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;

import java.util.Optional;


import com.SistemaMedioAmbiental.SistemaAmbiental.Models.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}