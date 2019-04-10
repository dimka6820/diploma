package com.dmma.diploma.repository;

import com.dmma.diploma.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
