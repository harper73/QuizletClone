package com.project.quizletclone.controller;

import com.project.quizletclone.model.Bookmark;
import com.project.quizletclone.model.User;
import com.project.quizletclone.model.Performance;
import com.project.quizletclone.model.Achievement;
import com.project.quizletclone.service.PerformanceService;
import com.project.quizletclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignUpPage() {
        System.out.print("\nIt should reach signup page here\n");
        return "/signup"; // This refers to the signup.html template
    }

    @PostMapping("/register")
    public User registerUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email) {
        System.out.print("\nThis should register user\n");
        return userService.registerUser(username, password, email);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping("/{id}/manage")
    public String getUserAccountPage(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "/userAccount"; // The name of the Thymeleaf template
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) MultipartFile avatar,
                           RedirectAttributes redirectAttributes) throws IOException {
        User updatedUser = userService.updateUser(id, username, email, avatar);
        redirectAttributes.addFlashAttribute("user", updatedUser);
        return "redirect:/api/users/" + id + "/manage";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/logout"; // Redirect to logout after account deletion
    }


    @GetMapping("/login")
    public String showLoginPage(Model model) {
        // Retrieve all users
        List<User> users = userService.getAllUsers();

        // Add users to the model
        model.addAttribute("users", users);

        System.out.println("I touch here");
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String rawPassword = "password";
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//
//        System.out.println("Encoded Password: " + encodedPassword);

        return "/login";
    }

    @GetMapping("/redirectAfterLogin")
    public String redirectAfterLogin(Principal principal) {
        // Fetch the username of the authenticated user
        String username = principal.getName();
        System.out.println("\nIt reach controller\n");
        // Fetch the user by username to get the ID
        User user = userService.findUserByUsername(username);

        if (user != null) {
            Long userId = user.getId();
            // Redirect to the specific user's progress page
            return "redirect:/user/" + userId + "/progress";
        } else {
            // Handle the case where the user is not found
            return "redirect:/login?error=userNotFound";
        }
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password, Model model) {
//        System.out.println("Login attempt for user: " + username);
//        boolean authenticated = userService.authenticateUser(username, password);
//        if (authenticated) {
//            // Find the user by username to get their ID
//            User user = userService.findUserByUsername(username);
//            if (user != null) {
//                Long userId = user.getId();
//                System.out.println("Login successful for user: " + username);
//                return "redirect:/user/" + userId + "/progress"; // Redirect to user's progress page
//            } else {
//                model.addAttribute("error", "User not found.");
//                return "login"; // Return to login page with error message
//            }
//        } else {
//            System.out.println("Login failed for user: " + username);
//            model.addAttribute("error", "Invalid username or password");
//            return "login"; // Redirect back to login page with error message
//        }
//    }

    // Additional endpoints for user management can be added here
}
