package com.library.model;

import java.util.List;

public class BookDetails {
    private int id;
    private String title;
    private String genre;
    private List<String> authors; // Список авторов

    // Конструкторы
    public BookDetails() {}

    public BookDetails(int id, String title, String genre, List<String> authors) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.authors = authors;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public List<String> getAuthors() { return authors; }

    public void setAuthors(List<String> authors) { this.authors = authors; }

    @Override
    public String toString() {
        return "BookDetails{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", authors=" + authors +
                '}';
    }
}