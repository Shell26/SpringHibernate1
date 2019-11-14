<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<h2>Users</h2>
<table>
    <tr>
        <th>id</th>
        <th>age</th>
        <th>name</th>
        <th>password</th>
        <th>role</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.age}</td>
            <td>${user.name}</td>
            <td>${user.password}</td>
            <td>${user.roles}</td>
            <td>
                <a href="/admin/edit/${user.id}">edit</a>
                <a href="/admin/delete/${user.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/admin/add" var="add"/>
<a href="${add}">Add new user</a>

<form action="/logout" method="post">
    <input type="submit" value="Logout"/>
</form>

</body>
</html>
