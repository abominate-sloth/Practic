package com.library.dao;

import com.library.model.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "postgres";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Добавление читателя
    public void addReader(Reader reader) {
        String sql = "INSERT INTO readers (name, email, join_date) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reader.getName());
            pstmt.setString(2, reader.getEmail());
            pstmt.setDate(3, reader.getJoinDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Получение всех читателей
    public List<Reader> getAllReaders() {
        List<Reader> readers = new ArrayList<>();
        String sql = "SELECT * FROM readers";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reader reader = new Reader();
                reader.setId(rs.getInt("id"));
                reader.setName(rs.getString("name"));
                reader.setEmail(rs.getString("email"));
                reader.setJoinDate(rs.getDate("join_date"));
                readers.add(reader);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return readers;
    }

    // Обновление читателя
    public void updateReader(Reader reader) {
        String sql = "UPDATE readers SET name = ?, email = ?, join_date = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reader.getName());
            pstmt.setString(2, reader.getEmail());
            pstmt.setDate(3, reader.getJoinDate());
            pstmt.setInt(4, reader.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Удаление читателя
    public void deleteReader(int id) {
        String sql = "DELETE FROM readers WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}