package com.project.quizletclone.service;

import com.project.quizletclone.model.Performance;
import com.project.quizletclone.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DifficultyAdjustmentService {

    @Autowired
    private PerformanceRepository performanceRepository;

    // Example method to adjust difficulty
    public void adjustDifficulty(Long userId) {
        // Fetch performance data and adjust quiz difficulty accordingly
        // This can be done by modifying question parameters or selecting different questions

        List<Performance> performances = performanceRepository.findByUserId(userId);

        double averageScore = performances.stream()
                .mapToInt(Performance::getScore)
                .average()
                .orElse(0);

        // Example logic: if average score > 80, increase difficulty, else decrease
        if (averageScore > 80) {
            // Increase difficulty (e.g., by selecting more challenging questions)
        } else {
            // Decrease difficulty (e.g., by selecting easier questions)
        }
    }
}
