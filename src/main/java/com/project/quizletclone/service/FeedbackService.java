package com.project.quizletclone.service;

import com.project.quizletclone.model.Answer;
import com.project.quizletclone.model.Question;
import com.project.quizletclone.model.Quiz;
import com.project.quizletclone.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<String> generateFeedback(Long quizId, List<Answer> userAnswers) {
        List<String> feedback = new ArrayList<>();
        List<Question> questions = questionRepository.findByQuizId(quizId);

        for (Question question : questions) {
            String correctAnswer = question.getCorrectAnswer();
            boolean correct = false;

            for (Answer userAnswer : userAnswers) {
                if (userAnswer.getQuestion().getId().equals(question.getId())) {
                    if (userAnswer.getText().equalsIgnoreCase(correctAnswer)) {
                        correct = true;
                        break;
                    } else {
                        feedback.add("Incorrect: " + question.getQuestionText() + " | Correct answer: " + correctAnswer);
                    }
                }
            }

            if (!correct) {
                feedback.add("Incorrect: " + question.getQuestionText() + " | Correct answer: " + correctAnswer);
            }
        }

        return feedback;
    }
}
