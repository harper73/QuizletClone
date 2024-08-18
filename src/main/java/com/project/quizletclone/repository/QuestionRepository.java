package com.project.quizletclone.repository;

import com.project.quizletclone.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Custom queries can be defined here
}