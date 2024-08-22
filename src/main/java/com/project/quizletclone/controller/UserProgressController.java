package com.project.quizletclone.controller;

import com.project.quizletclone.model.Achievement;
import com.project.quizletclone.model.User;
import com.project.quizletclone.model.Performance;
import com.project.quizletclone.service.AchievementService;
import com.project.quizletclone.service.UserService;
import com.project.quizletclone.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserProgressController {

    @Autowired
    private UserService userService;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private AchievementService achievementService;

    @GetMapping("/{userId}/progress")
    public String getUserDashboard(@PathVariable Long userId, Authentication authentication, Model model) {
        System.out.println("Start here");
        System.out.println(userId);
        System.out.println(model);
        System.out.println(authentication);
        System.out.println("End here");

        // Safely handle UserDetails
        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }else {
            // Handle case where principal is not an instance of UserDetails
            return "error/403"; // Unauthorized access
        }

        User authenticatedUser = userService.findUserByUsername(username);
        System.out.println("\nReach 1\n");

        if (authenticatedUser == null) {
            return "error/404"; // Handle user not found
        }

        // Ensure the authenticated user can view the specified userId
        if (!authenticatedUser.getId().equals(userId)) {
            return "error/403"; // Handle unauthorized access
        }

        // Fetch the user dashboard data
        User user = userService.findUserById(userId);
        System.out.println("\nReach 2\n");
        if (user == null) {
            return "error/404"; // Handle user not found
        }

        System.out.println("\nReach 3\n");
        int totalQuizzesTaken = performanceService.getTotalQuizzesTaken(user.getId());
        System.out.println(totalQuizzesTaken);
        System.out.println(user.getId());

        double averageScore = performanceService.getAverageScore(user.getId());


        System.out.println("\nReach 4\n");
        int quizzesCompleted = performanceService.getQuizzesCompleted(user.getId());
        int achievementsUnlocked = user.getAchievements().size();
        List<Performance> performanceOverTime = performanceService.getPerformanceOverTime(user.getId());

        Iterable<Performance> performances = performanceService.getPerformance(user.getId()); // Fetch performances

        Iterable<Achievement> achievements = achievementService.getAchievements(user.getId()); // Fetch achievements

        System.out.println("\nReach 5\n");
        model.addAttribute("user", user);
        model.addAttribute("totalQuizzesTaken", totalQuizzesTaken);
        model.addAttribute("averageScore", averageScore);
        model.addAttribute("quizzesCompleted", quizzesCompleted);
        model.addAttribute("achievementsUnlocked", achievementsUnlocked);
        model.addAttribute("performanceOverTime", performanceOverTime);

        model.addAttribute("performances", performances); // Add performances to model
        model.addAttribute("achievements", achievements); // Add achievements to model

        return "userProgress"; // Thymeleaf template name
    }

    @GetMapping("/test")
    public String testEndpoint(Model model) {
        model.addAttribute("message", "Hello, World!");
        return "test"; // Ensure there's a test.html in templates
    }
}
