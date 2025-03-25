package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor // Добавляем аннотацию для генерации конструктора
public class AuthorResponseDTO {
    private int id;
    private String name;
    private Date birthDate;
}