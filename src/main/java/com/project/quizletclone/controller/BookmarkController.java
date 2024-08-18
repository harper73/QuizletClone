package com.project.quizletclone.controller;

import com.project.quizletclone.model.Bookmark;
import com.project.quizletclone.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @PostMapping
    public Bookmark addBookmark(@PathVariable Long userId,
                                @RequestParam String contentType,
                                @RequestParam Long contentId) {
        return bookmarkService.addBookmark(userId, contentType, contentId);
    }

    @DeleteMapping("/{bookmarkId}")
    public void removeBookmark(@PathVariable Long userId,
                               @PathVariable Long bookmarkId) {
        bookmarkService.removeBookmark(userId, bookmarkId);
    }

    @GetMapping
    public List<Bookmark> getBookmarks(@PathVariable Long userId) {
        return bookmarkService.getBookmarksByUserId(userId);
    }
}
