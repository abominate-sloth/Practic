package com.library.dao;

import com.library.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    // Метод для подключения к базе данных
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Добавление пользователя
    public void addUser(User user) {
        String sql = "INSERT INTO users (username, password_hash, email, join_date, role_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());
            pstmt.setString(3, user.getEmail());
            pstmt.setDate(4, user.getJoinDate());
            pstmt.setInt(5, user.getRoleId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении пользователя: {0}", e.getMessage());
        }
    }

    // Получение всех пользователей
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));  // Получение логина
                user.setPasswordHash(rs.getString("password_hash"));  // Получение хэша пароля
                user.setEmail(rs.getString("email"));
                user.setJoinDate(rs.getDate("join_date"));
                user.setRoleId(rs.getInt("role_id"));  // Получение идентификатора роли
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении всех пользователей: {0}", e.getMessage());
        }
        return users;
    }

    // Обновление пользователя
    public void updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password_hash = ?, email = ?, join_date = ?, role_id = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());  // Обновление логина
            pstmt.setString(2, user.getPasswordHash());  // Обновление хэша пароля
            pstmt.setString(3, user.getEmail());
            pstmt.setDate(4, user.getJoinDate());
            pstmt.setInt(5, user.getRoleId());  // Обновление идентификатора роли
            pstmt.setInt(6, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении пользователя: {0}", e.getMessage());
        }
    }

    // Удаление пользователя
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении пользователя: {0}", e.getMessage());
        }
    }
}