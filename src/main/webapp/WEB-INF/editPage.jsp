<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${!empty user.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${!empty user.name}">
    <c:url value="/admin/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty user.name}">
        <input type="hidden" name="id" value="${user.id}">
    </c:if>

    <label for="name">Name</label>
    <input type="text" name="name" id="name">

    <label for="login">Login</label>
    <input type="text" name="login" id="login">

    <label for="password">Password</label>
    <input type="text" name="password" id="password">

    <c:if test="${!empty user.name}">
        <input type="submit" value="Edit user">
    </c:if>

</form>
</body>
</html>