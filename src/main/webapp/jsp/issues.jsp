<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Issues</title>
</head>
<body>
    <h1>Issues</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Book</th>
            <th>Reader ID</th>
            <th>Employee ID</th>
            <th>Issue Date</th>
            <th>Return Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="issue" items="${issues}">
            <tr>
                <td>${issue.id}</td>
                <td>
                    ${issue.bookDetails.title} (ID: ${issue.bookDetails.id})<br>
                    Genre: ${issue.bookDetails.genre}<br>
                    Authors: ${String.join(", ", issue.bookDetails.authors)}
                </td>
                <td>${issue.readerId}</td>
                <td>${issue.employeeId}</td>
                <td>${issue.issueDate}</td>
                <td>${issue.returnDate}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/issues?action=edit&id=${issue.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/issues?action=delete&id=${issue.id}"
                       onclick="return confirm('Are you sure you want to delete this issue?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add Issue</h2>
    <form action="${pageContext.request.contextPath}/issues" method="post" accept-charset="UTF-8">
        <input type="hidden" name="action" value="add">
        Book ID: <input type="number" name="bookId" required><br>
        Reader ID: <input type="number" name="readerId" required><br>
        Employee ID: <input type="number" name="employeeId" required><br>
        Issue Date: <input type="date" name="issueDate" required><br>
        Return Date: <input type="date" name="returnDate"><br>
        <input type="submit" value="Add Issue">
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/main">Back to Main</a>
</body>
</html>