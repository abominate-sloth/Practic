package com.library.model;

import java.sql.Date;

public class Issue {
    private int id;
    private int bookId;    // Внешний ключ на таблицу Books
    private int readerId;  // Внешний ключ на таблицу Readers
    private int employeeId; // Внешний ключ на таблицу Employees
    private Date issueDate;
    private Date returnDate;

    // Конструкторы
    public Issue() {}

    public Issue(int id, int bookId, int readerId, int employeeId, Date issueDate, Date returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.employeeId = employeeId; // Инициализация ID сотрудника
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getReaderId() { return readerId; }
    public void setReaderId(int readerId) { this.readerId = readerId; }

    public int getEmployeeId() { return employeeId; } // Новый геттер
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; } // Новый сеттер

    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }

    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", readerId=" + readerId +
                ", employeeId=" + employeeId + // Добавлено в toString
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}