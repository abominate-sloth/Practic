package com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: автоматически генерирует геттеры, сеттеры, toString, equals и hashCode
@NoArgsConstructor // Lombok: генерирует конструктор без аргументов
@Entity // Указывает, что это сущность JPA
@Table(name = "genres") // Указывает имя таблицы в базе данных
public class Genre {

    @Id // Указывает, что это первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private int id;

    @Column(name = "name", nullable = false, length = 100) // Указывает имя столбца и его ограничения
    private String name;

    // Конструктор с параметрами (Lombok не генерирует его автоматически)
    public Genre(String name) {
        this.name = name;
    }
}