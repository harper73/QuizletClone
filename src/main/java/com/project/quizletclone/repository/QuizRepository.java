package com.project.quizletclone.repository;

import com.project.quizletclone.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
