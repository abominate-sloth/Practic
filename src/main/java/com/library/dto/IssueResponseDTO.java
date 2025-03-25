package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Date;

@Data
@AllArgsConstructor // Добавляем аннотацию для генерации конструктора
public class IssueResponseDTO {
    private Integer id;
    private BookSimpleDTO book;
    private UserSimpleDTO reader;
    private UserSimpleDTO employee;
    private Date issueDate;
    private Date returnDate;

    // Добавляем пустой конструктор для Lombok
    public IssueResponseDTO() {}
}