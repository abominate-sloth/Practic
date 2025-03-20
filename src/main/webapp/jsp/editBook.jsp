<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
    <h1>Edit Book</h1>
    <form action="${pageContext.request.contextPath}/books" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${book.id}">
        Title: <input type="text" name="title" value="${book.title}" required><br>
        Genre ID: <input type="number" name="genreId" value="${book.genreId}"><br>
        Publish Year: <input type="number" name="publishYear" value="${book.publishYear}"><br>
        ISBN: <input type="text" name="isbn" value="${book.isbn}"><br>
        Copies Available: <input type="number" name="copiesAvailable" value="${book.copiesAvailable}" required><br>
        <input type="submit" value="Update Book">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/books">Back to Books</a>
</body>
</html>