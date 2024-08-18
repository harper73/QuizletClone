package com.project.quizletclone.service;

import com.project.quizletclone.model.Bookmark;
import com.project.quizletclone.model.User;
import com.project.quizletclone.repository.BookmarkRepository;
import com.project.quizletclone.repository.QuizRepository;
import com.project.quizletclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private QuizRepository quizRepository;

    public Bookmark addBookmark(Long userId, String contentType, Long contentId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check for valid content based on contentType
        if (contentType.equals("quiz") && !quizRepository.existsById(contentId)) {
            throw new IllegalArgumentException("Quiz not found");
        }
        // Add more checks for other content types if needed

        // Check for existing bookmark to avoid duplicates
        boolean exists = bookmarkRepository.existsByUserIdAndContentTypeAndContentId(userId, contentType, contentId);
        if (exists) {
            throw new IllegalArgumentException("Bookmark already exists");
        }

        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setContentType(contentType);
        bookmark.setContentId(contentId);

        return bookmarkRepository.save(bookmark);
    }

    public void removeBookmark(Long userId, Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new IllegalArgumentException("Bookmark not found"));
        if (!bookmark.getUser().getId().equals(userId)) {
            throw new SecurityException("User not authorized");
        }
        // Check for valid content based on contentType
        if (bookmark.getContentType().equals("quiz") && !quizRepository.existsById(bookmark.getContentId())) {
            throw new IllegalArgumentException("Content no longer exists");
        }
        // Add more checks for other content types if needed

        bookmarkRepository.delete(bookmark);
    }

    public List<Bookmark> getBookmarksByUserId(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }
}
