package com.project.quizletclone.service;

import com.project.quizletclone.model.Achievement;
import com.project.quizletclone.model.User;
import com.project.quizletclone.repository.AchievementRepository;
import com.project.quizletclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    public Achievement addAchievement(Long userId, String title, String description, String dateAwarded) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Achievement achievement = new Achievement();
        achievement.setUser(user);
        achievement.setTitle(title);
        achievement.setDescription(description);
        achievement.setDateAwarded(dateAwarded);

        return achievementRepository.save(achievement);
    }

    public Iterable<Achievement> getAchievements(Long userId) {
        Iterable<Achievement> achievements = achievementRepository.findByUserId(userId);
        for (Achievement achievement : achievements) {
            System.out.println("Achievement Title: " + achievement.getTitle());
            System.out.println("Achievement Description: " + achievement.getDescription());
            System.out.println("Achievement Date Awarded: " + achievement.getDateAwarded());
        }

        return achievementRepository.findByUserId(userId);
    }
}
