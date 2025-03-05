package com.library.model;

import java.sql.Date;

public class IssueDetails {
    private int id;
    private BookDetails bookDetails; // Полная информация о книге
    private int readerId;
    private int employeeId;
    private Date issueDate;
    private Date returnDate;

    // Конструкторы
    public IssueDetails() {}

    public IssueDetails(int id, BookDetails bookDetails, int readerId, int employeeId, Date issueDate, Date returnDate) {
        this.id = id;
        this.bookDetails = bookDetails;
        this.readerId = readerId;
        this.employeeId = employeeId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public BookDetails getBookDetails() { return bookDetails; }

    public void setBookDetails(BookDetails bookDetails) { this.bookDetails = bookDetails; }

    public int getReaderId() { return readerId; }

    public void setReaderId(int readerId) { this.readerId = readerId; }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public Date getIssueDate() { return issueDate; }

    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }

    public Date getReturnDate() { return returnDate; }

    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return "IssueDetails{" +
                "id=" + id +
                ", bookDetails=" + bookDetails +
                ", readerId=" + readerId +
                ", employeeId=" + employeeId +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
