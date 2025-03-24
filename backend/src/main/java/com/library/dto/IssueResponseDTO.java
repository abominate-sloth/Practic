package com.library.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class IssueResponseDTO {
    private Integer id;
    private BookSimpleDTO book;
    private UserSimpleDTO reader;
    private UserSimpleDTO employee;
    private Date issueDate;
    private Date returnDate;
}