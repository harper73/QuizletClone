package com.project.quizletclone.service;

import com.project.quizletclone.model.Quiz;
import com.project.quizletclone.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> searchQuizzes(String query) {
        return quizRepository.findByTitleContainingIgnoreCase(query);
    }

    public List<Quiz> searchQuizzesBySubjectArea(String subjectArea) {
        return quizRepository.findBySubjectAreaContainingIgnoreCase(subjectArea);
    }
}
