package com.library.controller;

import com.library.model.Author;
import com.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Указывает, что это контроллер, который возвращает данные в формате JSON
@RequestMapping("/api/authors") // Базовый путь для всех методов в этом контроллере
public class AuthorController {

    @Autowired // Внедряет сервис для работы с авторами
    private AuthorService authorService;

    // Получить всех авторов
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    // Получить автора по ID
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получить авторов по имени
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Author>> getAuthorsByName(@PathVariable String name) {
        List<Author> authors = authorService.getAuthorsByName(name);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    // Создать нового автора
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    // Обновить существующего автора
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author) {
        Author existingAuthor = authorService.getAuthorById(id);
        if (existingAuthor != null) {
            author.setId(id); // Убедимся, что ID обновляемого автора совпадает с переданным
            Author updatedAuthor = authorService.saveAuthor(author);
            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить автора по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
