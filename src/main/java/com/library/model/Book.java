package com.library.model;

public class Book {
    private int id;
    private String title;
    private Integer genreId;  // Внешний ключ на таблицу Genres
    private Integer publishYear;
    private String isbn;
    private int copiesAvailable; // Количество доступных экземпляров книги

    // Конструкторы
    public Book() {}

    public Book(int id, String title, Integer genreId, Integer publishYear, String isbn, int copiesAvailable) {
        this.id = id;
        this.title = title;
        this.genreId = genreId;
        this.publishYear = publishYear;
        this.isbn = isbn;
        this.copiesAvailable = copiesAvailable;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Integer getGenreId() { return genreId; }

    public void setGenreId(Integer genreId) { this.genreId = genreId; }

    public Integer getPublishYear() { return publishYear; }

    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getCopiesAvailable() { return copiesAvailable; }

    public void setCopiesAvailable(int copiesAvailable) { this.copiesAvailable = copiesAvailable; }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genreId=" + genreId +
                ", publishYear=" + publishYear +
                ", isbn='" + isbn + '\'' +
                ", copiesAvailable=" + copiesAvailable +
                '}';
    }
}