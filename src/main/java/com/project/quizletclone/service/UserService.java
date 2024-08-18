package com.project.quizletclone.service;

import com.project.quizletclone.model.Bookmark;
import com.project.quizletclone.model.User;
import com.project.quizletclone.model.Quiz;
import com.project.quizletclone.model.Performance;
import com.project.quizletclone.model.Achievement;
import com.project.quizletclone.repository.BookmarkRepository;
import com.project.quizletclone.repository.UserRepository;
import com.project.quizletclone.repository.QuizRepository;
import com.project.quizletclone.repository.PerformanceRepository;
import com.project.quizletclone.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, String email) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);

        return userRepository.save(user);
    }

    public Performance addPerformance(Long userId, Long quizId, Integer score, Long duration) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new IllegalArgumentException("Quiz not found"));

        Performance performance = new Performance();
        performance.setUser(user);
        performance.setQuiz(quiz);
        performance.setScore(score);
        performance.setDuration(duration);
        performance.setDateTaken(LocalDate.now().toString()); // Set the current date as dateTaken

        return performanceRepository.save(performance);
    }

    public Achievement addAchievement(Long userId, String title, String description, String dateAwarded) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Achievement achievement = new Achievement();
        achievement.setUser(user);
        achievement.setTitle(title);
        achievement.setDescription(description);
        achievement.setDateAwarded(dateAwarded);

        return achievementRepository.save(achievement);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Iterable<Performance> getPerformance(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return performanceRepository.findAll(); // Customize as needed
    }

    public Iterable<Achievement> getAchievements(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return achievementRepository.findAll(); // You might want to filter achievements by user
    }

    public User updateUser(Long id, String username, String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (username != null && !username.isEmpty()) {
            user.setUsername(username);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public Bookmark addBookmark(Long userId, String contentType, Long contentId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setContentType(contentType);
        bookmark.setContentId(contentId);

        return bookmarkRepository.save(bookmark);
    }

    public void removeBookmark(Long userId, Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new IllegalArgumentException("Bookmark not found"));
        if (!bookmark.getUser().getId().equals(userId)) {
            throw new SecurityException("User not authorized");
        }
        bookmarkRepository.delete(bookmark);
    }
    // Additional methods for user management can be added here
}
