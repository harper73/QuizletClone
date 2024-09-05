package com.project.quizletclone.service;

import com.project.quizletclone.model.Answer;
import com.project.quizletclone.model.Question;
import com.project.quizletclone.model.Quiz;
import com.project.quizletclone.model.User;
import com.project.quizletclone.model.Achievement;
import com.project.quizletclone.model.Performance;
import com.project.quizletclone.repository.QuestionRepository;
import com.project.quizletclone.repository.QuizRepository;
import com.project.quizletclone.repository.AchievementRepository;
import com.project.quizletclone.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QuizGradingService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private AchievementRepository achievementRepository;

    private long calculateDuration(int totalQuestions) {
        //  Return a mock duration in seconds.
        return totalQuestions * 60L; // Assuming 60 seconds per question as a rough estimate
    }

    private void checkAndAwardAchievements(Long userId, int score, int totalQuestions) {
        User user = userService.findUserById(userId);
        if (user == null) return; // Safety check

        // Example Achievement: First Quiz Completed
        if (user.getPerformances().size() == 1) {
            awardAchievement(user, "First Quiz Completed", "Awarded for completing your first quiz.");
        }

        // Example Achievement: Perfect Score
        if (score == totalQuestions) {
            awardAchievement(user, "Perfect Score", "Awarded for scoring 100% on a quiz.");
        }

        // Example Achievement: Quiz Master (Completed 10 quizzes)
        if (user.getPerformances().size() == 10) {
            awardAchievement(user, "Quiz Master", "Awarded for completing 10 quizzes.");
        }
    }

    private void awardAchievement(User user, String title, String description) {
        Optional<Achievement> existingAchievement = user.getAchievements().stream()
                .filter(a -> a.getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (existingAchievement.isEmpty()) {
            Achievement achievement = new Achievement();
            achievement.setUser(user);
            achievement.setTitle(title);
            achievement.setDescription(description);
            // Convert LocalDate to java.sql.Date
            achievement.setDateAwarded(java.sql.Date.valueOf(LocalDate.now()));

            achievementRepository.save(achievement);
        }
    }
    public int gradeQuiz(Long userId, Long quizId, List<Answer> userAnswers) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Question> questions = questionRepository.findByQuizId(quizId);

        int totalScore = 0;
        for (Question question : questions) {
            String correctAnswer = question.getCorrectAnswer();  // Assuming you have a method to get the correct answer
            for (Answer userAnswer : userAnswers) {
                if (userAnswer.getQuestion().getId().equals(question.getId())) {
                    if (userAnswer.getText().equalsIgnoreCase(correctAnswer)) {
                        totalScore += 1; // Assigning 1 point for each correct answer
                    }
                    break;
                }
            }
        }

        // Use the existing addPerformance method from UserService
        long duration = calculateDuration(userAnswers.size()); // Adjust based on your logic
        performanceService.addPerformance(userId, quizId, totalScore, duration);

        // Check for and award achievements
        checkAndAwardAchievements(userId, totalScore, questions.size());

        return totalScore;
    }
}
