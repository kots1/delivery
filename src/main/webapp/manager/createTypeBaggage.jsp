<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

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

            <form data-toggle="validator" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/typeBaggage" method="post">
                <span class="heading"><fmt:message key="create.type.title"/></span>
                <div class="form-group">
                    <input type="text" class="form-control" name="type" placeholder="<fmt:message key="create.type.input.type"/>"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" step="0.001" class="form-control" name="coefficient" placeholder="<fmt:message key="create.type.input.coefficient"/>"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default"><fmt:message key="main.create"/></button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>
