package com.library.main;

import com.library.dao.*;
import com.library.model.*;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String SUB_MENU ="1. Добавить запись\n" +
            "2. Показать все записи\n" +
            "3. Обновить запись\n" +
            "4. Удалить запись\n" +
            "5. Назад\n" +
            "Ваш выбор: \n";

    public static void main(String[] args) {
        ProcessMainMenu();
    }

    @SuppressWarnings("squid:S106")
    public static void ProcessMainMenu() {
        String menu ="Выберите таблицу для работы:\n" +
                "1. Авторы\n" +
                "2. Книги\n" +
                "3. Жанры\n" +
                "4. Читатели\n" +
                "5. Выдачи книг\n" +
                "6. Отзывы\n" +
                "7. Связь книг и авторов\n" +
                "8. Завершить работу\n" +
                "Ваш выбор: ";

        while (true) {
            System.out.print(menu);

            int choice = SCANNER.nextInt();
            SCANNER.nextLine(); // Очистка буфера

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
    @SuppressWarnings("squid:S106")
    private static void processAuthorMenu() {
        AuthorDAO authorDAO = new AuthorDAO();
        System.out.print("Работа с таблицей Авторы\n" + SUB_MENU);

        while (true) {
            int choice = SCANNER.nextInt();
            SCANNER.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите имя автора: ");
                    String name = SCANNER.nextLine();

                    System.out.print("Введите дату рождения автора (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String birthDateInput = SCANNER.nextLine();

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
                    int updateId = SCANNER.nextInt();
                    SCANNER.nextLine();
                    System.out.print("Введите новое имя автора: ");
                    String newName = SCANNER.nextLine();

                    System.out.print("Введите новую дату рождения автора (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String newBirthDateInput = SCANNER.nextLine();

                    // Если строка пустая, передаём null
                    Date newBirthDate = newBirthDateInput.isEmpty() ? null : Date.valueOf(newBirthDateInput);

                    Author updatedAuthor = new Author(updateId, newName, newBirthDate);
                    authorDAO.updateAuthor(updatedAuthor);
                    System.out.println("Автор успешно обновлён!");
                    break;
                case 4:
                    System.out.print("Введите ID автора для удаления: ");
                    int deleteId = SCANNER.nextInt();
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
    @SuppressWarnings("squid:S106")
    private static void processBookMenu() {
        BookDAO bookDAO = new BookDAO();
        System.out.print("Работа с таблицей Книги\n" + SUB_MENU);

        while (true) {
            int choice = SCANNER.nextInt();
            SCANNER.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите название книги: ");
                    String title = SCANNER.nextLine();

                    System.out.print("Введите ID автора (или оставьте пустым): ");
                    String authorIdInput = SCANNER.nextLine();
                    Integer authorId = authorIdInput.isEmpty() ? null : Integer.parseInt(authorIdInput);

                    System.out.print("Введите ID жанра (или оставьте пустым): ");
                    String genreIdInput = SCANNER.nextLine();
                    Integer genreId = genreIdInput.isEmpty() ? null : Integer.parseInt(genreIdInput);

                    System.out.print("Введите год публикации (или оставьте пустым): ");
                    String publishYearInput = SCANNER.nextLine();
                    Integer publishYear = publishYearInput.isEmpty() ? null : Integer.parseInt(publishYearInput);

                    System.out.print("Введите ISBN (или оставьте пустым): ");
                    String isbn = SCANNER.nextLine();
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
                    int updateId = SCANNER.nextInt();
                    SCANNER.nextLine();

                    System.out.print("Введите новое название книги: ");
                    String newTitle = SCANNER.nextLine();

                    System.out.print("Введите новый ID автора (или оставьте пустым): ");
                    String newAuthorIdInput = SCANNER.nextLine();
                    Integer newAuthorId = newAuthorIdInput.isEmpty() ? null : Integer.parseInt(newAuthorIdInput);

                    System.out.print("Введите новый ID жанра (или оставьте пустым): ");
                    String newGenreIdInput = SCANNER.nextLine();
                    Integer newGenreId = newGenreIdInput.isEmpty() ? null : Integer.parseInt(newGenreIdInput);

                    System.out.print("Введите новый год публикации (или оставьте пустым): ");
                    String newPublishYearInput = SCANNER.nextLine();
                    Integer newPublishYear = newPublishYearInput.isEmpty() ? null : Integer.parseInt(newPublishYearInput);

                    System.out.print("Введите новый ISBN (или оставьте пустым): ");
                    String newIsbn = SCANNER.nextLine();
                    String finalNewIsbn = newIsbn.isEmpty() ? null : newIsbn;

                    Book updatedBook = new Book(updateId, newTitle, newAuthorId, newGenreId, newPublishYear, finalNewIsbn);
                    bookDAO.updateBook(updatedBook);
                    System.out.println("Книга успешно обновлена!");
                    break;
                case 4:
                    System.out.print("Введите ID книги для удаления: ");
                    int deleteId = SCANNER.nextInt();
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

    @SuppressWarnings("squid:S106")
    private static void processGenreMenu() {
        GenreDAO genreDAO = new GenreDAO();
        System.out.print("Работа с таблицей Жанры\n" + SUB_MENU);

        while (true) {
            int choice = SCANNER.nextInt();
            SCANNER.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите название жанра: ");
                    String name = SCANNER.nextLine();

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
                    int updateId = SCANNER.nextInt();
                    SCANNER.nextLine();
                    System.out.print("Введите новое название жанра: ");
                    String newName = SCANNER.nextLine();

                    Genre updatedGenre = new Genre(updateId, newName);
                    genreDAO.updateGenre(updatedGenre);
                    System.out.println("Жанр успешно обновлён!");
                    break;
                case 4:
                    System.out.print("Введите ID жанра для удаления: ");
                    int deleteId = SCANNER.nextInt();
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

    @SuppressWarnings("squid:S106")
    private static void processReaderMenu() {
        ReaderDAO readerDAO = new ReaderDAO();
        System.out.print("Работа с таблицей Читателт\n" + SUB_MENU);

        while (true) {
            int choice = SCANNER.nextInt();
            SCANNER.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите имя читателя: ");
                    String name = SCANNER.nextLine();

                    System.out.print("Введите email читателя (или оставьте пустым): ");
                    String email = SCANNER.nextLine();
                    String finalEmail = email.isEmpty() ? null : email;

                    System.out.print("Введите дату регистрации (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String joinDateInput = SCANNER.nextLine();
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
                    int updateId = SCANNER.nextInt();
                    SCANNER.nextLine();

                    System.out.print("Введите новое имя читателя: ");
                    String newName = SCANNER.nextLine();

                    System.out.print("Введите новый email читателя (или оставьте пустым): ");
                    String newEmail = SCANNER.nextLine();
                    String finalNewEmail = newEmail.isEmpty() ? null : newEmail;

                    System.out.print("Введите новую дату регистрации (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String newJoinDateInput = SCANNER.nextLine();
                    Date newJoinDate = newJoinDateInput.isEmpty() ? null : Date.valueOf(newJoinDateInput);

                    Reader updatedReader = new Reader(updateId, newName, finalNewEmail, newJoinDate);
                    readerDAO.updateReader(updatedReader);
                    System.out.println("Читатель успешно обновлён!");
                    break;
                case 4:
                    System.out.print("Введите ID читателя для удаления: ");
                    int deleteId = SCANNER.nextInt();
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

    @SuppressWarnings("squid:S106")
    private static void processIssueMenu() {
        IssueDAO issueDAO = new IssueDAO();
        System.out.print("Работа с таблицей Выдачи книг\n" + SUB_MENU);

        while (true) {
            int choice = SCANNER.nextInt();
            SCANNER.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите ID книги: ");
                    int bookId = SCANNER.nextInt();

                    System.out.print("Введите ID читателя: ");
                    int readerId = SCANNER.nextInt();
                    SCANNER.nextLine(); // Очистка буфера

                    System.out.print("Введите дату выдачи (в формате ГГГГ-ММ-ДД): ");
                    String issueDateInput = SCANNER.nextLine();
                    Date issueDate = Date.valueOf(issueDateInput);

                    System.out.print("Введите дату возврата (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String returnDateInput = SCANNER.nextLine();
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
                    int updateId = SCANNER.nextInt();
                    SCANNER.nextLine();

                    System.out.print("Введите новый ID книги: ");
                    int newBookId = SCANNER.nextInt();

                    System.out.print("Введите новый ID читателя: ");
                    int newReaderId = SCANNER.nextInt();
                    SCANNER.nextLine(); // Очистка буфера

                    System.out.print("Введите новую дату выдачи (в формате ГГГГ-ММ-ДД): ");
                    String newIssueDateInput = SCANNER.nextLine();
                    Date newIssueDate = Date.valueOf(newIssueDateInput);

                    System.out.print("Введите новую дату возврата (в формате ГГГГ-ММ-ДД, или оставьте пустым): ");
                    String newReturnDateInput = SCANNER.nextLine();
                    Date newReturnDate = newReturnDateInput.isEmpty() ? null : Date.valueOf(newReturnDateInput);

                    Issue updatedIssue = new Issue(updateId, newBookId, newReaderId, newIssueDate, newReturnDate);
                    issueDAO.updateIssue(updatedIssue);
                    System.out.println("Выдача книги успешно обновлена!");
                    break;
                case 4:
                    System.out.print("Введите ID выдачи для удаления: ");
                    int deleteId = SCANNER.nextInt();
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

    @SuppressWarnings("squid:S106")
    private static void processReviewMenu() {
        ReviewDAO reviewDAO = new ReviewDAO();
        System.out.print("Работа с таблицей Отзывы\n" + SUB_MENU);


        while (true) {
            int choice = SCANNER.nextInt();
            SCANNER.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите ID книги: ");
                    int bookId = SCANNER.nextInt();

                    System.out.print("Введите ID читателя: ");
                    int readerId = SCANNER.nextInt();

                    System.out.print("Введите оценку (от 1 до 5): ");
                    int rating = SCANNER.nextInt();
                    SCANNER.nextLine(); // Очистка буфера

                    System.out.print("Введите комментарий (или оставьте пустым): ");
                    String comment = SCANNER.nextLine();
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
                    int updateId = SCANNER.nextInt();
                    SCANNER.nextLine();

                    System.out.print("Введите новый ID книги: ");
                    int newBookId = SCANNER.nextInt();

                    System.out.print("Введите новый ID читателя: ");
                    int newReaderId = SCANNER.nextInt();

                    System.out.print("Введите новую оценку (от 1 до 5): ");
                    int newRating = SCANNER.nextInt();
                    SCANNER.nextLine(); // Очистка буфера

                    System.out.print("Введите новый комментарий (или оставьте пустым): ");
                    String newComment = SCANNER.nextLine();
                    String finalNewComment = newComment.isEmpty() ? null : newComment;

                    Review updatedReview = new Review(updateId, newBookId, newReaderId, newRating, finalNewComment);
                    reviewDAO.updateReview(updatedReview);
                    System.out.println("Отзыв успешно обновлён!");
                    break;
                case 4:
                    System.out.print("Введите ID отзыва для удаления: ");
                    int deleteId = SCANNER.nextInt();
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

    @SuppressWarnings("squid:S106")
    private static void processBookAuthorMenu() {
        BookAuthorDAO bookAuthorDAO = new BookAuthorDAO();
        System.out.print("Работа с таблицей Связь Книг и Авторов\n" + SUB_MENU);


        while (true) {
            int choice = SCANNER.nextInt();
            SCANNER.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите ID книги: ");
                    int bookId = SCANNER.nextInt();

                    System.out.print("Введите ID автора: ");
                    int authorId = SCANNER.nextInt();
                    SCANNER.nextLine(); // Очистка буфера

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
                    System.out.print("Для данной таблицы доступны только добавление и удаление");
                    break;
                case 4:
                    System.out.print("Введите ID книги для удаления связи: ");
                    int deleteBookId = SCANNER.nextInt();

                    System.out.print("Введите ID автора для удаления связи: ");
                    int deleteAuthorId = SCANNER.nextInt();
                    SCANNER.nextLine(); // Очистка буфера

                    bookAuthorDAO.deleteBookAuthor(deleteBookId, deleteAuthorId);
                    System.out.println("Связь книги и автора успешно удалена!");
                    break;
                case 5:
                    return; // Возврат в главное меню
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}
