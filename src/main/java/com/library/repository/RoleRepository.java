package com.library.repository;

import com.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Указывает, что это репозиторий
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Spring Data JPA автоматически предоставляет CRUD-методы

    // Кастомный метод для поиска роли по имени
    Role findByRoleName(String roleName);
}