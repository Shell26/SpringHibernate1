<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>

</head>
<body>


<table>
    <tr>
        <th>ID</th>
        <th>User name</th>
        <th>Login User</th>
        <th>User password</th>
    </tr>
    <c:forEach items="${usersFromServer}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>
                <form action="/admin/delete/${user.id}" method="POST">
                    <input type="submit" value="Delete"/>
                </form>
            <td>
            <td>
            <form action="/admin/edit/${user.id}">
                <input type="submit" value="Edit"/>
            </form>
            <td>
        </tr>
    </c:forEach>
</table>
<form action="/admin/save">
    <input type="submit" value="Добавить">
</form>

<form action="/logout" method="post">
    <input type="submit" value="Logout">
</form>

</body>
</html>
