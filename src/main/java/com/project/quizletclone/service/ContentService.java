package com.project.quizletclone.service;

import com.project.quizletclone.model.*;
import com.project.quizletclone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ContentService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseByTitle(String title) {
        return courseRepository.findByTitle(title);
    }

    // Methods for Videos
    public Video addVideo(String title, String url, String description, String subjectArea) {
        Video video = new Video();
        video.setTitle(title);
        video.setUrl(url);
        video.setDescription(description);
        video.setSubjectArea(subjectArea);
        return videoRepository.save(video);
    }

    public Iterable<Video> getVideos() {
        return videoRepository.findAll();
    }

    // Methods for Articles
    public Article addArticle(String title, String content, String subjectArea) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setSubjectArea(subjectArea);
        return articleRepository.save(article);
    }

    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));
    }

    // Methods for Quizzes
    public Quiz addQuiz(String title, String subjectArea) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setSubjectArea(subjectArea);
        return quizRepository.save(quiz);
    }

    public Iterable<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz findQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));
    }

    // Methods for Questions
    public Question addQuestion(Long quizId, String questionText, String correctAnswer, Set<Answer> answers) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new IllegalArgumentException("Quiz not found"));
        Question question = new Question();
        question.setQuiz(quiz);
        question.setQuestionText(questionText);
        question.setCorrectAnswer(correctAnswer);
        question.setAnswers(answers);
        return questionRepository.save(question);
    }

    public Iterable<Question> getQuestions(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new IllegalArgumentException("Quiz not found"));
        return quiz.getQuestions();
    }
}

