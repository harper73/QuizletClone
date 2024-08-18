package com.project.quizletclone.controller;

import com.project.quizletclone.service.DifficultyAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/difficulty")
public class DifficultyController {

    @Autowired
    private DifficultyAdjustmentService difficultyAdjustmentService;

    @PostMapping("/adjust")
    public ResponseEntity<Void> adjustDifficulty(@RequestParam Long userId) {
        difficultyAdjustmentService.adjustDifficulty(userId);
        return ResponseEntity.ok().build();
    }
}
