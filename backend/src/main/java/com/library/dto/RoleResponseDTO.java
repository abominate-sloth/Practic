package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Добавляем аннотацию для генерации конструктора
public class RoleResponseDTO {
    private int id;
    private String roleName;

    // Добавляем пустой конструктор для Lombok
    public RoleResponseDTO() {}
}