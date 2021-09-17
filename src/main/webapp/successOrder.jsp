<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <jsp:include page="links.html"/>

    <title>Title</title>

</head>
<body>
<jsp:include page="header.jsp"/>

    <div class="container">
        <div class="row h-100 justify-content-center align-items-center">

            <div class="col-md-offset-3 col-md-6">
                <h1>Thank you</h1>
                <h3>We process your order</h3>
                <a href="${pageContext.request.contextPath}/">
                <button type="button"  class="btn btn-default" >Main
                </button>
                </a>
            </div>

        </div>
    </div>


</body>
</html>
