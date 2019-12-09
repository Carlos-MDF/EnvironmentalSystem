package com.SistemaMedioAmbiental.SistemaAmbiental.Repositories;

import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Suggestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    Suggestion findByUser(String user);
    Suggestion findById(long id);
}