package com.project.quizletclone.controller;

import com.project.quizletclone.model.User;
import com.project.quizletclone.model.Performance;
import com.project.quizletclone.service.UserService;
import com.project.quizletclone.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserProgressController {

    @Autowired
    private UserService userService;

    @Autowired
    private PerformanceService performanceService;


    @GetMapping("/user/dashboard")
    public String getUserDashboard(Authentication authentication, Model model) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.findUserByUsername(username);

        if (user == null) {
            return "error/404"; // Handle user not found
        }

        int totalQuizzesTaken = performanceService.getTotalQuizzesTaken(user.getId());
        double averageScore = performanceService.getAverageScore(user.getId());
        int quizzesCompleted = performanceService.getQuizzesCompleted(user.getId());
        int achievementsUnlocked = user.getAchievements().size();
        List<Performance> performanceOverTime = performanceService.getPerformanceOverTime(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("totalQuizzesTaken", totalQuizzesTaken);
        model.addAttribute("averageScore", averageScore);
        model.addAttribute("quizzesCompleted", quizzesCompleted);
        model.addAttribute("achievementsUnlocked", achievementsUnlocked);
        model.addAttribute("performanceOverTime", performanceOverTime);

        return "userProgress"; // Thymeleaf template name
    }
}
