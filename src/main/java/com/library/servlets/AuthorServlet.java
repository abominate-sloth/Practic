package com.library.servlets;

import com.library.dao.AuthorDAO;
import com.library.model.Author;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {
    private AuthorDAO authorDAO;

    @Override
    public void init() {
        authorDAO = new AuthorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Показать список авторов
            try {
                List<Author> authors = authorDAO.getAllAuthors();
                request.setAttribute("authors", authors);
                request.getRequestDispatcher("/jsp/authors.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Ошибка при получении авторов", e);
            }
        } else if ("edit".equals(action)) {
            // Редактирование автора
            int id = Integer.parseInt(request.getParameter("id"));
            Author author = authorDAO.getAuthorById(id); // Нужно добавить метод getAuthorById в AuthorDAO
            request.setAttribute("author", author);
            request.getRequestDispatcher("/jsp/editAuthor.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // Удаление автора
            int id = Integer.parseInt(request.getParameter("id"));
            authorDAO.deleteAuthor(id);
            response.sendRedirect(request.getContextPath() + "/authors");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Установка кодировки
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Добавление автора
            String name = request.getParameter("name");
            String birthDateStr = request.getParameter("birthDate");
            Date birthDate = null;

            if (birthDateStr != null && !birthDateStr.isEmpty()) {
                birthDate = Date.valueOf(birthDateStr); // Преобразуем строку в java.sql.Date
            }

            Author author = new Author();
            author.setName(name);
            author.setBirthDate(birthDate);
            authorDAO.addAuthor(author);
        } else if ("update".equals(action)) {
            // Обновление автора
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String birthDateStr = request.getParameter("birthDate");
            Date birthDate = null;

            if (birthDateStr != null && !birthDateStr.isEmpty()) {
                birthDate = Date.valueOf(birthDateStr); // Преобразуем строку в java.sql.Date
            }

            Author author = new Author(id, name, birthDate);
            authorDAO.updateAuthor(author);
        }

        response.sendRedirect(request.getContextPath() + "/authors");
    }
}
