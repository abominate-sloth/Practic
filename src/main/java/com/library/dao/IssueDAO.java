package com.library.dao;

import com.library.model.Issue;

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
    public List<Issue> getAllIssues() {
        List<Issue> issues = new ArrayList<>();
        String sql = "SELECT * FROM issues";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Issue issue = new Issue();
                issue.setId(rs.getInt("id"));
                issue.setBookId(rs.getInt("book_id"));
                issue.setReaderId(rs.getInt("reader_id"));
                issue.setEmployeeId(rs.getInt("employee_id")); // Получение employeeId
                issue.setIssueDate(rs.getDate("issue_date"));
                issue.setReturnDate(rs.getDate("return_date"));
                issues.add(issue);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении всех выдач: {0}", e.getMessage());
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