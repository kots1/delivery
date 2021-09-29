<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://delivery.com/checkBox" prefix="m" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<html>
<head>

    <title>Any title</title>
    <jsp:include page="links.html"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stepForm.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css"/>


</head>
<body>
<%@ include file="header.jsp" %>
<div class="row mt-5 justify-content-center align-items-center">

<h3><fmt:message key="main.title.tariff" /></h3>
</div>
<div class="row mt-2 justify-content-center align-items-center">
    <div class="modal-body row">


        <c:forEach var="tariff" items="${requestScope.tariffs}">
            <div class="col-md-2 m-lg-5">


                <div class="card mt-2" style="width: 21rem;">
                    <div class="card-body">
                        <h5 class="card-title justify-content-center">${tariff.name}</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><fmt:message key="tariff.pricePerKm" />: ${tariff.pricePerKm} $</li>
                            <li class="list-group-item"><fmt:message key="tariff.pricePerM3" /><sup>3</sup>: ${tariff.pricePerM3} $
                            </li>
                            <li class="list-group-item"><fmt:message key="tariff.pricePerKg" />: ${tariff.pricePerKg} $</li>
                            <li class="list-group-item"><fmt:message key="tariff.maxWeight" />: ${tariff.maxWeight} <fmt:message key="tariff.shortKg" /></li>
                            <li class="list-group-item"><fmt:message key="tariff.maxVolume" />: ${tariff.maxVolume} m<sup>3</sup>
                            </li>
                            <li class="list-group-item"><fmt:message key="tariff.averageSpeed" />: <fmt:formatNumber type="number"
                                                                                                                     maxFractionDigits="2"
                                                                                                                     value="${100/tariff.timePer100km}"/>
                                <fmt:message key="tariff.shortSpeed" />
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>
<div class="row mt-5 justify-content-center align-items-center">
    <nav aria-label="Navigation for direction">
        <c:if test="${noOfPages!=1}">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a href="tariff?page=${currentPage-1}" class="page-link"
                        ><fmt:message key="pagination.prev" />
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a href="tariff?page=${i}" class="page-link"> ${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <a href="tariff?page=${currentPage+1}" class="page-link bg-light"><fmt:message key="pagination.next" />
                        </a>
                    </li>
                </c:if>
            </ul>
        </c:if>
    </nav>
</div>

</body>
</html>