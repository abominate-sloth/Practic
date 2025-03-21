<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Review</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
        }

        form {
            margin-top: 20px;
            background-color: #fff;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        input[type="number"], input[type="submit"], textarea {
            padding: 10px;
            margin: 5px 0;
            width: 95%;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        textarea {
            height: 100px; /* Установите высоту для текстовой области */
            resize: vertical; /* Позволяет изменять размер по вертикали */
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        a {
            text-decoration: none;
            color: #4CAF50;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
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