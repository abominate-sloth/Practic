package com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: автоматически генерирует геттеры, сеттеры, toString, equals и hashCode
@NoArgsConstructor // Lombok: генерирует конструктор без аргументов
@Entity // Указывает, что это сущность JPA
@Table(name = "reviews") // Указывает имя таблицы в базе данных
public class Review {

    @Id // Указывает, что это первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private int id;

    @ManyToOne // Указывает на связь многие-к-одному с Book
    @JoinColumn(name = "book_id", nullable = false) // Внешний ключ на таблицу Books
    private Book book;

    @ManyToOne // Указывает на связь многие-к-одному с User (читатель)
    @JoinColumn(name = "reader_id", nullable = false) // Внешний ключ на таблицу Users
    private User reader;

    @Column(name = "rating", nullable = false) // Рейтинг отзыва
    private int rating;

    @Column(name = "comment", columnDefinition = "TEXT") // Комментарий отзыва
    private String comment;

    // Конструктор с параметрами (Lombok не генерирует его автоматически)
    public Review(Book book, User reader, int rating, String comment) {
        this.book = book;
        this.reader = reader;
        this.rating = rating;
        this.comment = comment;
    }
}