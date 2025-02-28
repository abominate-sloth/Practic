package com.library.main;

import com.library.dao.*;
import com.library.model.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ProcessMainMenu();
    }

    public static void ProcessMainMenu() {
        while (true) {
            System.out.println("Выберите таблицу для работы:");
            System.out.println("1. Авторы");
            System.out.println("2. Книги");
            System.out.println("3. Жанры");
            System.out.println("4. Читатели");
            System.out.println("5. Выдачи книг");
            System.out.println("6. Отзывы");
            System.out.println("7. Связь книг и авторов");
            System.out.println("8. Завершить работу");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    processAuthorMenu();
                    break;
                case 2:
                    processBookMenu();
                    break;
                case 3:
                    processGenreMenu();
                    break;
                case 4:
                    processReaderMenu();
                    break;
                case 5:
                    processIssueMenu();
                    break;
                case 6:
                    processReviewMenu();
                    break;
                case 7:
                    processBookAuthorMenu();
                    break;
                case 8:
                    System.out.println("Завершение работы программы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Методы для обработки меню каждой таблицы
    private static void processAuthorMenu() {
        AuthorDAO authorDAO = new AuthorDAO();
        System.out.println("Работа с таблицей Авторы:");
        System.out.println("1. Добавить автора");
        System.out.println("2. Показать всех авторов");
        System.out.println("3. Обновить автора");
        System.out.println("4. Удалить автора");
        System.out.println("5. Назад");
        System.out.print("Ваш выбор: ");

        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите имя автора: ");
                    String name = scanner.nextLine();

                    System.out.print("Введите дату рождения автора (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String birthDateInput = scanner.nextLine();

                    // Если строка пустая, передаём null
                    Date birthDate = birthDateInput.isEmpty() ? null : Date.valueOf(birthDateInput);

                    Author author = new Author(0, name, birthDate);
                    authorDAO.addAuthor(author);
                    System.out.println("Автор успешно добавлен!");
                    break;
                case 2:
                    System.out.println("Список всех авторов:");
                    for (Author a : authorDAO.getAllAuthors()) {
                        System.out.println(a);
                    }
                    break;
                case 3:
                    System.out.print("Введите ID автора для обновления: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите новое имя автора: ");
                    String newName = scanner.nextLine();

                    System.out.print("Введите новую дату рождения автора (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String newBirthDateInput = scanner.nextLine();

                    // Если строка пустая, передаём null
                    Date newBirthDate = newBirthDateInput.isEmpty() ? null : Date.valueOf(newBirthDateInput);

                    Author updatedAuthor = new Author(updateId, newName, newBirthDate);
                    authorDAO.updateAuthor(updatedAuthor);
                    System.out.println("Автор успешно обновлён!");
                    break;
                case 4:
                    System.out.print("Введите ID автора для удаления: ");
                    int deleteId = scanner.nextInt();
                    authorDAO.deleteAuthor(deleteId);
                    System.out.println("Автор успешно удалён!");
                    break;
                case 5:
                    return; // Возврат в главное меню
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void processBookMenu() {
        BookDAO bookDAO = new BookDAO();
        System.out.println("Работа с таблицей Книги:");
        System.out.println("1. Добавить книгу");
        System.out.println("2. Показать все книги");
        System.out.println("3. Обновить книгу");
        System.out.println("4. Удалить книгу");
        System.out.println("5. Назад");
        System.out.print("Ваш выбор: ");

        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите название книги: ");
                    String title = scanner.nextLine();

                    System.out.print("Введите ID автора (или оставьте пустым): ");
                    String authorIdInput = scanner.nextLine();
                    Integer authorId = authorIdInput.isEmpty() ? null : Integer.parseInt(authorIdInput);

                    System.out.print("Введите ID жанра (или оставьте пустым): ");
                    String genreIdInput = scanner.nextLine();
                    Integer genreId = genreIdInput.isEmpty() ? null : Integer.parseInt(genreIdInput);

                    System.out.print("Введите год публикации (или оставьте пустым): ");
                    String publishYearInput = scanner.nextLine();
                    Integer publishYear = publishYearInput.isEmpty() ? null : Integer.parseInt(publishYearInput);

                    System.out.print("Введите ISBN (или оставьте пустым): ");
                    String isbn = scanner.nextLine();
                    String finalIsbn = isbn.isEmpty() ? null : isbn;

                    Book book = new Book(0, title, authorId, genreId, publishYear, finalIsbn);
                    System.out.println("Добавляем книгу....");
                    bookDAO.addBook(book);
                    System.out.println("Книга успешно добавлена!");
                    break;
                case 2:
                    System.out.println("Список всех книг:");
                    for (Book b : bookDAO.getAllBooks()) {
                        System.out.println(b);
                    }
                    break;
                case 3:
                    System.out.print("Введите ID книги для обновления: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Введите новое название книги: ");
                    String newTitle = scanner.nextLine();

                    System.out.print("Введите новый ID автора (или оставьте пустым): ");
                    String newAuthorIdInput = scanner.nextLine();
                    Integer newAuthorId = newAuthorIdInput.isEmpty() ? null : Integer.parseInt(newAuthorIdInput);

                    System.out.print("Введите новый ID жанра (или оставьте пустым): ");
                    String newGenreIdInput = scanner.nextLine();
                    Integer newGenreId = newGenreIdInput.isEmpty() ? null : Integer.parseInt(newGenreIdInput);

                    System.out.print("Введите новый год публикации (или оставьте пустым): ");
                    String newPublishYearInput = scanner.nextLine();
                    Integer newPublishYear = newPublishYearInput.isEmpty() ? null : Integer.parseInt(newPublishYearInput);

                    System.out.print("Введите новый ISBN (или оставьте пустым): ");
                    String newIsbn = scanner.nextLine();
                    String finalNewIsbn = newIsbn.isEmpty() ? null : newIsbn;

                    Book updatedBook = new Book(updateId, newTitle, newAuthorId, newGenreId, newPublishYear, finalNewIsbn);
                    bookDAO.updateBook(updatedBook);
                    System.out.println("Книга успешно обновлена!");
                    break;
                case 4:
                    System.out.print("Введите ID книги для удаления: ");
                    int deleteId = scanner.nextInt();
                    bookDAO.deleteBook(deleteId);
                    System.out.println("Книга успешно удалена!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void processGenreMenu() {
        GenreDAO genreDAO = new GenreDAO();
        System.out.println("1. Добавить жанр");
        System.out.println("2. Показать все жанры");
        System.out.println("3. Обновить жанр");
        System.out.println("4. Удалить жанр");
        System.out.println("5. Назад");
        System.out.print("Ваш выбор: ");

        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите название жанра: ");
                    String name = scanner.nextLine();

                    Genre genre = new Genre(0, name);
                    genreDAO.addGenre(genre);
                    System.out.println("Жанр успешно добавлен!");
                    break;
                case 2:
                    System.out.println("Список всех жанров:");
                    for (Genre g : genreDAO.getAllGenres()) {
                        System.out.println(g);
                    }
                    break;
                case 3:
                    System.out.print("Введите ID жанра для обновления: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите новое название жанра: ");
                    String newName = scanner.nextLine();

                    Genre updatedGenre = new Genre(updateId, newName);
                    genreDAO.updateGenre(updatedGenre);
                    System.out.println("Жанр успешно обновлён!");
                    break;
                case 4:
                    System.out.print("Введите ID жанра для удаления: ");
                    int deleteId = scanner.nextInt();
                    genreDAO.deleteGenre(deleteId);
                    System.out.println("Жанр успешно удалён!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void processReaderMenu() {
        ReaderDAO readerDAO = new ReaderDAO();
        System.out.println("1. Добавить читателя");
        System.out.println("2. Показать всех читателей");
        System.out.println("3. Обновить читателя");
        System.out.println("4. Удалить читателя");
        System.out.println("5. Назад");
        System.out.print("Ваш выбор: ");

        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите имя читателя: ");
                    String name = scanner.nextLine();

                    System.out.print("Введите email читателя (или оставьте пустым): ");
                    String email = scanner.nextLine();
                    String finalEmail = email.isEmpty() ? null : email;

                    System.out.print("Введите дату регистрации (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String joinDateInput = scanner.nextLine();
                    Date joinDate = joinDateInput.isEmpty() ? null : Date.valueOf(joinDateInput);

                    Reader reader = new Reader(0, name, finalEmail, joinDate);
                    readerDAO.addReader(reader);
                    System.out.println("Читатель успешно добавлен!");
                    break;
                case 2:
                    System.out.println("Список всех читателей:");
                    for (Reader r : readerDAO.getAllReaders()) {
                        System.out.println(r);
                    }
                    break;
                case 3:
                    System.out.print("Введите ID читателя для обновления: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Введите новое имя читателя: ");
                    String newName = scanner.nextLine();

                    System.out.print("Введите новый email читателя (или оставьте пустым): ");
                    String newEmail = scanner.nextLine();
                    String finalNewEmail = newEmail.isEmpty() ? null : newEmail;

                    System.out.print("Введите новую дату регистрации (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String newJoinDateInput = scanner.nextLine();
                    Date newJoinDate = newJoinDateInput.isEmpty() ? null : Date.valueOf(newJoinDateInput);

                    Reader updatedReader = new Reader(updateId, newName, finalNewEmail, newJoinDate);
                    readerDAO.updateReader(updatedReader);
                    System.out.println("Читатель успешно обновлён!");
                    break;
                case 4:
                    System.out.print("Введите ID читателя для удаления: ");
                    int deleteId = scanner.nextInt();
                    readerDAO.deleteReader(deleteId);
                    System.out.println("Читатель успешно удалён!");
                    break;
                case 5:
                    return; // Возврат в главное меню
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void processIssueMenu() {
        IssueDAO issueDAO = new IssueDAO();
        System.out.println("1. Добавить выдачу книги");
        System.out.println("2. Показать все выдачи книг");
        System.out.println("3. Обновить выдачу книги");
        System.out.println("4. Удалить выдачу книги");
        System.out.println("5. Назад");
        System.out.print("Ваш выбор: ");

        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите ID книги: ");
                    int bookId = scanner.nextInt();

                    System.out.print("Введите ID читателя: ");
                    int readerId = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера

                    System.out.print("Введите дату выдачи (в формате ГГГГ-ММ-ДД): ");
                    String issueDateInput = scanner.nextLine();
                    Date issueDate = Date.valueOf(issueDateInput);

                    System.out.print("Введите дату возврата (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String returnDateInput = scanner.nextLine();
                    Date returnDate = returnDateInput.isEmpty() ? null : Date.valueOf(returnDateInput);

                    Issue issue = new Issue(0, bookId, readerId, issueDate, returnDate);
                    issueDAO.addIssue(issue);
                    System.out.println("Выдача книги успешно добавлена!");
                    break;
                case 2:
                    System.out.println("Список всех выдач книг:");
                    for (Issue i : issueDAO.getAllIssues()) {
                        System.out.println(i);
                    }
                    break;
                case 3:
                    System.out.print("Введите ID выдачи для обновления: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Введите новый ID книги: ");
                    int newBookId = scanner.nextInt();

                    System.out.print("Введите новый ID читателя: ");
                    int newReaderId = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера

                    System.out.print("Введите новую дату выдачи (в формате ГГГГ-ММ-ДД): ");
                    String newIssueDateInput = scanner.nextLine();
                    Date newIssueDate = Date.valueOf(newIssueDateInput);

                    System.out.print("Введите новую дату возврата (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String newReturnDateInput = scanner.nextLine();
                    Date newReturnDate = newReturnDateInput.isEmpty() ? null : Date.valueOf(newReturnDateInput);

                    Issue updatedIssue = new Issue(updateId, newBookId, newReaderId, newIssueDate, newReturnDate);
                    issueDAO.updateIssue(updatedIssue);
                    System.out.println("Выдача книги успешно обновлена!");
                    break;
                case 4:
                    System.out.print("Введите ID выдачи для удаления: ");
                    int deleteId = scanner.nextInt();
                    issueDAO.deleteIssue(deleteId);
                    System.out.println("Выдача книги успешно удалена!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }


    private static void processReviewMenu() {
        ReviewDAO reviewDAO = new ReviewDAO();
        System.out.println("1. Добавить отзыв");
        System.out.println("2. Показать все отзывы");
        System.out.println("3. Обновить отзыв");
        System.out.println("4. Удалить отзыв");
        System.out.println("5. Назад");
        System.out.print("Ваш выбор: ");

        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите ID книги: ");
                    int bookId = scanner.nextInt();

                    System.out.print("Введите ID читателя: ");
                    int readerId = scanner.nextInt();

                    System.out.print("Введите оценку (от 1 до 5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера

                    System.out.print("Введите комментарий (или оставьте пустым): ");
                    String comment = scanner.nextLine();
                    String finalComment = comment.isEmpty() ? null : comment;

                    Review review = new Review(0, bookId, readerId, rating, finalComment);
                    reviewDAO.addReview(review);
                    System.out.println("Отзыв успешно добавлен!");
                    break;
                case 2:
                    System.out.println("Список всех отзывов:");
                    for (Review r : reviewDAO.getAllReviews()) {
                        System.out.println(r);
                    }
                    break;
                case 3:
                    System.out.print("Введите ID отзыва для обновления: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Введите новый ID книги: ");
                    int newBookId = scanner.nextInt();

                    System.out.print("Введите новый ID читателя: ");
                    int newReaderId = scanner.nextInt();

                    System.out.print("Введите новую оценку (от 1 до 5): ");
                    int newRating = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера

                    System.out.print("Введите новый комментарий (или оставьте пустым): ");
                    String newComment = scanner.nextLine();
                    String finalNewComment = newComment.isEmpty() ? null : newComment;

                    Review updatedReview = new Review(updateId, newBookId, newReaderId, newRating, finalNewComment);
                    reviewDAO.updateReview(updatedReview);
                    System.out.println("Отзыв успешно обновлён!");
                    break;
                case 4:
                    System.out.print("Введите ID отзыва для удаления: ");
                    int deleteId = scanner.nextInt();
                    reviewDAO.deleteReview(deleteId);
                    System.out.println("Отзыв успешно удалён!");
                    break;
                case 5:
                    return; // Возврат в главное меню
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void processBookAuthorMenu() {
        BookAuthorDAO bookAuthorDAO = new BookAuthorDAO();
        System.out.println("Работа с таблицей Связь книг и авторов:");
        System.out.println("1. Добавить связь книги и автора");
        System.out.println("2. Показать все связи книг и авторов");
        System.out.println("3. Удалить связь книги и автора");
        System.out.println("4. Назад");
        System.out.print("Ваш выбор: ");

        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите ID книги: ");
                    int bookId = scanner.nextInt();

                    System.out.print("Введите ID автора: ");
                    int authorId = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера

                    BookAuthor bookAuthor = new BookAuthor(bookId, authorId);
                    bookAuthorDAO.addBookAuthor(bookAuthor);
                    System.out.println("Связь книги и автора успешно добавлена!");
                    break;
                case 2:
                    System.out.println("Список всех связей книг и авторов:");
                    for (BookAuthor ba : bookAuthorDAO.getAllBookAuthors()) {
                        System.out.println(ba);
                    }
                    break;
                case 3:
                    System.out.print("Введите ID книги для удаления связи: ");
                    int deleteBookId = scanner.nextInt();

                    System.out.print("Введите ID автора для удаления связи: ");
                    int deleteAuthorId = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера

                    bookAuthorDAO.deleteBookAuthor(deleteBookId, deleteAuthorId);
                    System.out.println("Связь книги и автора успешно удалена!");
                    break;
                case 4:
                    return; // Возврат в главное меню
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}
