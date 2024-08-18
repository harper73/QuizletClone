package com.project.quizletclone.controller;

import com.project.quizletclone.model.Performance;
import com.project.quizletclone.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping
    public Performance addPerformance(@PathVariable Long userId,
                                      @RequestParam Long quizId,
                                      @RequestParam Integer score,
                                      @RequestParam Long duration) {
        return performanceService.addPerformance(userId, quizId, score, duration);
    }

    @GetMapping
    public Iterable<Performance> getPerformance(@PathVariable Long userId) {
        return performanceService.getPerformance(userId);
    }
}
