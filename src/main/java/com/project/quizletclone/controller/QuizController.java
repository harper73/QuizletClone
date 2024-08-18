package com.project.quizletclone.controller;

import com.project.quizletclone.model.Quiz;

import com.project.quizletclone.model.Answer;
import com.project.quizletclone.service.FeedbackService;
import com.project.quizletclone.service.QuizGradingService;
import com.project.quizletclone.service.QuizGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
        List<String> feedback = feedbackService.generateFeedback(quizId, userAnswers);

        return ResponseEntity.ok().body(new QuizResult(totalScore, feedback));
    }

    // Class to encapsulate the result and feedback
    public static class QuizResult {
        private int score;
        private List<String> feedback;

        public QuizResult(int score, List<String> feedback) {
            this.score = score;
            this.feedback = feedback;
        }

        public int getScore() {
            return score;
        }

        public List<String> getFeedback() {
            return feedback;
        }
    }
}
