<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reviews</title>
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        form {
            margin-top: 20px;
            background-color: #fff;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        input[type="number"], textarea, input[type="submit"] {
            padding: 10px;
            margin: 5px 0;
            width: 95%;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        textarea {
            height: 100px; /* Set height for the comment area */
            resize: vertical; /* Allow vertical resizing */
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