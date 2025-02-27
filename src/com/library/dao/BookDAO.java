package com.library.dao;

import com.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final String url = "jdbc:postgresql://localhost:5432/your_database_name";
    private final String user = "your_username";
    private final String password = "your_password";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Добавление книги
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author_id, genre_id, publish_year, isbn) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setInt(2, book.getAuthorId());
            pstmt.setInt(3, book.getGenreId());
            pstmt.setInt(4, book.getPublishYear());
            pstmt.setString(5, book.getIsbn());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
                book.setAuthorId(rs.getInt("author_id"));
                book.setGenreId(rs.getInt("genre_id"));
                book.setPublishYear(rs.getInt("publish_year"));
                book.setIsbn(rs.getString("isbn"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    // Обновление книги
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author_id = ?, genre_id = ?, publish_year = ?, isbn = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setInt(2, book.getAuthorId());
            pstmt.setInt(3, book.getGenreId());
            pstmt.setInt(4, book.getPublishYear());
            pstmt.setString(5, book.getIsbn());
            pstmt.setInt(6, book.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
    }
}