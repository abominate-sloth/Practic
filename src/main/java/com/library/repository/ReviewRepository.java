package com.library.repository;

import com.library.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Указывает, что это репозиторий
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // Spring Data JPA автоматически предоставляет CRUD-методы

    // Кастомный метод для поиска отзывов по книге
    List<Review> findByBookId(int bookId);

    // Кастомный метод для поиска отзывов по читателю
    List<Review> findByReaderId(int readerId);
}