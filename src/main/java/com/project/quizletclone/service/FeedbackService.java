package com.project.quizletclone.service;

import com.project.quizletclone.model.Answer;
import com.project.quizletclone.model.Question;
import com.project.quizletclone.model.Quiz;
import com.project.quizletclone.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private QuestionRepository questionRepository;

    public Map<Long, String> generateFeedback(Long quizId, List<Answer> userAnswers) {
        Map<Long, String> feedbackMap = new HashMap<>();
        List<Question> questions = questionRepository.findByQuizId(quizId);

        // Debugging: Print the order of questions fetched from the backend
        System.out.println("Questions fetched from backend:");
        for (Question q : questions) {
            System.out.println("Question ID: " + q.getId() + ", Question Text: " + q.getQuestionText());
        }

        // Create a map of questionId -> correctAnswer
        Map<Long, String> questionIdToCorrectAnswer = questions.stream()
                .collect(Collectors.toMap(Question::getId, Question::getCorrectAnswer));

        // Debugging: Print the order of user answers from the frontend
        System.out.println("User answers from frontend:");
        for (Answer a : userAnswers) {
            System.out.println("Question ID: " + a.getQuestion().getId() + ", User Answer: " + a.getText());
        }

        // Iterate over the user's answers
        for (Answer userAnswer : userAnswers) {
            Long questionId = userAnswer.getQuestion().getId();
            String correctAnswer = questionIdToCorrectAnswer.get(questionId);

            // Check if question is found
            if (correctAnswer == null) {
                feedbackMap.put(questionId, "Question not found.");
                continue;
            }

            // Generate feedback based on user's answer vs the correct answer
            if (userAnswer.getText().equalsIgnoreCase(correctAnswer)) {
                feedbackMap.put(questionId, "Correct answer, nice.");
            } else {
                String questionText = questions.stream()
                        .filter(q -> q.getId().equals(questionId))
                        .findFirst()
                        .map(Question::getQuestionText)
                        .orElse("Unknown question");

                feedbackMap.put(questionId, "Incorrect! For question: " + questionText + ", correct answer: " + correctAnswer);
            }
        }

        return feedbackMap;

//        for (Question question : questions) {
//            System.out.println("\nQuestion here: " +  question.getQuestionText() + "\n");
//            String correctAnswer = question.getCorrectAnswer();
//            System.out.println("Correct answer here: " +  correctAnswer + "\n");
//
//            // Find the user's answer for the current question
//            for (Answer userAnswer : userAnswers) {
//                if (userAnswer.getQuestion().getId().equals(question.getId())) {
//                    System.out.println("\n" + question.getQuestionText() + " vs " + userAnswer.getQuestion().getQuestionText() + "\n");
//                    System.out.println("User answer here: " +  userAnswer.getText() + " vs " + correctAnswer + "\n");
//                    // Check if the answer is correct
//                    if (userAnswer.getText().equalsIgnoreCase(correctAnswer)) {
//                        feedback.add("Correct answer, nice.");
//                        break;
//                    } else {
//                        feedback.add("Incorrect! For question: " + question.getQuestionText() + ", correct answer: " + correctAnswer);
//                    }
//                }
//            }
//
//        }

    }
}
