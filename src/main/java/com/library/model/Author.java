package com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data // Lombok: автоматически генерирует геттеры, сеттеры, toString, equals и hashCode
@NoArgsConstructor // Lombok: генерирует конструктор без аргументов
@Entity // Указывает, что это сущность JPA
@Table(name = "authors") // Указывает имя таблицы в базе данных
public class Author {

    @Id // Указывает, что это первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private int id;

    @Column(name = "name", nullable = false, length = 100) // Указывает имя столбца и его ограничения
    private String name;

    @Column(name = "birth_date") // Дата рождения автора
    private Date birthDate;

    // Конструктор с параметрами (Lombok не генерирует его автоматически)
    public Author(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
