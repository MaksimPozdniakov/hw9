package com.example.hw9.controller;

import com.example.hw9.domain.Note;
import com.example.hw9.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    /**
     * Метод получения всех заметок
     * @return возвращаем список заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return new ResponseEntity<>(noteService.getAll(), HttpStatus.OK);
    }

    /**
     * Метод создания новой заметки
     * @param note объект заметки
     * @return возвращаем объект заметки
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.OK);
    }

    /**
     * Метод поиска заметки по id
     * @param id id нужной нам заметки
     * @return возвращаем найденную заметку, или постой объект, если заметка не найдена
     */
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) {
        Note noteById;
        try {
            noteById = noteService.getNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(noteById, HttpStatus.OK);
    }

    /**
     * Метод изменяет заметку
     * @param id id нужной нам заметки
     * @param note объект заметки
     * @return возвращаем измененную заметку
     */
    @PutMapping("{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        return new ResponseEntity<>(noteService.updateNote(id, note), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        noteService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
