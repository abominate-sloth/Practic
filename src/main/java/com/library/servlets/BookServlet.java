package com.library.servlets;

import com.library.dao.BookDAO;
import com.library.model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Override
    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Показать список книг
            try {
                List<Book> books = bookDAO.getAllBooks();
                request.setAttribute("books", books);
                request.getRequestDispatcher("/jsp/books.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Ошибка при получении книг", e);
            }
        } else if ("edit".equals(action)) {
            // Редактирование книги
            int id = Integer.parseInt(request.getParameter("id"));
            Book book = bookDAO.getBookById(id); // Нужно добавить метод getBookById в BookDAO
            request.setAttribute("book", book);
            request.getRequestDispatcher("/jsp/editBook.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // Удаление книги
            int id = Integer.parseInt(request.getParameter("id"));
            bookDAO.deleteBook(id);
            response.sendRedirect(request.getContextPath() + "/books");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Установка кодировки
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Добавление книги
            String title = request.getParameter("title");
            Integer genreId = parseInteger(request.getParameter("genreId"));
            Integer publishYear = parseInteger(request.getParameter("publishYear"));
            String isbn = request.getParameter("isbn");
            int copiesAvailable = Integer.parseInt(request.getParameter("copiesAvailable"));

            Book book = new Book();
            book.setTitle(title);
            book.setGenreId(genreId);
            book.setPublishYear(publishYear);
            book.setIsbn(isbn);
            book.setCopiesAvailable(copiesAvailable);
            bookDAO.addBook(book);
        } else if ("update".equals(action)) {
            // Обновление книги
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            Integer genreId = parseInteger(request.getParameter("genreId"));
            Integer publishYear = parseInteger(request.getParameter("publishYear"));
            String isbn = request.getParameter("isbn");
            int copiesAvailable = Integer.parseInt(request.getParameter("copiesAvailable"));

            Book book = new Book(id, title, genreId, publishYear, isbn, copiesAvailable);
            bookDAO.updateBook(book);
        }

        response.sendRedirect(request.getContextPath() + "/books");
    }

    // Вспомогательный метод для преобразования строки в Integer (с учетом null)
    private Integer parseInteger(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return Integer.parseInt(value);
    }
}
