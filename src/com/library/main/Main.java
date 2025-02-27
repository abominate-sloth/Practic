package com.library.main;

import com.library.dao.AuthorDAO;
import com.library.model.Author;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthorDAO authorDAO = new AuthorDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Добавить автора");
            System.out.println("2. Показать всех авторов");
            System.out.println("3. Обновить автора");
            System.out.println("4. Удалить автора");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите имя автора: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите дату рождения автора(в формате ГГГГ-ММ-ДД): ");
                    String birthDateInput = scanner.nextLine();

                    try {
                        Date birthDate = Date.valueOf(birthDateInput);
                        authorDAO.addAuthor(new Author(0, name, birthDate));
                        System.out.println("Автор успешно добавлен!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: Неверный формат даты. Используйте формат ГГГГ-ММ-ДД.");
                    }

                    break;
                case 2:
                    List<Author> authors = authorDAO.getAllAuthors();
                    for (Author author : authors) {
                        System.out.println(author);
                    }
                    break;
                case 3:
                    System.out.print("Введите ID автора для обновления: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите новое имя автора: ");
                    String newName = scanner.nextLine();
                    System.out.print("Введите новую дату рождения автора: ");
                    String newBirthDate = scanner.nextLine();
                    authorDAO.updateAuthor(new Author(updateId, newName, null));
                    break;
                case 4:
                    System.out.print("Введите ID автора для удаления: ");
                    int deleteId = scanner.nextInt();
                    authorDAO.deleteAuthor(deleteId);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}
