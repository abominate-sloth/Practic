package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Добавляем аннотацию для генерации конструктора
public class BookSimpleDTO {
    private Integer id;
    private String title;

    // Добавляем пустой конструктор для Lombok
    public BookSimpleDTO() {}
}
