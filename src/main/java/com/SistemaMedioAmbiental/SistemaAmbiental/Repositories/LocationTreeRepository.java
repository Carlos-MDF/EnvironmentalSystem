package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;



import com.SistemaMedioAmbiental.SistemaAmbiental.Models.LocationTree;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LocationTreeRepository extends JpaRepository<LocationTree, Long> {
    LocationTree findByName(String name);
    LocationTree findById(long id);
}