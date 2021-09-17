
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
    <jsp:include page="links.html"/>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
        <div class="text-center align-middle">


    <h1>ERROR</h1>
    <br>
            <c:if test="${errorMessage ==null}">
                cannot find page
            </c:if>
            ${errorMessage}
        </div>
</div>
</body>
</html>
