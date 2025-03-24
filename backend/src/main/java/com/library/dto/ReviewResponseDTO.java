package com.library.dto;

import lombok.Data;

@Data
public class ReviewResponseDTO {
    private Integer id;
    private BookSimpleDTO book;
    private UserSimpleDTO user;
    private Integer rating;
    private String comment;
}

