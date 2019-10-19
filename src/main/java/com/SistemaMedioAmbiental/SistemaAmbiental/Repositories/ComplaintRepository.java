package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;



import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Complaint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    Complaint findByWhistleblower(String whistleblower);
    Complaint findById(long id);
}