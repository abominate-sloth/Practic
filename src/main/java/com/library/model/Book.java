package com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data // Lombok: автоматически генерирует геттеры, сеттеры, toString, equals и hashCode
@NoArgsConstructor // Lombok: генерирует конструктор без аргументов
@Entity // Указывает, что это сущность JPA
@Table(name = "books") // Указывает имя таблицы в базе данных
public class Book {

    @Id // Указывает, что это первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private int id;

    @Column(name = "title", nullable = false, length = 255) // Указывает имя столбца и его ограничения
    private String title;

    @ManyToOne // Указывает на связь многие-к-одному с Genre
    @JoinColumn(name = "genre_id", nullable = true) // Внешний ключ на таблицу Genres
    private Genre genre;

    @Column(name = "publish_year") // Год публикации
    private Integer publishYear;

    @Column(name = "isbn", length = 20) // ISBN книги
    private String isbn;

    @Column(name = "copies_available", nullable = false) // Количество доступных экземпляров
    private int copiesAvailable;

    @ManyToMany // Указывает на связь многие-ко-многим с Author
    @JoinTable(
            name = "bookauthors", // Имя связующей таблицы
            joinColumns = @JoinColumn(name = "book_id"), // Внешний ключ на таблицу Books
            inverseJoinColumns = @JoinColumn(name = "author_id") // Внешний ключ на таблицу Authors
    )
    private Set<Author> authors;

    // Конструктор с параметрами (Lombok не генерирует его автоматически)
    public Book(String title, Genre genre, Integer publishYear, String isbn, int copiesAvailable) {
        this.title = title;
        this.genre = genre;
        this.publishYear = publishYear;
        this.isbn = isbn;
        this.copiesAvailable = copiesAvailable;
    }
}