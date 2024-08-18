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
import java.util.List;

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

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
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


    // Additional methods for user management can be added here
}
