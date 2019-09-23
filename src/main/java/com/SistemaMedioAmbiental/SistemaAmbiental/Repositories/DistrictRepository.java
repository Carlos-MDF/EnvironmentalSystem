package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;



import com.SistemaMedioAmbiental.SistemaAmbiental.Models.District;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    District findByName(String name);
    District findById(long id);
}