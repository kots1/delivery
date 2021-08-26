<%@ page import="com.delivery.entity.User" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="links.html"/>

    <title>JSP - Hello World</title>
</head>
<body>
<jsp:include page="header.jsp"/>
    <%--<ul class="nav navbar-nav navbar-right">
                <c:if test="${user==null}">
                    <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </c:if>
                <c:if test="${user!=null}">
                    <p class="navbar-text"><span class="glyphicon glyphicon-user"></span> ${user.login}</p>
                    <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
                </c:if>
            </ul>--%>

</body>
</html>