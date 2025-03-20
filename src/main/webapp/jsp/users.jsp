<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <h1>Users</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Join Date</th>
            <th>Role ID</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.joinDate}</td>
                <td>${user.roleId}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/users?action=edit&id=${user.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/users?action=delete&id=${user.id}"
                       onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add User</h2>
    <form action="${pageContext.request.contextPath}/users" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="add">
        Username: <input type="text" name="username" required><br>
        Password Hash: <input type="text" name="passwordHash" required><br>
        Email: <input type="email" name="email"><br>
        Join Date: <input type="date" name="joinDate"><br>
        Role ID: <input type="number" name="roleId" required><br>
        <input type="submit" value="Add User">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/main">Back to Main</a>
</body>
</html>