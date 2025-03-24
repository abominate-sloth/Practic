package com.library.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class IssueRequestDTO {
    private Integer bookId;
    private Integer readerId;
    private Integer employeeId;
    private Date issueDate;
    private Date returnDate;
}