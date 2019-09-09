package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;

import java.util.List;



import com.SistemaMedioAmbiental.SistemaAmbiental.Models.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByCi(long ci);
}