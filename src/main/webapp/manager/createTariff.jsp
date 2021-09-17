<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

            <form data-toggle="validator" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/tariff" method="post">
                <span class="heading">Create tariff</span>
                <div class="form-group">
                    <input type="text" class="form-control" name="name_en" placeholder="Input name_en"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <c:forEach var="locale" items="${locales}">
                    <c:if test="${locale!='en'}">
                        <div class="form-group">
                            <input type="text" class="form-control" name="name_${locale}" placeholder="Input name_${locale}" >
                        </div>
                    </c:if>
                </c:forEach>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="priceKg" placeholder="Input price per kilogram"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="priceM3" placeholder="Input price per cubic meter"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="priceKm" placeholder="Input price per kilometer"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="maxKg" placeholder="Input max kg"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="maxM3" placeholder="Input max volume"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="time" placeholder="Input time for 100 km"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Create</button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>
