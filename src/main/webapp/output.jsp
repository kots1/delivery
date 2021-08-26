<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
         language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <jsp:include page="links.html"/>

    <title>Any title</title>

</head>
<body>
<jsp:include page="header.jsp"/>

<h1>Привіт ${login}</h1>
<table border="1">
    <tr>
        <td>id</td>
        <td>login</td>
        <td>name</td>
        <td>second name</td>
        <td>email</td>
    </tr>
<c:forEach var="user" items="${users}">
    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.name}</td>
        <td>${user.secondName}</td>
        <td>${user.email}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
