package com.project.quizletclone.controller;

import com.project.quizletclone.model.Quiz;

import com.project.quizletclone.model.Answer;
import com.project.quizletclone.service.FeedbackService;
import com.project.quizletclone.service.QuizGradingService;
import com.project.quizletclone.service.QuizGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizGenerationService quizGenerationService;

    @Autowired
    private QuizGradingService quizGradingService;

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/generate")
    public ResponseEntity<Quiz> generateQuiz(@RequestParam Long userId) {
        Quiz quiz = quizGenerationService.generateQuiz(userId);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/{quizId}/grade")
    public ResponseEntity<?> gradeQuiz(@PathVariable Long userId, Long quizId, @RequestBody List<Answer> userAnswers) {
        int totalScore = quizGradingService.gradeQuiz(userId, quizId, userAnswers);
        Map<Long, String> feedback = feedbackService.generateFeedback(quizId, userAnswers);

        return ResponseEntity.ok().body(new QuizResult(totalScore, feedback));
    }

    // Class to encapsulate the result and feedback
    public static class QuizResult {
        private int score;
        private Map<Long, String> feedback;

        public QuizResult(int score, Map<Long, String> feedback) {
            this.score = score;
            this.feedback = feedback;
        }

        public int getScore() {
            return score;
        }

        public Map<Long, String> getFeedback() {
            return feedback;
        }
    }
}
