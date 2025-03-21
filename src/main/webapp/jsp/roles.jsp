<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Roles</title>
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

        input[type="text"], input[type="submit"] {
            padding: 10px;
            margin: 5px 0;
            width: 95%;
            border: 1px solid #ccc;
            border-radius: 4px;
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