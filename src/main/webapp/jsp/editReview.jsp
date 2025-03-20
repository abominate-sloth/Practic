<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Review</title>
</head>
<body>
    <h1>Edit Review</h1>
    <form action="${pageContext.request.contextPath}/reviews" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${review.id}">
        Book ID: <input type="number" name="bookId" value="${review.bookId}" required><br>
        Reader ID: <input type="number" name="readerId" value="${review.readerId}" required><br>
        Rating: <input type="number" name="rating" value="${review.rating}" min="1" max="5" required><br>
        Comment: <textarea name="comment">${review.comment}</textarea><br>
        <input type="submit" value="Update Review">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/reviews">Back to Reviews</a>
</body>
</html>