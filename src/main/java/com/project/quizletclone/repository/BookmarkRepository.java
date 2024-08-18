package com.project.quizletclone.repository;

import com.project.quizletclone.model.Bookmark;
import com.project.quizletclone.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUserId(Long userId);
    boolean existsByUserIdAndContentTypeAndContentId(Long userId, String contentType, Long contentId);
}
