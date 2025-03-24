package com.library.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class UserResponseDTO {
    private int id;
    private String username;
    private String email;
    private Date joinDate;
    private RoleResponseDTO role; // Вложенный DTO для роли
}