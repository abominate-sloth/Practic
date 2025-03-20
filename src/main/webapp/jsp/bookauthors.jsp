<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book Authors</title>
</head>
<body>
    <h1>Book Authors</h1>
    <table border="1">
        <tr>
            <th>Book ID</th>
            <th>Author ID</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="bookAuthor" items="${bookAuthors}">
            <tr>
                <td>${bookAuthor.bookId}</td>
                <td>${bookAuthor.authorId}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/bookauthors?action=delete&bookId=${bookAuthor.bookId}&authorId=${bookAuthor.authorId}"
                       onclick="return confirm('Are you sure you want to delete this link?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add Book Author Link</h2>
    <form action="${pageContext.request.contextPath}/bookauthors" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="add">
        Book ID: <input type="number" name="bookId" required><br>
        Author ID: <input type="number" name="authorId" required><br>
        <input type="submit" value="Add Link">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/main">Back to Main</a>
</body>
</html>