package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;


import com.SistemaMedioAmbiental.SistemaAmbiental.Models.SubDistrict;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubDistrictRepository extends JpaRepository<SubDistrict, Long> {
    SubDistrict findByName(String name);
    SubDistrict findById(long id);
}