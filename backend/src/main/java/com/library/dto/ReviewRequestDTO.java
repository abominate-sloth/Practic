package com.library.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    @NotNull(message = "Book ID is required")
    private Integer bookId;

    @NotNull(message = "User ID is required")
    private Integer userId;

    @Min(1) @Max(5)
    private int rating;

    @Size(max = 1000)
    private String comment;
}