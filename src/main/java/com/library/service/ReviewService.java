package com.library.service;

import com.library.model.Review;
import com.library.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Указывает, что это сервис
public class ReviewService {

    @Autowired // Внедряет репозиторий
    private ReviewRepository reviewRepository;

    // Получить все отзывы
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Получить отзыв по ID
    public Review getReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    // Получить отзывы по книге
    public List<Review> getReviewsByBook(int bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    // Получить отзывы по читателю
    public List<Review> getReviewsByReader(int readerId) {
        return reviewRepository.findByReaderId(readerId);
    }

    // Сохранить отзыв
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    // Удалить отзыв по ID
    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}