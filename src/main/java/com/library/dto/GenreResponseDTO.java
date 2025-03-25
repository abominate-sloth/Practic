package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Добавляем аннотацию для генерации конструктора
public class GenreResponseDTO {
    private int id;
    private String name;

    // Добавляем пустой конструктор для Lombok
    public GenreResponseDTO() {}
}