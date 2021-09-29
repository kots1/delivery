<%@ page import="com.delivery.CurrentLocale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />
<html>
<head>
    <title>Title</title>
    <jsp:include page="../links.html"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />

</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-md-6 col-md-offset-3">

            <form data-toggle="validator" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/direction" method="post">
                <span class="heading"><fmt:message key="create.direction.title" /></span>
                <div class="form-group">
                    <input type="text" class="form-control" name="start_en" placeholder="<fmt:message key="create.direction.input.startCity" /> en" required>
                    <br>
                    <input type="text" class="form-control" name="final_en" placeholder="<fmt:message key="create.direction.input.finalCity" /> en" required>
                    <div class="help-block with-errors"></div>
                    <br>

                </div>
                <c:forEach var="locale" items="${locales}">
                    <c:if test="${locale!='en'}">
                        <div class="form-group">
                            <input type="text" class="form-control" name="start_${locale}" placeholder="<fmt:message key="create.direction.input.startCity" /> ${locale}" >
                            <br>
                            <input type="text" class="form-control" name="final_${locale}" placeholder="<fmt:message key="create.direction.input.finalCity" /> ${locale}" >
                        </div>
                        <br>

                    </c:if>
                </c:forEach>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="distance" placeholder="<fmt:message key="create.direction.input.distance" />"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default"><fmt:message key="main.create" /></button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>
