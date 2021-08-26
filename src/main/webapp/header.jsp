<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Delivery</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="menu-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li class="menu-item"><a href="users">Users</a></li>
            <li class="menu-item"><a href="#">Page 2</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:if test="${user==null}">
                <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </c:if>
            <c:if test="${user!=null}">
                <p class="navbar-text"><span class="glyphicon glyphicon-user"></span> ${user.login}</p>
                <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
            </c:if>
        </ul>
    </div>
</nav>