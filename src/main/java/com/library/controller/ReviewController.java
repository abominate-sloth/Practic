package com.library.controller;

import com.library.model.Review;
import com.library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Указывает, что это контроллер, который возвращает данные в формате JSON
@RequestMapping("/api/reviews") // Базовый путь для всех методов в этом контроллере
public class ReviewController {

    @Autowired // Внедряет сервис для работы с отзывами
    private ReviewService reviewService;

    // Получить все отзывы
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Получить отзыв по ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable int id) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получить отзывы по книге
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getReviewsByBook(@PathVariable int bookId) {
        List<Review> reviews = reviewService.getReviewsByBook(bookId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Получить отзывы по читателю
    @GetMapping("/reader/{readerId}")
    public ResponseEntity<List<Review>> getReviewsByReader(@PathVariable int readerId) {
        List<Review> reviews = reviewService.getReviewsByReader(readerId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Создать новый отзыв
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewService.saveReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    // Обновить существующий отзыв
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable int id, @RequestBody Review review) {
        Review existingReview = reviewService.getReviewById(id);
        if (existingReview != null) {
            review.setId(id); // Убедимся, что ID обновляемого отзыва совпадает с переданным
            Review updatedReview = reviewService.saveReview(review);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить отзыв по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            reviewService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}