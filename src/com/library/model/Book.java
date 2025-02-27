package com.library.model;

public class Book {
    private int id;
    private String title;
    private int authorId; // Внешний ключ на таблицу Authors
    private int genreId;  // Внешний ключ на таблицу Genres
    private int publishYear;
    private String isbn;

    // Конструкторы
    public Book() {}

    public Book(int id, String title, int authorId, int genreId, int publishYear, String isbn) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
        this.publishYear = publishYear;
        this.isbn = isbn;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public int getGenreId() { return genreId; }
    public void setGenreId(int genreId) { this.genreId = genreId; }

    public int getPublishYear() { return publishYear; }
    public void setPublishYear(int publishYear) { this.publishYear = publishYear; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", genreId=" + genreId +
                ", publishYear=" + publishYear +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
