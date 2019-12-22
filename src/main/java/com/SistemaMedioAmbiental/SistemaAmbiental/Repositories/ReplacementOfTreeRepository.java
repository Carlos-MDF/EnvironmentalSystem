package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;

import com.SistemaMedioAmbiental.SistemaAmbiental.Models.ReplacementOfTree;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplacementOfTreeRepository extends JpaRepository<ReplacementOfTree, Long> {
    ReplacementOfTree findByTypeOfReplacement(String typeOfReplacement);
    ReplacementOfTree findById(long id);
}