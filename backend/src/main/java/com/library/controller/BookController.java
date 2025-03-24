package com.library.controller;

import com.library.dto.*;
import com.library.model.Author;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.GenreService;
import com.library.service.AuthorService;
import com.library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ReviewService reviewService;

    // Фильтрация книг
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> filterBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false) Integer publishYear,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer copiesAvailable) {

        List<Book> books = bookService.filterBooks(title, genreId, publishYear, isbn, copiesAvailable);
        List<BookResponseDTO> dtos = books.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Получить книгу по ID
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return new ResponseEntity<>(convertToResponseDTO(book), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Создать книгу
    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book createdBook = bookService.saveBook(book);
        return new ResponseEntity<>(convertToResponseDTO(createdBook), HttpStatus.CREATED);
    }

    // Обновить книгу
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(
            @PathVariable int id,
            @RequestBody BookRequestDTO bookDTO) {

        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            Book book = convertToEntity(bookDTO);
            book.setId(id);
            Book updatedBook = bookService.saveBook(book);
            return new ResponseEntity<>(convertToResponseDTO(updatedBook), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Удалить книгу
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Методы преобразования
    private Book convertToEntity(BookRequestDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setGenre(genreService.getGenreById(dto.getGenreId()));
        book.setPublishYear(dto.getPublishYear());
        book.setIsbn(dto.getIsbn());
        book.setCopiesAvailable(dto.getCopiesAvailable());

        // Установка авторов
        Set<Author> authors = dto.getAuthorIds().stream()
                .map(authorId -> authorService.getAuthorById(authorId))
                .collect(Collectors.toSet());
        book.setAuthors(authors);

        return book;
    }

    private BookResponseDTO convertToResponseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());

        if (book.getGenre() != null) {
            GenreResponseDTO genreDTO = new GenreResponseDTO();
            genreDTO.setId(book.getGenre().getId());
            genreDTO.setName(book.getGenre().getName());
            dto.setGenre(genreDTO);
        }

        dto.setPublishYear(book.getPublishYear());
        dto.setIsbn(book.getIsbn());
        dto.setCopiesAvailable(book.getCopiesAvailable());

        // Преобразование авторов
        Set<AuthorResponseDTO> authorDTOs = book.getAuthors().stream()
                .map(author -> {
                    AuthorResponseDTO authorDTO = new AuthorResponseDTO();
                    authorDTO.setId(author.getId());
                    authorDTO.setName(author.getName());
                    authorDTO.setBirthDate(author.getBirthDate());
                    return authorDTO;
                })
                .collect(Collectors.toSet());
        dto.setAuthors(authorDTOs);

        // Добавляем только средний рейтинг
        dto.setAverageRating(reviewService.getAverageRatingForBook(book.getId()));

        return dto;
    }
}