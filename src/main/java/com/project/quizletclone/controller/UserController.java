package com.project.quizletclone.controller;

import com.project.quizletclone.model.Bookmark;
import com.project.quizletclone.model.User;
import com.project.quizletclone.model.Performance;
import com.project.quizletclone.model.Achievement;
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

    @PostMapping("/{userId}/performance")
    public Performance addPerformance(@PathVariable Long userId,
                                      @RequestParam Long quizId,
                                      @RequestParam Integer score,
                                      @RequestParam Long duration) {
        return userService.addPerformance(userId, quizId, score, duration);
    }

    @GetMapping("/{userId}/performance")
    public Iterable<Performance> getPerformance(@PathVariable Long userId) {
        return userService.getPerformance(userId);
    }

    @PostMapping("/{userId}/achievements")
    public Achievement addAchievement(@PathVariable Long userId,
                                      @RequestParam String title,
                                      @RequestParam String description,
                                      @RequestParam String dateAwarded) {
        return userService.addAchievement(userId, title, description, dateAwarded);
    }

    @GetMapping("/{userId}/achievements")
    public Iterable<Achievement> getAchievements(@PathVariable Long userId) {
        return userService.getAchievements(userId);
    }

    @PostMapping("/{userId}/bookmarks")
    public Bookmark addBookmark(@PathVariable Long userId,
                                @RequestParam String contentType,
                                @RequestParam Long contentId) {
        return userService.addBookmark(userId, contentType, contentId);
    }

    @DeleteMapping("/{userId}/bookmarks/{bookmarkId}")
    public void removeBookmark(@PathVariable Long userId,
                               @PathVariable Long bookmarkId) {
        userService.removeBookmark(userId, bookmarkId);
    }

    @PostMapping("/login")
    public boolean authenticateUser(@RequestParam String username,
                                    @RequestParam String password) {
        return userService.authenticateUser(username, password);
    }

    // Additional endpoints for user management can be added here
}
