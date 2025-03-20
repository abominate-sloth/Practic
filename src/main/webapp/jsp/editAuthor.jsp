<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Author</title>
</head>
<body>
    <h1>Edit Author</h1>
    <form action="${pageContext.request.contextPath}/authors" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${author.id}">
        Name: <input type="text" name="name" value="${author.name}"><br>
        Birth Date: <input type="date" name="birthDate" value="${author.birthDate}"><br>
        <input type="submit" value="Update Author">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/authors">Back to Authors</a>
</body>
</html>