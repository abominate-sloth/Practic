package com.library.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class AuthorResponseDTO {
    private int id;
    private String name;
    private Date birthDate;
}