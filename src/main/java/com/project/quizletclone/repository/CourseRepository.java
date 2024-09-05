package com.project.quizletclone.repository;

import com.project.quizletclone.model.Course;
import com.project.quizletclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByTitle(String title);
}
