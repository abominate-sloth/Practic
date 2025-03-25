package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Добавляем аннотацию для генерации конструктора
public class ReviewResponseDTO {
    private Integer id;
    private BookSimpleDTO book;
    private UserSimpleDTO user;
    private Integer rating;
    private String comment;

    // Добавляем пустой конструктор для Lombok
    public ReviewResponseDTO() {}
}

