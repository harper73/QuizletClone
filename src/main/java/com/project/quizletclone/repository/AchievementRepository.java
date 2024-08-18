package com.project.quizletclone.repository;

import com.project.quizletclone.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
