<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method='POST'>

    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='name'></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td><button type="submit">Login</button></td>
        </tr>
    </table>

</form>
</body>
</html>
