package com.library.repository;

import com.library.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Указывает, что это репозиторий
public interface IssueRepository extends JpaRepository<Issue, Integer> {
    // Spring Data JPA автоматически предоставляет CRUD-методы

    // Кастомный метод для поиска выдач по книге
    List<Issue> findByBookId(int bookId);

    // Кастомный метод для поиска выдач по читателю
    List<Issue> findByReaderId(int readerId);

    // Кастомный метод для поиска выдач по сотруднику
    List<Issue> findByEmployeeId(int employeeId);
}
