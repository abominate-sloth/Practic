package com.library.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class UserRequestDTO {
    private String username;
    private String passwordHash;
    private String email;
    private Date joinDate;
    private int roleId; // Теперь используем roleId вместо Role
}