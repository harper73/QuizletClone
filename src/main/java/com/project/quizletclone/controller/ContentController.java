package com.project.quizletclone.controller;

import com.project.quizletclone.model.*;
import com.project.quizletclone.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    // Endpoints for Videos
    @PostMapping("/videos")
    public Video addVideo(@RequestParam String title,
                          @RequestParam String url,
                          @RequestParam String description,
                          @RequestParam String subjectArea) {
        return contentService.addVideo(title, url, description, subjectArea);
    }

    @GetMapping("/videos")
    public Iterable<Video> getVideos() {
        return contentService.getVideos();
    }

    // Endpoints for Articles
    @PostMapping("/articles")
    public Article addArticle(@RequestParam String title,
                              @RequestParam String content,
                              @RequestParam String subjectArea) {
        return contentService.addArticle(title, content, subjectArea);
    }

    @GetMapping("/articles")
    public Iterable<Article> getArticles() {
        return contentService.getArticles();
    }

    // Endpoints for Quizzes
    @PostMapping("/quizzes")
    public Quiz addQuiz(@RequestParam String title,
                        @RequestParam String subjectArea) {
        return contentService.addQuiz(title, subjectArea);
    }

    @GetMapping("/quizzes")
    public Iterable<Quiz> getQuizzes() {
        return contentService.getQuizzes();
    }

    // Endpoints for Questions
    @PostMapping("/questions")
    public Question addQuestion(@RequestParam Long quizId,
                                @RequestParam String questionText,
                                @RequestParam String correctAnswer,
                                @RequestParam Set<Answer> answers) {
        return contentService.addQuestion(quizId, questionText, correctAnswer, answers);
    }

    @GetMapping("/questions")
    public Iterable<Question> getQuestions(@RequestParam Long quizId) {
        return contentService.getQuestions(quizId);
    }
}

