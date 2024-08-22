package com.project.quizletclone.repository;

import com.project.quizletclone.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findByUserId(Long userId);
    int countByUserId(Long userId);
    double findAverageScoreByUserId(Long userId);
    int countCompletedQuizzesByUserId(Long userId);
    List<Performance> findByUserIdOrderByDateTaken(Long userId);
}
