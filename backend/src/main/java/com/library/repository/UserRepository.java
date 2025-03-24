package com.library.repository;

import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository // Указывает, что это репозиторий
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    // Spring Data JPA автоматически предоставляет CRUD-методы

    // Кастомный метод для поиска пользователя по имени пользователя
    User findByUsername(String username);

    // Кастомный метод для поиска пользователя по email
    User findByEmail(String email);
}