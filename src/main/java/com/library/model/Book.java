package com.library.model;

public class Book {
    private int id;
    private String title;
    private Integer authorId; // Внешний ключ на таблицу Authors
    private Integer genreId;  // Внешний ключ на таблицу Genres
    private Integer publishYear;
    private String isbn;

    // Конструкторы
    public Book() {}

    public Book(int id, String title, Integer authorId, Integer genreId, Integer publishYear, String isbn) {
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

    public Integer getAuthorId() { return authorId; }
    public void setAuthorId(Integer authorId) { this.authorId = authorId; }

    public Integer getGenreId() { return genreId; }
    public void setGenreId(Integer genreId) { this.genreId = genreId; }

    public Integer getPublishYear() { return publishYear; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }

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
