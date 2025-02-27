package com.library.dao;

import com.library.model.BookAuthor;

import java.sql.*;

public class BookAuthorDAO {
    private final String url = "jdbc:postgresql://localhost:5432/your_database_name";
    private final String user = "your_username";
    private final String password = "your_password";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Добавление связи между книгой и автором
    public void addBookAuthor(BookAuthor bookAuthor) {
        String sql = "INSERT INTO bookauthors (book_id, author_id) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookAuthor.getBookId());
            pstmt.setInt(2, bookAuthor.getAuthorId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Удаление связи между книгой и автором
    public void deleteBookAuthor(int bookId, int authorId) {
        String sql = "DELETE FROM bookauthors WHERE book_id = ? AND author_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, authorId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}