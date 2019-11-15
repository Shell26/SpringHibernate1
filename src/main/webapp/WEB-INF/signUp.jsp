<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
<form action="/signUp" method="POST">
    <input type="text" placeholder="name" name="name" required>
    <input type="text" placeholder="login" name="login" required>
    <input type="text" placeholder="password" name="password" required>
    <select name="role">
        <option>USER</option>
        <option>ADMIN</option>
    </select>
    <input type="submit" value="Зарегистрироваться"/>
</form>
</body>
</html>
