package com.project.quizletclone.repository;

import com.project.quizletclone.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // Method to find quizzes by title containing the query string, case insensitive
    List<Quiz> findByTitleContainingIgnoreCase(String title);

    // Method to find quizzes by subject area
    List<Quiz> findBySubjectAreaContainingIgnoreCase(String subjectArea);

}
