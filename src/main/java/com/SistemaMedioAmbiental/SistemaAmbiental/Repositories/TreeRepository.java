package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;

import java.util.Optional;

import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Tree;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
    Optional<Tree> findByCommonName(String findByCommonName);
    Boolean existsByCodeTree(String codeTree);
}