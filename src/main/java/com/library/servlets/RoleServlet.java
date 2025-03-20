package com.library.servlets;

import com.library.dao.RoleDAO;
import com.library.model.Role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/roles")
public class RoleServlet extends HttpServlet {
    private RoleDAO roleDAO;

    @Override
    public void init() {
        roleDAO = new RoleDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Показать список ролей
            try {
                List<Role> roles = roleDAO.getAllRoles();
                request.setAttribute("roles", roles);
                request.getRequestDispatcher("/jsp/roles.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Ошибка при получении ролей", e);
            }
        } else if ("edit".equals(action)) {
            // Редактирование роли
            int id = Integer.parseInt(request.getParameter("id"));
            Role role = roleDAO.getRoleById(id); // Нужно добавить метод getRoleById в RoleDAO
            request.setAttribute("role", role);
            request.getRequestDispatcher("/jsp/editRole.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // Удаление роли
            int id = Integer.parseInt(request.getParameter("id"));
            roleDAO.deleteRole(id);
            response.sendRedirect(request.getContextPath() + "/roles");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Установка кодировки
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Добавление роли
            String roleName = request.getParameter("roleName");
            Role role = new Role();
            role.setRoleName(roleName);
            roleDAO.addRole(role);
        } else if ("update".equals(action)) {
            // Обновление роли
            int id = Integer.parseInt(request.getParameter("id"));
            String roleName = request.getParameter("roleName");
            Role role = new Role(id, roleName);
            roleDAO.updateRole(role);
        }

        response.sendRedirect(request.getContextPath() + "/roles");
    }
}