<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Добро пожаловать, "${user}". </br>
У вас недостаточно прав, для просмотра всех пользователей.
<form action="/logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
