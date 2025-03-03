package com.library.dao;

import com.library.model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final Logger logger = Logger.getLogger(RoleDAO.class.getName());

    // Метод для подключения к базе данных
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Добавление роли
    public void addRole(Role role) {
        String sql = "INSERT INTO roles (role_name) VALUES (?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, role.getRoleName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении роли: {0}", e.getMessage());
        }
    }

    // Получение всех ролей
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM roles";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleName(rs.getString("role_name"));
                roles.add(role);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении всех ролей: {0}", e.getMessage());
        }
        return roles;
    }

    // Обновление роли
    public void updateRole(Role role) {
        String sql = "UPDATE roles SET role_name = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, role.getRoleName());
            pstmt.setInt(2, role.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении роли: {0}", e.getMessage());
        }
    }

    // Удаление роли
    public void deleteRole(int id) {
        String sql = "DELETE FROM roles WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении роли: {0}", e.getMessage());
        }
    }
}
