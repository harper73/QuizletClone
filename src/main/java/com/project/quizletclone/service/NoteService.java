package com.project.quizletclone.service;

import com.project.quizletclone.model.Note;
import com.project.quizletclone.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> findByUserId(Long userId) {
        return noteRepository.findByUserId(userId);
    }

    public Note findById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }
}
