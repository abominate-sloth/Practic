package com.library.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class AuthorRequestDTO {
    private String name;
    private Date birthDate;
}