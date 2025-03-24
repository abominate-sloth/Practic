package com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data // Lombok: автоматически генерирует геттеры, сеттеры, toString, equals и hashCode
@NoArgsConstructor // Lombok: генерирует конструктор без аргументов
@Entity // Указывает, что это сущность JPA
@Table(name = "users") // Указывает имя таблицы в базе данных
public class User {

    @Id // Указывает, что это первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private int id;

    @Column(name = "username", nullable = false, unique = true, length = 100) // Указывает имя столбца и его ограничения
    private String username;

    @Column(name = "password_hash", nullable = false, length = 255) // Хэш пароля
    private String passwordHash;

    @Column(name = "email", length = 100) // Электронная почта
    private String email;

    @Column(name = "join_date") // Дата регистрации
    private Date joinDate;

    @ManyToOne // Указывает на связь многие-к-одному с Role
    @JoinColumn(name = "role_id", nullable = false) // Внешний ключ на таблицу Roles
    private Role role;

    // Конструктор с параметрами (Lombok не генерирует его автоматически)
    public User(String username, String passwordHash, String email, Date joinDate, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.joinDate = joinDate;
        this.role = role;
    }
}