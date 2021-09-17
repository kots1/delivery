
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>

    <title>Any title</title>
    <jsp:include page="links.html"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stepForm.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css"/>

    <script>
        var currentTab = ${step};
    </script>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="container-fluid" id="grad1">
    <div class="row justify-content-center mt-0">
        <div class="col-11 col-sm-9 col-md-7 col-lg-6 text-center p-0 mt-3 mb-2">
            <div class="card px-0 pt-4 pb-0 mt-3 mb-3">
                <h2><strong>Calculator</strong></h2>
                <div class="row">
                    <div class="col-md-12 mx-0">
                        <form id="regForm" action="order" method="post">

                            <div class="tab">
                                <%@ include file="orderPart/characteristic.jspf" %>
                            </div>

                            <div class="tab">
                                <%@include file="orderPart/chooseTypeOrder.jspf" %>
                            </div>
                            <div class="tab">
                                <%@include file="orderPart/address.jspf" %>
                            </div>
                            <div class="tab">
                                <%@include file="orderPart/description.jspf" %>
                            </div>
                            <div class="tab">
                                <%@include file="orderPart/result.jspf" %>
                                <input type="number" name="price" value="${price}"  hidden>
                            </div>
                            <hr>
                            <div style="overflow:auto;">
                                <div style="float:right;">
                                    <button type="submit" id="prevBtn" class="btn btn-default" name="step"
                                            value="-1">Previous
                                    </button>
                                    <button type="submit" id="nextBtn" class="btn btn-default" name="step"
                                            value="+1">Next
                                    </button>
                                </div>
                            </div>

                            <!-- Circles which indicates the steps of the form: -->
                            <div style="text-align:center;margin-top:40px;">
                                <span class="step"></span>
                                <span class="step"></span>
                                <span class="step"></span>
                                <span class="step"></span>
                                <span class="step"></span>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/calculate.js"></script>
<script src="${pageContext.request.contextPath}/js/stepForm.js"></script>
</body>
</html>

