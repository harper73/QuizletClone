package com.project.quizletclone.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bookmarks")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String contentType; // e.g., "quiz", "article"

    @Column(nullable = false)
    private Long contentId;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", user=" + user +
                ", contentType='" + contentType + '\'' +
                ", contentId=" + contentId +
                '}';
    }
}
