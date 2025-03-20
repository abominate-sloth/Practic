<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Role</title>
</head>
<body>
    <h1>Edit Role</h1>
    <form action="${pageContext.request.contextPath}/roles" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${role.id}">
        Role Name: <input type="text" name="roleName" value="${role.roleName}" required><br>
        <input type="submit" value="Update Role">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/roles">Back to Roles</a>
</body>
</html>