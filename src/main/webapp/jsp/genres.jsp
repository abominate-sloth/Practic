<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Genres</title>
</head>
<body>
    <h1>Genres</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="genre" items="${genres}">
            <tr>
                <td>${genre.id}</td>
                <td>${genre.name}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/genres?action=edit&id=${genre.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/genres?action=delete&id=${genre.id}"
                       onclick="return confirm('Are you sure you want to delete this genre?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add Genre</h2>
    <form action="${pageContext.request.contextPath}/genres" method="post">
        <input type="hidden" name="action" value="add">
        Name: <input type="text" name="name" required><br>
        <input type="submit" value="Add Genre">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/main">Back to Main</a>
</body>
</html>