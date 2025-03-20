package com.library.servlets;

import com.library.dao.GenreDAO;
import com.library.model.Genre;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/genres")
public class GenreServlet extends HttpServlet {
    private GenreDAO genreDAO;

    @Override
    public void init() {
        genreDAO = new GenreDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Показать список жанров
            try {
                List<Genre> genres = genreDAO.getAllGenres();
                request.setAttribute("genres", genres);
                request.getRequestDispatcher("/jsp/genres.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Ошибка при получении жанров", e);
            }
        } else if ("edit".equals(action)) {
            // Редактирование жанра
            int id = Integer.parseInt(request.getParameter("id"));
            Genre genre = genreDAO.getGenreById(id); // Нужно добавить метод getGenreById в GenreDAO
            request.setAttribute("genre", genre);
            request.getRequestDispatcher("/jsp/editGenre.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // Удаление жанра
            int id = Integer.parseInt(request.getParameter("id"));
            genreDAO.deleteGenre(id);
            response.sendRedirect(request.getContextPath() + "/genres");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Добавление жанра
            String name = request.getParameter("name");
            Genre genre = new Genre();
            genre.setName(name);
            genreDAO.addGenre(genre);
        } else if ("update".equals(action)) {
            // Обновление жанра
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Genre genre = new Genre(id, name);
            genreDAO.updateGenre(genre);
        }

        response.sendRedirect(request.getContextPath() + "/genres");
    }
}
