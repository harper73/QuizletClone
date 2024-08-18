package com.project.quizletclone.repository;

import com.project.quizletclone.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
