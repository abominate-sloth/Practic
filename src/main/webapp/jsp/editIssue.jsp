<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Issue</title>
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

        input[type="number"], input[type="date"], input[type="submit"] {
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
    <h1>Edit Issue</h1>
    <form action="${pageContext.request.contextPath}/issues" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${issue.id}">
        Book ID: <input type="number" name="bookId" value="${issue.bookId}" required><br>
        Reader ID: <input type="number" name="readerId" value="${issue.readerId}" required><br>
        Employee ID: <input type="number" name="employeeId" value="${issue.employeeId}" required><br>
        Issue Date: <input type="date" name="issueDate" value="${issue.issueDate}" required><br>
        Return Date: <input type="date" name="returnDate" value="${issue.returnDate}"><br>
        <input type="submit" value="Update Issue">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/issues">Back to Issues</a>
</body>
</html>