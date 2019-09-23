package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;


import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Clasification;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClasificationRepository extends JpaRepository<Clasification, Long> {
    Clasification findByName(String name);
    Clasification findById(long id);
}