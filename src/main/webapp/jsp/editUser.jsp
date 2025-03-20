<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
    <h1>Edit User</h1>
    <form action="${pageContext.request.contextPath}/users" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${user.id}">
        Username: <input type="text" name="username" value="${user.username}" required><br>
        Password Hash: <input type="text" name="passwordHash" value="${user.passwordHash}" required><br>
        Email: <input type="email" name="email" value="${user.email}"><br>
        Join Date: <input type="date" name="joinDate" value="${user.joinDate}"><br>
        Role ID: <input type="number" name="roleId" value="${user.roleId}" required><br>
        <input type="submit" value="Update User">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/users">Back to Users</a>
</body>
</html>