package com.library.dao;

import com.library.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenreDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final Logger logger = Logger.getLogger(GenreDAO.class.getName());

    // Метод для подключения к базе данных
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Добавление жанра
    public void addGenre(Genre genre) {
        String sql = "INSERT INTO genres (name) VALUES (?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, genre.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении жанра: {0}", e.getMessage());
        }
    }

    // Получение всех жанров
    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genres";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt("id"));
                genre.setName(rs.getString("name"));
                genres.add(genre);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении жанров: {0}", e.getMessage());
        }
        return genres;
    }

    // Обновление жанра
    public void updateGenre(Genre genre) {
        String sql = "UPDATE genres SET name = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, genre.getName());
            pstmt.setInt(2, genre.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении жанра: {0}", e.getMessage());
        }
    }

    // Удаление жанра
    public void deleteGenre(int id) {
        String sql = "DELETE FROM genres WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении жанра: {0}", e.getMessage());
        }
    }
}