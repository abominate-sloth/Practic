<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
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

        input[type="text"], input[type="number"], input[type="submit"] {
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
    <h1>Edit Book</h1>
    <form action="${pageContext.request.contextPath}/books" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${book.id}">
        Title: <input type="text" name="title" value="${book.title}" required><br>
        Genre ID: <input type="number" name="genreId" value="${book.genreId}"><br>
        Publish Year: <input type="number" name="publishYear" value="${book.publishYear}"><br>
        ISBN: <input type="text" name="isbn" value="${book.isbn}"><br>
        Copies Available: <input type="number" name="copiesAvailable" value="${book.copiesAvailable}" required><br>
        <input type="submit" value="Update Book">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/books">Back to Books</a>
</body>
</html>