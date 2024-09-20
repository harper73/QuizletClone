package com.project.quizletclone.controller;

import com.project.quizletclone.model.Note;
import com.project.quizletclone.model.User;
import com.project.quizletclone.service.NoteService;
import com.project.quizletclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    // Render the upload page for a specific user
    @GetMapping("/{userId}/upload")
    public String showUploadPage(@PathVariable Long userId, Model model) {
        // Fetch the user by ID
        User user = userService.findUserById(userId);

        if (user == null) {
            // Handle the case where the user is not found
            return "error"; // Redirect to an error page or handle appropriately
        }

        model.addAttribute("user", user); // Pass the user to the model
        return "uploadNote"; // Renders the uploadNote.html template
    }

    // Handle file upload for a specific user
    @PostMapping("/{userId}/upload")
    public ResponseEntity<String> uploadNote(@PathVariable Long userId,
                             @RequestParam("file") MultipartFile file,
                             Model model) {
        try {
            // Find the user by ID
            User user = userService.findUserById(userId);
            if (user == null) {
                model.addAttribute("error", "User not found.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
            }

            // Read the uploaded file content
            StringBuilder contentBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    contentBuilder.append(line).append("\n");
                }
            }

            // Create and save the note
            Note note = new Note();
            note.setUser(user);
            note.setContent(contentBuilder.toString());
            note.setTitle(file.getOriginalFilename()); // Use file name as the title
            noteService.save(note);

            return ResponseEntity.ok("Note uploaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while uploading the note.");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Note>> getUserNotes(@PathVariable Long userId) {
        List<Note> notes = noteService.findByUserId(userId);
        return ResponseEntity.ok(notes);
    }
}
