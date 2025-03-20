package com.library.servlets;

import com.library.dao.UserDAO;
import com.library.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Показать список пользователей
            try {
                List<User> users = userDAO.getAllUsers();
                request.setAttribute("users", users);
                request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Ошибка при получении пользователей", e);
            }
        } else if ("edit".equals(action)) {
            // Редактирование пользователя
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userDAO.getUserById(id); // Нужно добавить метод getUserById в UserDAO
            request.setAttribute("user", user);
            request.getRequestDispatcher("/jsp/editUser.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // Удаление пользователя
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.deleteUser(id);
            response.sendRedirect(request.getContextPath() + "/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Установка кодировки
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Добавление пользователя
            String username = request.getParameter("username");
            String passwordHash = request.getParameter("passwordHash"); // Хэш пароля
            String email = request.getParameter("email");
            Date joinDate = Date.valueOf(request.getParameter("joinDate"));
            int roleId = Integer.parseInt(request.getParameter("roleId"));

            User user = new User();
            user.setUsername(username);
            user.setPasswordHash(passwordHash);
            user.setEmail(email);
            user.setJoinDate(joinDate);
            user.setRoleId(roleId);
            userDAO.addUser(user);
        } else if ("update".equals(action)) {
            // Обновление пользователя
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String passwordHash = request.getParameter("passwordHash"); // Хэш пароля
            String email = request.getParameter("email");
            Date joinDate = Date.valueOf(request.getParameter("joinDate"));
            int roleId = Integer.parseInt(request.getParameter("roleId"));

            User user = new User(id, username, passwordHash, email, joinDate, roleId);
            userDAO.updateUser(user);
        }

        response.sendRedirect(request.getContextPath() + "/users");
    }
}