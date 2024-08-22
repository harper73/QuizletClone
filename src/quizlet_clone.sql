CREATE DATABASE IF NOT EXISTS quizlet_clone;
USE quizlet_clone;

CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS courses (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
    subjectArea VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS quizzes (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
    subjectArea VARCHAR(255) NOT NULL,
    course_id BIGINT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id)
    );

CREATE TABLE IF NOT EXISTS questions (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         questionText TEXT NOT NULL,
                                         correctAnswer TEXT NOT NULL,
                                         quiz_id BIGINT NOT NULL,
                                         FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
    );

CREATE TABLE IF NOT EXISTS answers (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       text TEXT NOT NULL,
                                       correct BOOLEAN NOT NULL,
                                       question_id BIGINT NOT NULL,
                                       FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS articles (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    subjectArea VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS bookmarks (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         user_id BIGINT,
                                         contentType VARCHAR(255) NOT NULL,
    contentId BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS notes (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id BIGINT NOT NULL,
                                     content VARCHAR(1000) NOT NULL,
    title VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS performance (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           user_id BIGINT,
                                           quiz_id BIGINT,
                                           score INT NOT NULL,
                                           duration BIGINT NOT NULL, -- Time in seconds
                                           dateTaken DATE NOT NULL,
                                           FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
    );

CREATE TABLE IF NOT EXISTS achievements (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            user_id BIGINT NOT NULL,
                                            title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    dateAwarded DATE,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS videos (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    description TEXT,
    subjectArea VARCHAR(255) NOT NULL
    );
