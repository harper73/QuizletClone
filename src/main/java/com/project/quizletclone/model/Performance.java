package com.project.quizletclone.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "performance")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private long duration; // Time taken in seconds

    @Temporal(TemporalType.DATE)
    @Column(name = "date_taken", nullable = false)
    private Date dateTaken;

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

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", user=" + user +
                ", quiz=" + quiz +
                ", score=" + score +
                ", duration=" + duration +
                ", dateTaken='" + dateTaken + '\'' +
                '}';
    }
}
