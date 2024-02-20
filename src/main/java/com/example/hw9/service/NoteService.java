package com.example.hw9.service;

import com.example.hw9.aspects.TrackUserAction;
import com.example.hw9.domain.Note;
import com.example.hw9.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    /**
     * Метод получения всех заметок
     * @return возвращаем список заметок
     */
    @TrackUserAction
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    /**
     * Метод создания новой заметки
     * @param note объект заметки
     * @return возвращаем объект заметки
     */
    @TrackUserAction
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Метод поиска заметки по id
     * @param id id нужной нам заметки
     * @return возвращаем найденную заметку, или постой объект, если заметка не найдена
     */
    @TrackUserAction
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    /**
     * Метод изменяет заметку
     * @param noteDetails объект заметки
     * @return возвращаем измененную заметку
     */
    @TrackUserAction
    public Note updateNote(Long id, Note noteDetails) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setTitle(noteDetails.getTitle());
            note.setDescription(noteDetails.getDescription());
            return noteRepository.save(note);
        } else {
            throw new IllegalArgumentException("Note not found with id: " + id);
        }
    }

    /**
     * Метод удаляет заметку по id
     * @param id id заметки
     */
    @TrackUserAction
    public void deleteProduct(Long id) {
        Note noteById = getNoteById(id);
        noteRepository.delete(noteById);
    }

}
