<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://delivery.com/checkBox" prefix="m" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />
<html>
<head>

    <title>Delivery</title>
    <jsp:include page="links.html"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stepForm.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css"/>


</head>
<body>
<%@ include file="header.jsp" %>
<div class="row mt-5 justify-content-center align-items-center">
    <div class="modal-body row">
        <div class="col-md-2 ml-lg-5">
            <form action="main">
                <h3><fmt:message key="main.filter" /></h3>
                <hr>
                <h5><fmt:message key="direction.startCity" /></h5>


                <c:forEach var="startCity" items="${distinctStartCities}">
                    <input class="form-check-input" type="checkbox" id="${startCity}" name="start" value="${startCity}"
                        <m:checkBox var="${startCity}" items='<%= request.getParameterValues("start") %>'/> >
                    <label class="form-check-label" for="${startCity}">${startCity}
                    </label>
                    <br>
                </c:forEach>

                <hr>
                <h5><fmt:message key="direction.finalCity" /></h5>
                <c:forEach var="finalCity" items="${distinctFinalCities}">
                    <input class="form-check-input" type="checkbox" id="${finalCity}" name="final" value="${finalCity}"
                        <m:checkBox var="${finalCity}" items='<%= request.getParameterValues("final") %>'/> >

                    <label class="form-check-label" for="${finalCity}">${finalCity}
                    </label>
                    <br>
                </c:forEach>
                <hr>
                <input type="text" name="page" value="1" hidden>
                <input type="submit" value="<fmt:message key="main.filter" />">
            </form>
        </div>
        <div class="col-md-6">
            <table class="table table-striped">
                <h3><fmt:message key="main.title.direction" /></h3>
                <thead>
                <th><fmt:message key="direction.startCity" /></th>
                <th><fmt:message key="direction.finalCity" /></th>
                <th><fmt:message key="direction.distance" /></th>
                </thead>
                <tbody>
                <c:forEach var="direction" items="${directions}">
                <tr>
                    <td>
                            ${direction.startCity}
                    </td>
                    <td>
                            ${direction.finalCity}
                    </td>
                    <td>
                            ${direction.distance} km
                    </td>
                </tr>
                </c:forEach>
            </table>
            <div class="row mt-5 justify-content-center align-items-center">

                <nav aria-label="Navigation for direction">
                    <c:if test="${noOfPages!=1}">
                        <form action="main">

                            <ul class="pagination">
                                <c:if test="${currentPage != 1}">
                                    <li class="page-item">
                                        <button type="submit" class="page-link" name="page" value="${currentPage-1}"
                                        ><fmt:message key="pagination.prev" />
                                        </button>
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
                                                <button type="submit" class="page-link" name="page"
                                                        value="${i}"> ${i}</button>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <c:if test="${currentPage lt noOfPages}">
                                    <li class="page-item">
                                        <button type="submit" class="page-link bg-light" name="page"
                                                value="${currentPage+1}"><fmt:message key="pagination.next" />
                                        </button>
                                    </li>
                                </c:if>
                            </ul>
                            <c:forEach var="finalCity" items='<%= request.getParameterValues("final") %>'>
                                <input type="text" value="${finalCity}" name="final" hidden>
                            </c:forEach>
                            <c:forEach var="startCity" items='<%= request.getParameterValues("start") %>'>
                                <input type="text" value="${startCity}" name="start" hidden>
                            </c:forEach>
                        </form>
                    </c:if>
                </nav>
            </div>

        </div>
        <div class="col-md-2">
            <h3><fmt:message key="main.title.tariff" /></h3>
            <c:forEach var="tariff" items="${requestScope.tariffs}">
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
            </c:forEach>
            <a href="tariff"><fmt:message key="tariff.more" />...</a>
        </div>
    </div>


</div>

</body>
</html>
