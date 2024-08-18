package com.project.quizletclone.controller;

import com.project.quizletclone.model.User;
import com.project.quizletclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProgressController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/{userId}/progress")
    public String viewUserProgress(@PathVariable("userId") Long userId, Model model) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return "error/404"; // Handle user not found
        }

        model.addAttribute("user", user);
        model.addAttribute("performances", userService.getPerformance(userId));
        model.addAttribute("achievements", userService.getAchievements(userId));

        return "userProgress";
    }
}
