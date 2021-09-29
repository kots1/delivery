
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />
<html>
<head>
    <title>Title</title>
    <jsp:include page="links.html"/>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
        <div class="text-center align-middle">


    <h1><fmt:message key="error.name" /></h1>
    <br>
            <c:if test="${errorMessage ==null}">
                <fmt:message key="error.404" />
            </c:if>
            ${errorMessage}
        </div>
</div>
</body>
</html>
