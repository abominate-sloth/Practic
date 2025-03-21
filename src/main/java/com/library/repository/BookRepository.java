package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Указывает, что это репозиторий
public interface BookRepository extends JpaRepository<Book, Integer> {
    // Spring Data JPA автоматически предоставляет CRUD-методы

    // Кастомный метод для поиска книг по названию
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Кастомный метод для поиска книг по жанру
    List<Book> findByGenreId(int genreId);
}
