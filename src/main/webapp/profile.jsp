<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/links.html"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css"/>

</head>
<body>
<jsp:include page="/header.jsp"/>



<div class="row mt-5 justify-content-center">
    <h1> <fmt:message key="header.profile"/> </h1>
</div>
<div class="row mt-2 justify-content-center align-items-center">
    <div class="modal-body row">


        <div class="col-md-2 m-lg-5">


            <div class="card mt-2" style="width: 21rem;">
                <div class="card-body">
                    <h5 class="card-title justify-content-center">${user.login}</h5>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><fmt:message key="user.name"/>: ${user.name}</li>
                        <li class="list-group-item"><fmt:message key="user.secondName"/>: ${user.secondName}
                        </li>
                        <li class="list-group-item">Email: ${user.email}</li>
                        <li class="list-group-item"><fmt:message key="user.phone"/>: ${user.phone}</li>

                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-5 m-lg-5">


            <div class=" mt-2" >
                <table class="table table-striped">
                    <thead>
                    <th><fmt:message key="order.tariff"/></th>
                    <th><fmt:message key="order.direction"/></th>
                    <th><fmt:message key="order.price"/></th>
                    <th><fmt:message key="order.orderDate"/></th>
                    <th><fmt:message key="order.dispatchDate"/></th>
                    <th><fmt:message key="order.receivingDate"/></th>
                    <th><fmt:message key="create.order.characteristics"/></th>
                    <th><fmt:message key="order.detail"/></th>
                    <th><fmt:message key="order.status"/></th>
                    <th><fmt:message key="main.action"/></th>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orders}">
                    <tr>

                        <td>
                                ${order.tariff.name}
                        </td>

                        <td>
                                ${order.direction.startCity} - ${order.direction.finalCity}
                        </td>
                        <td>
                            <fmt:formatNumber type="number"
                                              maxFractionDigits="2" value="${order.price}"/>
                        </td>
                        <td>
                                ${order.orderDate}
                        </td>
                        <td>
                                ${order.dispatchDate}
                        </td>
                        <td>
                                ${order.receivingDate}
                        </td>
                        <td>
                                ${order.volume} m<sup>3</sup> ,${order.weight} kg
                        </td>
                        <td>
                            <a href="detail?orderId=${order.orderId}">More</a>
                        </td>
                        <td>
                            <c:if test="${order.orderStatus=='in the way'}">
                                <fmt:message key="order.way"/>
                            </c:if>
                            <c:if test="${order.orderStatus!='in the way'}">
                                <fmt:message key="order.${order.orderStatus}"/>
                            </c:if>
                            <c:if test="${order.orderStatus=='unpaid'}">
                                <form action="updateOrder" method="post">
                                    <input type="text" name="status_${order.orderId}" value="paid"
                                           hidden>
                                    <input type="text" value="profile" name="link" hidden>
                                    <button type="submit" class="btn btn-primary">
                                        <fmt:message key="order.pay"/>
                                    </button>
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${order.orderStatus=='processing' || order.orderStatus=='unpaid'}">
                                <form action="updateOrder" method="post">
                                    <input type="text" name="delete_${order.orderId}" value="delete"
                                           hidden>
                                    <input type="text" value="profile" name="link" hidden>
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="row mt-5 justify-content-center align-items-center">
                <nav aria-label="Navigation for direction">
                    <c:if test="${noOfPages!=1}">
                        <ul class="pagination">
                            <c:if test="${currentPage != 1}">
                                <li class="page-item">
                                    <a href="profile?page=${currentPage-1}" class="page-link"
                                    ><fmt:message key="pagination.prev"/>
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
                                            <a href="profile?page=${i}" class="page-link"> ${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:if test="${currentPage lt noOfPages}">
                                <li class="page-item">
                                    <a href="profile?page=${currentPage+1}"
                                       class="page-link bg-light"><fmt:message
                                            key="pagination.next"/>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </c:if>
                </nav>
            </div>
        </div>
    </div>

</div>
</body>
</html>