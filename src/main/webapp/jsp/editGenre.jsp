<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Genre</title>
</head>
<body>
    <h1>Edit Genre</h1>
    <form action="${pageContext.request.contextPath}/genres" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${genre.id}">
        Name: <input type="text" name="name" value="${genre.name}" required><br>
        <input type="submit" value="Update Genre">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/genres">Back to Genres</a>
</body>
</html>