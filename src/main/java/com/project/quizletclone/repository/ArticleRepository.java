package com.project.quizletclone.repository;

import com.project.quizletclone.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // Custom queries can be defined here
}
