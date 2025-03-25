package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Date;

@Data
@AllArgsConstructor // Добавляем аннотацию для генерации конструктора
public class UserResponseDTO {
    private int id;
    private String username;
    private String email;
    private Date joinDate;
    private RoleResponseDTO role; // Вложенный DTO для роли

    // Добавляем пустой конструктор для Lombok
    public UserResponseDTO() {}
}