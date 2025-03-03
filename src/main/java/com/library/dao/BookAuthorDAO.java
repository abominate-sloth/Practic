package com.library.dao;

import com.library.model.BookAuthor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDAO {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "postgres";

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

    public List<BookAuthor> getAllBookAuthors() {
        List<BookAuthor> bookAuthors = new ArrayList<>();
        String sql = "SELECT * FROM bookauthors";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setBookId(rs.getInt("book_id"));
                bookAuthor.setAuthorId(rs.getInt("author_id"));
                bookAuthors.add(bookAuthor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookAuthors;
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