<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <jsp:include page="/links.html"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />

</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="row mt-5 justify-content-center align-items-center">
    <div class="col-lg-8">
        <div class="row d-flex justify-content-between">
            <div class="col-lg-auto col-md-auto">
                <div class="row">
                    <div class="col-12 mt-5">
                        <!-- Nav Card -->
                        <div class="tab-content" id="nav-tabContent">
                            <!-- Card one -->
                            <div class="tab-pane fade show active" id="order" role="tabpanel" aria-labelledby="nav-home-tab">
                                <%@ include file="order.jspf" %>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
