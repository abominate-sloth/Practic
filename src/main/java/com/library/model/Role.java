package com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: автоматически генерирует геттеры, сеттеры, toString, equals и hashCode
@NoArgsConstructor // Lombok: генерирует конструктор без аргументов
@Entity // Указывает, что это сущность JPA
@Table(name = "roles") // Указывает имя таблицы в базе данных
public class Role {

    @Id // Указывает, что это первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private int id;

    @Column(name = "role_name", nullable = false, unique = true, length = 50) // Указывает имя столбца и его ограничения
    private String roleName;

    // Конструктор с параметрами (Lombok не генерирует его автоматически)
    public Role(String roleName) {
        this.roleName = roleName;
    }
}