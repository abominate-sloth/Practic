<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>
    <h1>Authors</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Birth Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="author" items="${authors}">
            <tr>
                <td>${author.id}</td>
                <td>${author.name}</td>
                <td>${author.birthDate}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/authors?action=edit&id=${author.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/authors?action=delete&id=${author.id}"
                       onclick="return confirm('Are you sure you want to delete this author?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add Author</h2>
    <form action="${pageContext.request.contextPath}/authors" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="add">
        Name: <input type="text" name="name" required><br>
        Birth Date: <input type="date" name="birthDate"><br>
        <input type="submit" value="Add Author">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/main">Back to Main</a>
</body>
</html>