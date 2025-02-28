package com.library.model;

import java.sql.Date;

public class Reader {
    private int id;
    private String name;
    private String email;
    private Date joinDate;

    // Конструкторы
    public Reader() {}

    public Reader(int id, String name, String email, Date joinDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.joinDate = joinDate;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}
