<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authors</title>
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

        input[type="text"], input[type="date"], input[type="submit"] {
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
    <h1>Authors</h1>
    <table>
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