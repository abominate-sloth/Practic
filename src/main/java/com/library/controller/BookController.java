package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Указывает, что это контроллер, который возвращает данные в формате JSON
@RequestMapping("/api/books") // Базовый путь для всех методов в этом контроллере
public class BookController {

    @Autowired // Внедряет сервис для работы с книгами
    private BookService bookService;

    // Получить все книги
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Получить книгу по ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получить книги по названию
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
        List<Book> books = bookService.getBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Получить книги по жанру
    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable int genreId) {
        List<Book> books = bookService.getBooksByGenre(genreId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Создать новую книгу
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.saveBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    // Обновить существующую книгу
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            book.setId(id); // Убедимся, что ID обновляемой книги совпадает с переданным
            Book updatedBook = bookService.saveBook(book);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить книгу по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
