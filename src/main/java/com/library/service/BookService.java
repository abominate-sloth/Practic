package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Указывает, что это сервис
public class BookService {

    @Autowired // Внедряет репозиторий
    private BookRepository bookRepository;

    // Получить все книги
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Получить книгу по ID
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Получить книги по названию
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // Получить книги по жанру
    public List<Book> getBooksByGenre(int genreId) {
        return bookRepository.findByGenreId(genreId);
    }

    // Сохранить книгу
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Удалить книгу по ID
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
