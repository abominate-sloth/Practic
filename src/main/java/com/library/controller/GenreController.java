package com.library.controller;

import com.library.model.Genre;
import com.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Указывает, что это контроллер, который возвращает данные в формате JSON
@RequestMapping("/api/genres") // Базовый путь для всех методов в этом контроллере
public class GenreController {

    @Autowired // Внедряет сервис для работы с жанрами
    private GenreService genreService;

    // Получить все жанры
    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    // Получить жанр по ID
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable int id) {
        Genre genre = genreService.getGenreById(id);
        if (genre != null) {
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Создать новый жанр
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre createdGenre = genreService.saveGenre(genre);
        return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
    }

    // Обновить существующий жанр
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable int id, @RequestBody Genre genre) {
        Genre existingGenre = genreService.getGenreById(id);
        if (existingGenre != null) {
            genre.setId(id); // Убедимся, что ID обновляемого жанра совпадает с переданным
            Genre updatedGenre = genreService.saveGenre(genre);
            return new ResponseEntity<>(updatedGenre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить жанр по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable int id) {
        Genre genre = genreService.getGenreById(id);
        if (genre != null) {
            genreService.deleteGenre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}