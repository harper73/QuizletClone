package com.project.quizletclone.service;

import com.project.quizletclone.model.Note;
import com.project.quizletclone.model.Quiz;
import com.project.quizletclone.model.Question;
import com.project.quizletclone.repository.QuizRepository;
import com.project.quizletclone.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizGenerationService {

    @Autowired
    private NoteService noteService;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Quiz generateQuiz(Long userId) {
        List<Note> notes = noteService.findByUserId(userId);
        // Basic example of quiz generation logic
        Quiz quiz = new Quiz();
        quiz.setTitle("Personalized Quiz for User " + userId);
        quiz = quizRepository.save(quiz);

        // Example logic for creating questions based on notes
        for (Note note : notes) {
            Question question = new Question();
            question.setQuiz(quiz);
            question.setQuestionText("What is important in the following note? " + note.getContent());
            // Add logic for correct answers and options

            //  Do something here, I don't know how?
//            YOU NEED TO DO THIS
            questionRepository.save(question);
        }
        return quiz;
    }
}
