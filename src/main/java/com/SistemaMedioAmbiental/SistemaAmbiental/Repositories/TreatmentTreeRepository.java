package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;

import com.SistemaMedioAmbiental.SistemaAmbiental.Models.TreatmentTree;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentTreeRepository extends JpaRepository<TreatmentTree, Long> {
    TreatmentTree findByTypeOfPruning(String typeOfPruning);
    TreatmentTree findById(long id);
}