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

            <form data-toggle="validator" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/direction" method="post">
                <span class="heading">Create direction</span>
                <div class="form-group">
                    <input type="text" class="form-control" name="start_en" placeholder="Input start city en" required>
                    <br>
                    <input type="text" class="form-control" name="final_en" placeholder="Input final city en" required>
                    <div class="help-block with-errors"></div>
                    <br>

                </div>
                <c:forEach var="locale" items="${locales}">
                    <c:if test="${locale!='en'}">
                        <div class="form-group">
                            <input type="text" class="form-control" name="start_${locale}" placeholder="Input start city ${locale}" >
                            <br>
                            <input type="text" class="form-control" name="final_${locale}" placeholder="Input final city ${locale}" >
                        </div>
                        <br>

                    </c:if>
                </c:forEach>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="distance" placeholder="Input distance between cities"  required>
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
