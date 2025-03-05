package com.library.dao;

import com.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final Logger logger = Logger.getLogger(BookDAO.class.getName());

    // Метод для подключения к базе данных
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Добавление книги
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, genre_id, publish_year, isbn, copies_available) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());

            // Используем setObject для nullable полей
            pstmt.setObject(2, book.getGenreId());
            pstmt.setObject(3, book.getPublishYear());

            pstmt.setString(4, book.getIsbn());
            pstmt.setInt(5, book.getCopiesAvailable()); // Добавлено поле copiesAvailable
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении книги: {0}", e.getMessage());
        }
    }

    // Получение всех книг
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));

                // Используем getObject для nullable полей
                book.setGenreId((Integer) rs.getObject("genre_id"));
                book.setPublishYear((Integer) rs.getObject("publish_year"));

                book.setIsbn(rs.getString("isbn"));
                book.setCopiesAvailable(rs.getInt("copies_available")); // Получение copiesAvailable
                books.add(book);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении книг: {0}", e.getMessage());
        }
        return books;
    }

    // Обновление книги
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, genre_id = ?, publish_year = ?, isbn = ?, copies_available = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());

            // Используем setObject для nullable полей
            pstmt.setObject(2, book.getGenreId());
            pstmt.setObject(3, book.getPublishYear());

            pstmt.setString(4, book.getIsbn());
            pstmt.setInt(5, book.getCopiesAvailable()); // Обновление copiesAvailable
            pstmt.setInt(6, book.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении книги: {0}", e.getMessage());
        }
    }

    // Удаление книги
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении книги: {0}", e.getMessage());
        }
    }
}