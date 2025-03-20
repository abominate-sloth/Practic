<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Roles</title>
</head>
<body>
    <h1>Roles</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Role Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="role" items="${roles}">
            <tr>
                <td>${role.id}</td>
                <td>${role.roleName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/roles?action=edit&id=${role.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/roles?action=delete&id=${role.id}"
                       onclick="return confirm('Are you sure you want to delete this role?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add Role</h2>
    <form action="${pageContext.request.contextPath}/roles" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="add">
        Role Name: <input type="text" name="roleName" required><br>
        <input type="submit" value="Add Role">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/main">Back to Main</a>
</body>
</html>