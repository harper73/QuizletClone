package com.project.quizletclone.controller;

import com.project.quizletclone.model.Achievement;
import com.project.quizletclone.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/api/users/{userId}/achievements")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @PostMapping
    public Achievement addAchievement(@PathVariable Long userId,
                                      @RequestParam String title,
                                      @RequestParam String description,
                                      @RequestParam String dateAwarded) throws ParseException {
        // Parse the date from String to Date
        Date sqlDate = Date.valueOf(dateAwarded);

        return achievementService.addAchievement(userId, title, description, sqlDate);
    }

    @GetMapping
    public Iterable<Achievement> getAchievements(@PathVariable Long userId) {
        return achievementService.getAchievements(userId);
    }
}
