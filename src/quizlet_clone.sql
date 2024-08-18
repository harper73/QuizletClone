-- Create the database
CREATE DATABASE IF NOT EXISTS quizlet_clone;
USE quizlet_clone;

-- Create the 'users' table
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
    );

-- Create the 'courses' table
CREATE TABLE IF NOT EXISTS courses (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
    description TEXT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
    );

-- Create the 'quizzes' table
CREATE TABLE IF NOT EXISTS quizzes (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
    course_id BIGINT,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
    );

-- Create the 'questions' table
CREATE TABLE IF NOT EXISTS questions (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         text TEXT NOT NULL,
                                         quiz_id BIGINT,
                                         FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE
    );

-- Create the 'answers' table
CREATE TABLE IF NOT EXISTS answers (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       text TEXT NOT NULL,
                                       correct BOOLEAN NOT NULL,
                                       question_id BIGINT,
                                       FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
    );

-- Create the 'performance' table
CREATE TABLE IF NOT EXISTS performance (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           user_id BIGINT,
                                           quiz_id BIGINT,
                                           score INT NOT NULL,
                                           duration BIGINT NOT NULL, -- Duration in seconds
                                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE
    );

-- Create the 'achievements' table
CREATE TABLE IF NOT EXISTS achievements (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            user_id BIGINT,
                                            title VARCHAR(255) NOT NULL,
    description TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );
