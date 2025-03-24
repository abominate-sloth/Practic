package com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data // Lombok: автоматически генерирует геттеры, сеттеры, toString, equals и hashCode
@NoArgsConstructor // Lombok: генерирует конструктор без аргументов
@Entity // Указывает, что это сущность JPA
@Table(name = "issues") // Указывает имя таблицы в базе данных
public class Issue {

    @Id // Указывает, что это первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private int id;

    @ManyToOne // Указывает на связь многие-к-одному с Book
    @JoinColumn(name = "book_id", nullable = false) // Внешний ключ на таблицу Books
    private Book book;

    @ManyToOne // Указывает на связь многие-к-одному с User (читатель)
    @JoinColumn(name = "reader_id", nullable = false) // Внешний ключ на таблицу Users
    private User reader;

    @ManyToOne // Указывает на связь многие-к-одному с User (сотрудник)
    @JoinColumn(name = "employee_id", nullable = false) // Внешний ключ на таблицу Users
    private User employee;

    @Column(name = "issue_date", nullable = false) // Дата выдачи книги
    private Date issueDate;

    @Column(name = "return_date") // Дата возврата книги
    private Date returnDate;

    // Конструктор с параметрами (Lombok не генерирует его автоматически)
    public Issue(Book book, User reader, User employee, Date issueDate, Date returnDate) {
        this.book = book;
        this.reader = reader;
        this.employee = employee;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
}