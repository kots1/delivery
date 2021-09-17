<%@ page import="com.delivery.CurrentLocale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Delivery</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/calculator">Calculator <span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${role=='manager'}">
                <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/manager"> Manager panel</a></li>
            </c:if>
            <c:if test="${role=='manager' || role=='client'}">
                <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/order"> Make order </a></li>
            </c:if>
        </ul>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%= CurrentLocale.getLocale()%>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <c:forEach var="locale" items="${locales}">
                    <a class="dropdown-item" href="?lang=${locale}">${locale}</a>
                    </c:forEach>
                </div>
            </li>
            <c:if test="${user==null}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </c:if>

            <c:if test="${user!=null}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${user.login}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/profile">profile</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout.jsp">Log out</a>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
<%--

<nav class="navbar navbar-inverse position-fixed">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Delivery</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="menu-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li class="menu-item"><a href="users">Users</a></li>
            <c:if test="${user.role=='manager'}">
                <li><a href="manager"> manager </a></li>
            </c:if>
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
</nav>--%>
