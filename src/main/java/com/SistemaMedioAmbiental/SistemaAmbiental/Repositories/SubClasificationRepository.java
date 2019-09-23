package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;


import com.SistemaMedioAmbiental.SistemaAmbiental.Models.SubClasification;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubClasificationRepository extends JpaRepository<SubClasification, Long> {
    SubClasification findByName(String name);
    SubClasification findById(long id);
}