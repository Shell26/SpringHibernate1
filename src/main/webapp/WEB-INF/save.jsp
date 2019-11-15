<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Save</title>

</head>
<body>

<form action="/admin/save" method="POST">
    <input type="text" placeholder="name" name="name">
    <input type="text" placeholder="login" name="login">
    <input type="text" placeholder="password" name="password">
    <input type="submit" value="Add">
</form>

</body>
</html>
