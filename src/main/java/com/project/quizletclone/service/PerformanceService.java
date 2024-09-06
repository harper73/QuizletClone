package com.project.quizletclone.service;

import com.project.quizletclone.model.Performance;
import com.project.quizletclone.model.Quiz;
import com.project.quizletclone.model.User;
import com.project.quizletclone.repository.PerformanceRepository;
import com.project.quizletclone.repository.QuizRepository;
import com.project.quizletclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.sql.Date;

@Service
public class PerformanceService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    public Performance addPerformance(Long userId, Long quizId, Integer score, Long duration) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new IllegalArgumentException("Quiz not found"));

        Performance performance = new Performance();
        performance.setUser(user);
        performance.setQuiz(quiz);
        performance.setScore(score);
        performance.setDuration(duration);
        performance.setDateTaken(Date.valueOf(LocalDate.now())); // Use SQL Date

        // Print details for debugging
        System.out.println("Performance Details:");
        System.out.println("User ID: " + userId);
        System.out.println("Quiz ID: " + quizId);
        System.out.println("Score: " + score);
        System.out.println("Duration: " + duration);
        System.out.println("Date Taken: " + performance.getDateTaken());

        Performance savedPerformance = performanceRepository.save(performance);

        // Print the saved performance object
        System.out.println("Saved Performance Details:");
        System.out.println("ID: " + savedPerformance.getId());
        System.out.println("User ID: " + savedPerformance.getUser().getId());
        System.out.println("Quiz ID: " + savedPerformance.getQuiz().getId());
        System.out.println("Score: " + savedPerformance.getScore());
        System.out.println("Duration: " + savedPerformance.getDuration());
        System.out.println("Date Taken: " + savedPerformance.getDateTaken());

        return savedPerformance;
    }

    public Iterable<Performance> getPerformance(Long userId) {
        return performanceRepository.findByUserId(userId);
    }

    public int getTotalQuizzesTaken(Long userId) {
        return performanceRepository.countByUserId(userId);
    }

    public double getAverageScore(Long userId) {
        return performanceRepository.findAverageScoreByUserId(userId);
    }

    public int getQuizzesCompleted(Long userId) {
        return performanceRepository.countCompletedQuizzesByUserId(userId);
    }

    public List<Performance> getPerformanceOverTime(Long userId) {
        return performanceRepository.findByUserIdOrderByDateTaken(userId);
    }

}
