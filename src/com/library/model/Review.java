package com.library.model;

public class Review {
    private int id;
    private int bookId;    // Внешний ключ на таблицу Books
    private int readerId;  // Внешний ключ на таблицу Readers
    private int rating;
    private String comment;

    // Конструкторы
    public Review() {}

    public Review(int id, int bookId, int readerId, int rating, String comment) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.rating = rating;
        this.comment = comment;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getReaderId() { return readerId; }
    public void setReaderId(int readerId) { this.readerId = readerId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", readerId=" + readerId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
