<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
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

        p {
            font-size: 1.2em;
            margin-bottom: 15px;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin: 10px 0;
        }

        a {
            text-decoration: none;
            color: #4CAF50;
            font-size: 1.1em;
        }

        a:hover {
            text-decoration: underline;
            color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Welcome to the Library Management System</h1>
    <p>Choose an entity to manage:</p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/authors">Authors</a></li>
        <li><a href="${pageContext.request.contextPath}/books">Books</a></li>
        <li><a href="${pageContext.request.contextPath}/genres">Genres</a></li>
        <li><a href="${pageContext.request.contextPath}/users">Users</a></li>
        <li><a href="${pageContext.request.contextPath}/roles">Roles</a></li>
        <li><a href="${pageContext.request.contextPath}/reviews">Reviews</a></li>
        <li><a href="${pageContext.request.contextPath}/issues">Issues</a></li>
        <li><a href="${pageContext.request.contextPath}/bookauthors">Book Authors (Many-to-Many)</a></li>
    </ul>
</body>
</html>