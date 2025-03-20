<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Issue</title>
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