<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
    <h1>Welcome to the Library Management System</h1>
    <p>Choose an entity to manage:</p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/authors">Authors</a></li>
        <li><a href="${pageContext.request.contextPath}/books">Books</a></li>
        <li><a href="${pageContext.request.contextPath}/genres">Genres</a></li>
        <li><a href="${pageContext.request.contextPath}/users">Users</a></li>
        <li><a href="${pageContext.request.contextPath}/roles">Roles</a></li> <!-- Добавлено -->
        <li><a href="${pageContext.request.contextPath}/reviews">Reviews</a></li>
        <li><a href="${pageContext.request.contextPath}/issues">Issues</a></li>
        <li><a href="${pageContext.request.contextPath}/bookauthors">Book Authors (Many-to-Many)</a></li> <!-- Добавлено -->
    </ul>
</body>
</html>