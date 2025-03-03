package com.library.dao;

import com.library.model.BookAuthor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookAuthorDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final Logger logger = Logger.getLogger(BookAuthorDAO.class.getName());

    // Метод для подключения к базе данных
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
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
            logger.log(Level.SEVERE, "Ошибка при добавлении связи книги и автора: {0}", e.getMessage());
        }
    }

    // Получение всех связей между книгами и авторами
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
            logger.log(Level.SEVERE, "Ошибка при получении связей между книгами и авторами: {0}", e.getMessage());
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
            logger.log(Level.SEVERE, "Ошибка при удалении связи книги и автора: {0}", e.getMessage());
        }
    }
}