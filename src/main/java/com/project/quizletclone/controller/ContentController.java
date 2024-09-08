package com.project.quizletclone.controller;

import com.project.quizletclone.model.*;
import com.project.quizletclone.service.ContentService;
import com.project.quizletclone.service.FeedbackService;
import com.project.quizletclone.service.QuizGradingService;
import com.project.quizletclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuizGradingService quizGradingService;

    @Autowired
    private FeedbackService feedbackService;

    // Endpoints for Videos
    @PostMapping("/videos")
    public Video addVideo(@RequestParam String title,
                          @RequestParam String url,
                          @RequestParam String description,
                          @RequestParam String subjectArea) {
        return contentService.addVideo(title, url, description, subjectArea);
    }

    @GetMapping("/videos")
    public Iterable<Video> getVideos() {
        return contentService.getVideos();
    }

    // Endpoints for Articles
    @PostMapping("/articles")
    public Article addArticle(@RequestParam String title,
                              @RequestParam String content,
                              @RequestParam String subjectArea) {
        return contentService.addArticle(title, content, subjectArea);
    }

    @GetMapping("/articles")
    public Iterable<Article> getArticles() {
        return contentService.getArticles();
    }

    // Endpoints for Quizzes
    @PostMapping("/quizzes")
    public Quiz addQuiz(@RequestParam String title,
                        @RequestParam String subjectArea) {
        return contentService.addQuiz(title, subjectArea);
    }

    @GetMapping("/{userId}/courses")
    public String getCourses(@PathVariable Long userId, Model model) {
        // Fetch the user by ID
        User user = userService.findUserById(userId);

        if (user == null) {
            // Handle the case where the user is not found
            return "error"; // Redirect to an error page or handle appropriately
        }

        // Fetch all courses
        Iterable<Course> courses = contentService.getCourses();

        // Debug: Print out the courses to make sure they are being fetched
        courses.forEach(course -> {
            System.out.println("\nCourse: " + course.getTitle());
        });

        // Add the user and courses to the model
        model.addAttribute("user", user);
        model.addAttribute("courses", courses);

        return "quizzes"; // The name of your Thymeleaf template
    }

    @GetMapping("/{userId}/courses/{courseTitle}/quiz_{quizId}")
    public String getQuiz(@PathVariable Long userId, @PathVariable String courseTitle, @PathVariable Long quizId, Model model) {
        // Fetch the user by ID
        User user = userService.findUserById(userId);

        if (user == null) {
            // Handle the case where the user is not found
            return "error"; // Redirect to an error page or handle appropriately
        }

        // Fetch the course by title (if needed for validation or display)
        Course course = contentService.findCourseByTitle(courseTitle);
        if (course == null) {
            // Handle the case where the course is not found
            return "error"; // Redirect to an error page or handle appropriately
        }

        // Fetch the quiz by ID
        Quiz quiz = contentService.findQuizById(quizId);

        if (quiz == null) {
            // Handle the case where the quiz is not found
            return "error"; // Redirect to an error page or handle appropriately
        }

        // Fetch the questions for the quiz
        Iterable<Question> questions = contentService.getQuestions(quizId);

        // Debug: Print out the quiz and questions to make sure they are being fetched
        System.out.println("Quiz: " + quiz.getTitle());
        questions.forEach(question -> {
            System.out.println("Question: " + question.getQuestionText());
        });

        // Add the user, quiz, and questions to the model
        model.addAttribute("user", user);
        model.addAttribute("course", course);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        model.addAttribute("quizSubmitted", false);  // Ensure quizSubmitted is set to false initially

        return "takeQuiz"; // The name of your Thymeleaf template for the specific quiz
    }

    @PostMapping("/{userId}/courses/{courseTitle}/quiz_{quizId}/submit")
    public String submitQuiz(@PathVariable Long userId, @PathVariable String courseTitle, @PathVariable Long quizId,
                             @RequestParam Map<String, String> answers, Model model) {
        // Remove any unwanted form fields, such as CSRF tokens
        answers.remove("_csrf");

        // Fetch the user by ID
        User user = userService.findUserById(userId);

        if (user == null) {
            // Handle the case where the user is not found
            return "error"; // Redirect to an error page or handle appropriately
        }

        // Convert the answers from the form into a List<Answer>
        List<Answer> userAnswers = answers.entrySet().stream()
                .map(entry -> {
                    Question question = new Question();
                    question.setId(Long.valueOf(entry.getKey())); // Parse the question ID

                    Answer answer = new Answer();
                    answer.setQuestion(question);
                    answer.setText(entry.getValue());
                    return answer;
                })
                .collect(Collectors.toList());

        // Grade the quiz
        int totalScore = quizGradingService.gradeQuiz(userId, quizId, userAnswers);

        System.out.println("\n Total Score: " + totalScore + "\n");
        System.out.println("\n UserId testing: " + userId + "\n");

        // Generate feedback
        Map<Long, String> feedback = feedbackService.generateFeedback(quizId, userAnswers);

        // Add the results to the model
        model.addAttribute("totalScore", totalScore);
        model.addAttribute("feedback", feedback);
        model.addAttribute("quiz", contentService.findQuizById(quizId));
        model.addAttribute("questions", contentService.getQuestions(quizId));
        model.addAttribute("user", user);
        model.addAttribute("user", user);
        model.addAttribute("course", contentService.findCourseByTitle(courseTitle));
        // Add the submission status to the model
        model.addAttribute("quizSubmitted", true);

        // Pass the user's answers back to the view for display
        for (Question question : contentService.getQuestions(quizId)) {
            for (Answer answer : userAnswers) {
                if (question.getId().equals(answer.getQuestion().getId())) {
                    question.setUserAnswer(answer.getText());
                    question.setCorrectAnswer(question.getCorrectAnswer()); // Set correct answer
                }
            }
        }

        System.out.println("\n Test userID at backend after grading quiz: " + userId + "\n");

        return "takeQuiz"; // Return to the same template to display results and feedback
    }

    @GetMapping("/quizzes")
    public Iterable<Quiz> getQuizzes() {
        return contentService.getQuizzes();
    }

    // Endpoints for Questions
    @PostMapping("/questions")
    public Question addQuestion(@RequestParam Long quizId,
                                @RequestParam String questionText,
                                @RequestParam String correctAnswer,
                                @RequestParam Set<Answer> answers) {
        return contentService.addQuestion(quizId, questionText, correctAnswer, answers);
    }

    @GetMapping("/questions")
    public Iterable<Question> getQuestions(@RequestParam Long quizId) {
        return contentService.getQuestions(quizId);
    }
}

