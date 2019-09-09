package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;

import java.util.Optional;

import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Role;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}