package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRatingDTO {
    private Double averageRating;
    private Integer ratingsCount;
}