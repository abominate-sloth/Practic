package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Добавляем аннотацию для генерации конструктора
public class UserSimpleDTO {
    private Integer id;
    private String username;

    // Добавляем пустой конструктор для Lombok
    public UserSimpleDTO() {}
}