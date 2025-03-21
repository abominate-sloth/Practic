package com.library.repository;

import com.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Указывает, что это репозиторий
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    // Spring Data JPA автоматически предоставляет CRUD-методы

    // Кастомный метод для поиска авторов по имени
    List<Author> findByNameContainingIgnoreCase(String name);
}