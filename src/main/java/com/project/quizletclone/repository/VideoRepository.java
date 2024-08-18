package com.project.quizletclone.repository;

import com.project.quizletclone.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    // Custom queries can be defined here
}
