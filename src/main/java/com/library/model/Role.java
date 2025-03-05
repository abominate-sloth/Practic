package com.library.model;

public class Role {
    private int id;
    private String roleName;

    // Конструкторы
    public Role() {}

    public Role(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getRoleName() { return roleName; }

    public void setRoleName(String roleName) { this.roleName = roleName; }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
