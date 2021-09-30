<%@ page import="com.delivery.CurrentLocale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

<html>
<head>
    <title>Title</title>
    <jsp:include page="../links.html"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css"/>

</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-md-6 col-md-offset-3">

            <form data-toggle="validator" class="form-horizontal" role="form"
                  action="${pageContext.request.contextPath}/tariff" method="post">
                <span class="heading"><fmt:message key="create.tariff.title"/></span>
                <div class="form-group">
                    <input type="text" class="form-control" data-error="<fmt:message key="form.shouldInput"/>" name="name_en" placeholder="<fmt:message
                        key="create.tariff.input.name"/>_en" required>
                    <div class="help-block with-errors"></div>
                </div>
                <c:forEach var="locale" items="${locales}">
                    <c:if test="${locale!='en'}">
                        <div class="form-group">
                            <input type="text" class="form-control" data-error="<fmt:message key="form.shouldInput"/>" name="name_${locale}"
                                   placeholder="<fmt:message key='create.tariff.input.name'/> ${locale}">
                        </div>
                    </c:if>
                </c:forEach>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" data-error="<fmt:message key="form.shouldInput"/>" name="priceKg" placeholder="<fmt:message
                            key="create.tariff.input.priceKg"/>" required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" data-error="<fmt:message key="form.shouldInput"/>" name="priceM3" placeholder="<fmt:message
                                key="create.tariff.input.priceM3"/>" required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" data-error="<fmt:message key="form.shouldInput"/>" name="priceKm" placeholder="<fmt:message
                                    key="create.tariff.input.priceKm"/>" required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" data-error="<fmt:message key="form.shouldInput"/>" name="maxKg" placeholder="<fmt:message
                                        key="create.tariff.input.maxKg"/>" required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" data-error="<fmt:message key="form.shouldInput"/>" name="maxM3" placeholder="<fmt:message
                                            key="create.tariff.input.maxM3"/>" required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" data-error="<fmt:message key="form.shouldInput"/>" name="time" placeholder="<fmt:message
                                                key="create.tariff.input.time"/>" required>
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
