package com.library.servlets;

import com.library.dao.ReviewDAO;
import com.library.model.Review;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/reviews")
public class ReviewServlet extends HttpServlet {
    private ReviewDAO reviewDAO;

    @Override
    public void init() {
        reviewDAO = new ReviewDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Показать список отзывов
            try {
                List<Review> reviews = reviewDAO.getAllReviews();
                request.setAttribute("reviews", reviews);
                request.getRequestDispatcher("/jsp/reviews.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Ошибка при получении отзывов", e);
            }
        } else if ("edit".equals(action)) {
            // Редактирование отзыва
            int id = Integer.parseInt(request.getParameter("id"));
            Review review = reviewDAO.getReviewById(id);
            request.setAttribute("review", review);
            request.getRequestDispatcher("/jsp/editReview.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // Удаление отзыва
            int id = Integer.parseInt(request.getParameter("id"));
            reviewDAO.deleteReview(id);
            response.sendRedirect(request.getContextPath() + "/reviews");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Установка кодировки
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Добавление отзыва
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int readerId = Integer.parseInt(request.getParameter("readerId"));
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comment = request.getParameter("comment"); // Комментарий может быть null

            Review review = new Review();
            review.setBookId(bookId);
            review.setReaderId(readerId);
            review.setRating(rating);
            review.setComment(comment);
            reviewDAO.addReview(review);
        } else if ("update".equals(action)) {
            // Обновление отзыва
            int id = Integer.parseInt(request.getParameter("id"));
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int readerId = Integer.parseInt(request.getParameter("readerId"));
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comment = request.getParameter("comment"); // Комментарий может быть null

            Review review = new Review(id, bookId, readerId, rating, comment);
            reviewDAO.updateReview(review);
        }

        response.sendRedirect(request.getContextPath() + "/reviews");
    }
}