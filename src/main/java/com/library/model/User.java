package com.library.model;

import java.sql.Date;

public class User {
    private int id;
    private String username;         // Поле для логина
    private String passwordHash;     // Поле для хэша пароля
    private String email;
    private Date joinDate;
    private int roleId;             // Поле для идентификатора роли

    // Конструкторы
    public User() {}

    public User(int id, String username, String passwordHash, String email, Date joinDate, int roleId) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.joinDate = joinDate;
        this.roleId = roleId;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", joinDate=" + joinDate +
                ", roleId=" + roleId +
                '}';
    }
}