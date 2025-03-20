package com.library.servlets;

import com.library.dao.BookAuthorDAO;
import com.library.model.BookAuthor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookauthors")
public class BookAuthorServlet extends HttpServlet {
    private BookAuthorDAO bookAuthorDAO;

    @Override
    public void init() {
        bookAuthorDAO = new BookAuthorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Показать список связей
            try {
                List<BookAuthor> bookAuthors = bookAuthorDAO.getAllBookAuthors();
                request.setAttribute("bookAuthors", bookAuthors);
                request.getRequestDispatcher("/jsp/bookauthors.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Ошибка при получении списка связей", e);
            }
        } else if ("delete".equals(action)) {
            // Удаление связи
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int authorId = Integer.parseInt(request.getParameter("authorId"));
            bookAuthorDAO.deleteBookAuthor(bookId, authorId);
            response.sendRedirect(request.getContextPath() + "/bookauthors");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Установка кодировки
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Добавление связи
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int authorId = Integer.parseInt(request.getParameter("authorId"));

            BookAuthor bookAuthor = new BookAuthor(bookId, authorId);
            bookAuthorDAO.addBookAuthor(bookAuthor);
        }

        response.sendRedirect(request.getContextPath() + "/bookauthors");
    }
}