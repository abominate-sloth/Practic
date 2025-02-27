package com.library.dao;

import com.library.model.Issue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueDAO {
    private final String url = "jdbc:postgresql://localhost:5432/your_database_name";
    private final String user = "your_username";
    private final String password = "your_password";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Добавление выдачи книги
    public void addIssue(Issue issue) {
        String sql = "INSERT INTO issues (book_id, reader_id, issue_date, return_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, issue.getBookId());
            pstmt.setInt(2, issue.getReaderId());
            pstmt.setDate(3, issue.getIssueDate());
            pstmt.setDate(4, issue.getReturnDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
                issue.setIssueDate(rs.getDate("issue_date"));
                issue.setReturnDate(rs.getDate("return_date"));
                issues.add(issue);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return issues;
    }

    // Обновление выдачи
    public void updateIssue(Issue issue) {
        String sql = "UPDATE issues SET book_id = ?, reader_id = ?, issue_date = ?, return_date = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, issue.getBookId());
            pstmt.setInt(2, issue.getReaderId());
            pstmt.setDate(3, issue.getIssueDate());
            pstmt.setDate(4, issue.getReturnDate());
            pstmt.setInt(5, issue.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
    }
}