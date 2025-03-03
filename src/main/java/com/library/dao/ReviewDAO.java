package com.library.dao;

import com.library.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReviewDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final Logger logger = Logger.getLogger(ReviewDAO.class.getName());

    // Метод для подключения к базе данных
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Добавление отзыва
    public void addReview(Review review) {
        String sql = "INSERT INTO reviews (book_id, reader_id, rating, comment) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review.getBookId());
            pstmt.setInt(2, review.getReaderId());
            pstmt.setInt(3, review.getRating());
            pstmt.setString(4, review.getComment());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении отзыва: {0}", e.getMessage());
        }
    }

    // Получение всех отзывов
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setBookId(rs.getInt("book_id"));
                review.setReaderId(rs.getInt("reader_id"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении всех отзывов: {0}", e.getMessage());
        }
        return reviews;
    }

    // Обновление отзыва
    public void updateReview(Review review) {
        String sql = "UPDATE reviews SET book_id = ?, reader_id = ?, rating = ?, comment = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review.getBookId());
            pstmt.setInt(2, review.getReaderId());
            pstmt.setInt(3, review.getRating());
            pstmt.setString(4, review.getComment());
            pstmt.setInt(5, review.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении отзыва: {0}", e.getMessage());
        }
    }

    // Удаление отзыва
    public void deleteReview(int id) {
        String sql = "DELETE FROM reviews WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении отзыва: {0}", e.getMessage());
        }
    }
}