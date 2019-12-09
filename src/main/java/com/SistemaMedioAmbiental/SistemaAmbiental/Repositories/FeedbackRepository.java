package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;

import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Feedback;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Feedback findByTreeSpecies(String treeSpecies);
    Feedback findById(long id);
}