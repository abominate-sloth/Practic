package com.library.dao;

import com.library.model.Issue;
import com.library.model.IssueDetails;
import com.library.model.BookDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IssueDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final Logger logger = Logger.getLogger(IssueDAO.class.getName());

    // Метод для подключения к базе данных
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Добавление выдачи книги
    public void addIssue(Issue issue) {
        String sql = "INSERT INTO issues (book_id, reader_id, employee_id, issue_date, return_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, issue.getBookId());
            pstmt.setInt(2, issue.getReaderId());
            pstmt.setInt(3, issue.getEmployeeId()); // Добавлено поле employeeId
            pstmt.setDate(4, issue.getIssueDate());
            pstmt.setDate(5, issue.getReturnDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении выдачи книги: {0}", e.getMessage());
        }
    }

    // Получение всех выдач

    public List<IssueDetails> getAllIssues() {
        List<IssueDetails> issues = new ArrayList<>();
        String sql = """
                     SELECT i.id, 
                            b.id AS book_id, b.title, g.name AS genre, a.name AS author,
                            i.reader_id, i.employee_id, i.issue_date, i.return_date
                     FROM issues i
                     JOIN books b ON i.book_id = b.id
                     LEFT JOIN genres g ON b.genre_id = g.id
                     LEFT JOIN bookauthors ba ON b.id = ba.book_id
                     LEFT JOIN authors a ON ba.author_id = a.id
                     """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Хранение книг по их id
            List<BookDetails> books = new ArrayList<>();
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String author = rs.getString("author");

                // Поиск существующей книги
                BookDetails bookDetails = books.stream()
                        .filter(b -> b.getId() == bookId)
                        .findFirst()
                        .orElse(null);

                // Если книга не найдена, создаем новую
                if (bookDetails == null) {
                    List<String> authors = new ArrayList<>();
                    if (author != null) {
                        authors.add(author); // Добавляем первого автора
                    }
                    bookDetails = new BookDetails(bookId, title, genre, authors);
                    books.add(bookDetails);
                } else {
                    // Если книга уже существует, добавляем автора только если он не дубликат
                    if (author != null && !bookDetails.getAuthors().contains(author)) {
                        bookDetails.getAuthors().add(author);
                    }
                }

                // Создаем объект IssueDetails
                IssueDetails issue = new IssueDetails(
                        rs.getInt("id"),
                        bookDetails,
                        rs.getInt("reader_id"),
                        rs.getInt("employee_id"),
                        rs.getDate("issue_date"),
                        rs.getDate("return_date")
                );

                // Проверяем, нет ли дубликатов в списке issues
                if (issues.stream().noneMatch(i -> i.getId() == issue.getId())) {
                    issues.add(issue);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении списка выдач: {0}", e.getMessage());
        }
        return issues;
    }

    // Обновление выдачи
    public void updateIssue(Issue issue) {
        String sql = "UPDATE issues SET book_id = ?, reader_id = ?, employee_id = ?, issue_date = ?, return_date = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, issue.getBookId());
            pstmt.setInt(2, issue.getReaderId());
            pstmt.setInt(3, issue.getEmployeeId()); // Обновление employeeId
            pstmt.setDate(4, issue.getIssueDate());
            pstmt.setDate(5, issue.getReturnDate());
            pstmt.setInt(6, issue.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении выдачи: {0}", e.getMessage());
        }
    }

    // Удаление выдачи
    public void deleteIssue(int id) {
        String sql = "DELETE FROM issues WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении выдачи: {0}", e.getMessage());
        }
    }
}