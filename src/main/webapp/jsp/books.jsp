<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
    <h1>Books</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Genre ID</th>
            <th>Publish Year</th>
            <th>ISBN</th>
            <th>Copies Available</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.genreId}</td>
                <td>${book.publishYear}</td>
                <td>${book.isbn}</td>
                <td>${book.copiesAvailable}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/books?action=edit&id=${book.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/books?action=delete&id=${book.id}"
                       onclick="return confirm('Are you sure you want to delete this book?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add Book</h2>
    <form action="${pageContext.request.contextPath}/books" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="add">
        Title: <input type="text" name="title" required><br>
        Genre ID: <input type="number" name="genreId"><br>
        Publish Year: <input type="number" name="publishYear"><br>
        ISBN: <input type="text" name="isbn"><br>
        Copies Available: <input type="number" name="copiesAvailable" required><br>
        <input type="submit" value="Add Book">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/main">Back to Main</a>
</body>
</html>