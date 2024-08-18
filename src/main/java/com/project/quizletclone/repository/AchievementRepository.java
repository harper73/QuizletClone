package com.project.quizletclone.repository;

import com.project.quizletclone.model.Achievement;
import com.project.quizletclone.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByUserId(Long userId);

}
