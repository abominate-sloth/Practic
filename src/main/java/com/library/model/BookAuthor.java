package com.library.model;

public class BookAuthor {
    private int bookId;    // Внешний ключ на таблицу Books
    private int authorId;  // Внешний ключ на таблицу Authors

    // Конструкторы
    public BookAuthor() {}

    public BookAuthor(int bookId, int authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    // Геттеры и сеттеры
    public int getBookId() { return bookId; }

    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getAuthorId() { return authorId; }

    public void setAuthorId(int authorId) { this.authorId = authorId; }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "bookId=" + bookId +
                ", authorId=" + authorId +
                '}';
    }
}
