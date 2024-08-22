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

        if (authenticatedUser == null) {
            return "error/404"; // Handle user not found
        }

        // Ensure the authenticated user can view the specified userId
        if (!authenticatedUser.getId().equals(userId)) {
            return "error/403"; // Handle unauthorized access
        }

        // Fetch the user dashboard data
        User user = userService.findUserById(userId);
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

    @GetMapping("/test")
    public String testEndpoint(Model model) {
        model.addAttribute("message", "Hello, World!");
        return "test"; // Ensure there's a test.html in templates
    }
}
