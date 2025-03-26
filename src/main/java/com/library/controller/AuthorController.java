package com.library.controller;

import com.library.dto.AuthorRequestDTO;
import com.library.dto.AuthorResponseDTO;
import com.library.model.Author;
import com.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Фильтрация авторов (возвращает DTO)
    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> filterAuthors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Date birthDate) {

        List<Author> authors = authorService.filterAuthors(name, birthDate);
        List<AuthorResponseDTO> responseDTOs = authors.stream()
                .map(this::convertToResponseDTO)
                .toList(); // Изменено с collect(Collectors.toList())
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    // Получить автора по ID (возвращает DTO)
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            return new ResponseEntity<>(convertToResponseDTO(author), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Создать автора (принимает DTO)
    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO authorDTO) {
        Author author = convertToEntity(authorDTO);
        Author createdAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(convertToResponseDTO(createdAuthor), HttpStatus.CREATED);
    }

    // Обновить автора (принимает DTO)
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable int id, @RequestBody AuthorRequestDTO authorDTO) {
        Author existingAuthor = authorService.getAuthorById(id);
        if (existingAuthor != null) {
            Author author = convertToEntity(authorDTO);
            author.setId(id); // Устанавливаем ID для обновления
            Author updatedAuthor = authorService.saveAuthor(author);
            return new ResponseEntity<>(convertToResponseDTO(updatedAuthor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удаление автора (остается без изменений)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // --- Методы преобразования ---
    private Author convertToEntity(AuthorRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setBirthDate(dto.getBirthDate());
        return author;
    }

    private AuthorResponseDTO convertToResponseDTO(Author author) {
        return new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                author.getBirthDate()
        );
    }
}