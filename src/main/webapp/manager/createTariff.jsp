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
                    <input type="text" class="form-control" name="name" placeholder="Input name"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="kg" placeholder="Input price per kilogram"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="m3" placeholder="Input price per cubic meter"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="km" placeholder="Input price per kilometer"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <label for="transport">Select transport for tariff</label>
                    <select class="form-control" id="transport" name="transport" required>
                        <c:forEach var="transport" items="${transports}">
                            <option value="${transport.id}">ID ${transport.id}, ${transport.description}, ${transport.maxKg} kg, ${transport.maxM3} m3, ${transport.timePer100km} hours per 100km</option>
                        </c:forEach>
                    </select>
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
