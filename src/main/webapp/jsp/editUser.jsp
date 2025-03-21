<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
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

        form {
            margin-top: 20px;
            background-color: #fff;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        input[type="text"], input[type="email"], input[type="date"], input[type="number"], input[type="submit"] {
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