package com.library.dao;

import com.library.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final Logger logger = Logger.getLogger(AuthorDAO.class.getName());

    // Метод для подключения к базе данных
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Create (Добавление автора)
    public void addAuthor(Author author) {
        String sql = "INSERT INTO authors (name, birth_date) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author.getName());
            pstmt.setDate(2, author.getBirthDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении автора: {0}", e.getMessage());
        }
    }

    // Read (Получение всех авторов)
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM authors";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setBirthDate(rs.getDate("birth_date"));
                authors.add(author);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении авторов: {0}", e.getMessage());
        }
        return authors;
    }

    // Update (Обновление автора)
    public void updateAuthor(Author author) {
        String sql = "UPDATE authors SET name = ?, birth_date = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author.getName());
            pstmt.setDate(2, author.getBirthDate());
            pstmt.setInt(3, author.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении автора: {0}", e.getMessage());
        }
    }

    // Delete (Удаление автора)
    public void deleteAuthor(int id) {
        String sql = "DELETE FROM authors WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении автора: {0}", e.getMessage());
        }
    }
}