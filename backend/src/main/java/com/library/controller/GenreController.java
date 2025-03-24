package com.library.controller;

import com.library.dto.GenreRequestDTO;
import com.library.dto.GenreResponseDTO;
import com.library.model.Genre;
import com.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    // Получить все жанры
    @GetMapping
    public ResponseEntity<List<GenreResponseDTO>> getAllGenres(
            @RequestParam(required = false) String name) {

        List<Genre> genres = genreService.filterGenres(name);
        List<GenreResponseDTO> dtos = genres.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Получить жанр по ID
    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> getGenreById(@PathVariable int id) {
        Genre genre = genreService.getGenreById(id);
        if (genre != null) {
            return new ResponseEntity<>(convertToResponseDTO(genre), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Создать жанр
    @PostMapping
    public ResponseEntity<GenreResponseDTO> createGenre(@RequestBody GenreRequestDTO genreDTO) {
        Genre genre = convertToEntity(genreDTO);
        Genre createdGenre = genreService.saveGenre(genre);
        return new ResponseEntity<>(convertToResponseDTO(createdGenre), HttpStatus.CREATED);
    }

    // Обновить жанр
    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> updateGenre(
            @PathVariable int id,
            @RequestBody GenreRequestDTO genreDTO) {

        Genre existingGenre = genreService.getGenreById(id);
        if (existingGenre != null) {
            Genre genre = convertToEntity(genreDTO);
            genre.setId(id);
            Genre updatedGenre = genreService.saveGenre(genre);
            return new ResponseEntity<>(convertToResponseDTO(updatedGenre), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Удалить жанр
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable int id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Методы преобразования
    private Genre convertToEntity(GenreRequestDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());
        return genre;
    }

    private GenreResponseDTO convertToResponseDTO(Genre genre) {
        GenreResponseDTO dto = new GenreResponseDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }
}