<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reviews</title>
</head>
<body>
    <h1>Reviews</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Book ID</th>
            <th>Reader ID</th>
            <th>Rating</th>
            <th>Comment</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="review" items="${reviews}">
            <tr>
                <td>${review.id}</td>
                <td>${review.bookId}</td>
                <td>${review.readerId}</td>
                <td>${review.rating}</td>
                <td>${review.comment}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/reviews?action=edit&id=${review.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/reviews?action=delete&id=${review.id}"
                       onclick="return confirm('Are you sure you want to delete this review?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add Review</h2>
    <form action="${pageContext.request.contextPath}/reviews" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="add">
        Book ID: <input type="number" name="bookId" required><br>
        Reader ID: <input type="number" name="readerId" required><br>
        Rating: <input type="number" name="rating" min="1" max="5" required><br>
        Comment: <textarea name="comment"></textarea><br>
        <input type="submit" value="Add Review">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/main">Back to Main</a>
</body>
</html>