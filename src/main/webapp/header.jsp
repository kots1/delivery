<%@ page import="com.delivery.CurrentLocale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Delivery</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/"><fmt:message key="header.home" /> <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/calculator"><fmt:message key="header.calculator" /> <span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${role.name=='manager'}">
                <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/manager?part=order&page=1"> <fmt:message key="header.manager" /></a></li>
            </c:if>
            <c:if test="${role!=null}">
                <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/order"> <fmt:message key="header.order" /> </a></li>
            </c:if>
        </ul>
        <ul class="navbar-nav mr-5 w-25">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%=CurrentLocale.getLocale()%>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <c:forEach var="locale" items="${locales}">
                    <a class="dropdown-item" href="?lang=${locale}">${locale}</a>
                    </c:forEach>
                </div>
            </li>
            <c:if test="${user==null}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/registration.jsp"><span class="glyphicon glyphicon-user"></span> <fmt:message key="header.singIn" /></a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login.jsp"><span class="glyphicon glyphicon-log-in"></span> <fmt:message key="header.login" /></a></li>
            </c:if>

            <c:if test="${user!=null}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${user.login}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/profile"><fmt:message key="header.profile" /></a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="header.logOut" /></a>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>
</nav>

