package com.library.servlets;

import com.library.dao.IssueDAO;
import com.library.model.Issue;
import com.library.model.IssueDetails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/issues")
public class IssueServlet extends HttpServlet {
    private IssueDAO issueDAO;

    @Override
    public void init() {
        issueDAO = new IssueDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Показать список выдач
            try {
                List<IssueDetails> issues = issueDAO.getAllIssues();
                request.setAttribute("issues", issues);
                request.getRequestDispatcher("/jsp/issues.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Ошибка при получении списка выдач", e);
            }
        } else if ("edit".equals(action)) {
            // Редактирование выдачи
            int id = Integer.parseInt(request.getParameter("id"));
            Issue issue = issueDAO.getIssueById(id); // Нужно добавить метод getIssueById в IssueDAO
            request.setAttribute("issue", issue);
            request.getRequestDispatcher("/jsp/editIssue.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // Удаление выдачи
            int id = Integer.parseInt(request.getParameter("id"));
            issueDAO.deleteIssue(id);
            response.sendRedirect(request.getContextPath() + "/issues");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Установка кодировки
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Добавление выдачи
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int readerId = Integer.parseInt(request.getParameter("readerId"));
            int employeeId = Integer.parseInt(request.getParameter("employeeId"));
            Date issueDate = Date.valueOf(request.getParameter("issueDate"));
            Date returnDate = request.getParameter("returnDate").isEmpty() ? null : Date.valueOf(request.getParameter("returnDate"));

            Issue issue = new Issue();
            issue.setBookId(bookId);
            issue.setReaderId(readerId);
            issue.setEmployeeId(employeeId);
            issue.setIssueDate(issueDate);
            issue.setReturnDate(returnDate);
            issueDAO.addIssue(issue);
        } else if ("update".equals(action)) {
            // Обновление выдачи
            int id = Integer.parseInt(request.getParameter("id"));
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int readerId = Integer.parseInt(request.getParameter("readerId"));
            int employeeId = Integer.parseInt(request.getParameter("employeeId"));
            Date issueDate = Date.valueOf(request.getParameter("issueDate"));
            Date returnDate = request.getParameter("returnDate").isEmpty() ? null : Date.valueOf(request.getParameter("returnDate"));

            Issue issue = new Issue(id, bookId, readerId, employeeId, issueDate, returnDate);
            issueDAO.updateIssue(issue);
        }

        response.sendRedirect(request.getContextPath() + "/issues");
    }
}