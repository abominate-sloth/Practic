package com.library.controller;

import com.library.dto.*;
import com.library.model.Review;
import com.library.service.ReviewService;
import com.library.service.BookService;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, BookService bookService, UserService userService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
        this.userService = userService;
    }

    // Фильтрация отзывов
    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> filterReviews(
            @RequestParam(required = false) Integer bookId,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer rating) {

        List<Review> reviews = reviewService.filterReviews(bookId, userId, rating);
        List<ReviewResponseDTO> dtos = reviews.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Получить отзыв по ID
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable Integer id) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            return new ResponseEntity<>(convertToResponseDTO(review), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Создать отзыв
    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewRequestDTO reviewDTO) {
        Review review = convertToEntity(reviewDTO);
        Review createdReview = reviewService.saveReview(review);
        return new ResponseEntity<>(convertToResponseDTO(createdReview), HttpStatus.CREATED);
    }

    // Обновить отзыв
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable Integer id,
            @RequestBody ReviewRequestDTO reviewDTO) {

        Review existingReview = reviewService.getReviewById(id);
        if (existingReview != null) {
            Review review = convertToEntity(reviewDTO);
            review.setId(id);
            Review updatedReview = reviewService.saveReview(review);
            return new ResponseEntity<>(convertToResponseDTO(updatedReview), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Удалить отзыв
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Методы преобразования
    private Review convertToEntity(ReviewRequestDTO dto) {
        Review review = new Review();
        review.setBook(bookService.getBookById(dto.getBookId()));
        review.setReader(userService.getUserById(dto.getUserId()));
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        return review;
    }

    private ReviewResponseDTO convertToResponseDTO(Review review) {
        BookSimpleDTO bookDTO = new BookSimpleDTO(review.getBook().getId(), review.getBook().getTitle());
        UserSimpleDTO userDTO = new UserSimpleDTO(review.getReader().getId(), review.getReader().getUsername());

        return new ReviewResponseDTO(
                review.getId(),
                bookDTO,
                userDTO,
                review.getRating(),
                review.getComment()
        );
    }
}