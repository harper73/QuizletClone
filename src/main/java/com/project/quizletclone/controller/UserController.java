package com.project.quizletclone.controller;

import com.project.quizletclone.model.Bookmark;
import com.project.quizletclone.model.User;
import com.project.quizletclone.model.Performance;
import com.project.quizletclone.model.Achievement;
import com.project.quizletclone.service.PerformanceService;
import com.project.quizletclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email) {
        return userService.registerUser(username, password, email);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String email) {
        return userService.updateUser(id, username, email);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @PostMapping("/login")
    public boolean authenticateUser(@RequestParam String username,
                                    @RequestParam String password) {
        return userService.authenticateUser(username, password);
    }

    // Additional endpoints for user management can be added here
}
