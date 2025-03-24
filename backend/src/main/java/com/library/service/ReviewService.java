package com.library.service;

import com.library.dto.BookRatingDTO;
import com.library.model.Review;
import com.library.repository.ReviewRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Указывает, что это сервис
public class ReviewService {

    @Autowired // Внедряет репозиторий
    private ReviewRepository reviewRepository;

    // Получить всех отзывов
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Фильтрация отзывов по параметрам
    public List<Review> filterReviews(Integer bookId, Integer readerId, Integer rating) {
        return reviewRepository.findAll((Specification<Review>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (bookId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("book").get("id"), bookId));
            }
            if (readerId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("reader").get("id"), readerId));
            }
            if (rating != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("rating"), rating));
            }

            return predicate;
        });
    }

    // Сохранить отзыв
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    // Удалить отзыв по ID
    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }

    // Получить отзыв по ID
    public Review getReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Double getAverageRatingForBook(Integer bookId) {
        return reviewRepository.findAverageRatingByBookId(bookId);
    }
}