package com.project.quizletclone.controller;

import com.project.quizletclone.model.Note;
import com.project.quizletclone.model.User;
import com.project.quizletclone.service.NoteService;
import com.project.quizletclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<Note> uploadNote(@RequestBody Note note, @RequestParam Long userId) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }
        note.setUser(user);
        Note savedNote = noteService.save(note);
        return ResponseEntity.ok(savedNote);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Note>> getUserNotes(@PathVariable Long userId) {
        List<Note> notes = noteService.findByUserId(userId);
        return ResponseEntity.ok(notes);
    }
}
