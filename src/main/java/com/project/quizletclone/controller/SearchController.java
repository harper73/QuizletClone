package com.project.quizletclone.controller;

import com.project.quizletclone.model.Quiz;
import com.project.quizletclone.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/quizzes")
    @ResponseBody
    public List<Quiz> searchQuizzes(@RequestParam String query) {
        return searchService.searchQuizzes(query);
    }

    @GetMapping("/quizzes/subject")
    @ResponseBody
    public List<Quiz> searchQuizzesBySubjectArea(@RequestParam String subjectArea) {
        return searchService.searchQuizzesBySubjectArea(subjectArea);
    }
}
