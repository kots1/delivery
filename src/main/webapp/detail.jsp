<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
<div class="container">
    <div class="mt-5 justify-content-center align-items-center">
        <h1><fmt:message key="order.title" /> № ${order.orderId}</h1>
        <hr>
        <div class="modal-body row">
            <div class="col-md-3 ml-lg-5">
                <h3><fmt:message key="order.tariff" /></h3>
                <p><fmt:message key="tariff.name" />:${order.tariff.name} </p>
                <p><fmt:message key="tariff.pricePerKm" />: ${order.tariff.pricePerKm} $</p>
                <p><fmt:message key="tariff.pricePerM3" /><sup>3</sup>:${order.tariff.pricePerM3} $</p>
                <p><fmt:message key="tariff.pricePerKg" />:${order.tariff.pricePerKg} $</p>
                <p><fmt:message key="tariff.maxWeight" />:${order.tariff.maxWeight} <fmt:message key="tariff.shortKg" /></p>
                <p><fmt:message key="tariff.maxVolume" />:${order.tariff.maxVolume} m<sup>3</sup></p>
                <p><fmt:message key="tariff.averageSpeed" />: <fmt:formatNumber type="number"
                                                    maxFractionDigits="2" value="${100/order.tariff.timePer100km}"/>
                    <fmt:message key="tariff.shortSpeed" /></p>
            </div>
            <div class="col-md-3 ml-lg-5">
                <h3><fmt:message key="order.direction" /></h3>
                <p><fmt:message key="direction.startCity" />: ${order.direction.startCity}  </p>
                <p><fmt:message key="direction.finalCity" />:${order.direction.finalCity} </p>
                <p><fmt:message key="direction.distance" />:${order.direction.distance} </p>
            </div>
            <div class="col-md-3 ml-lg-5">
                <h3><fmt:message key="order.user" /></h3>
                <p><fmt:message key="user.login" />:${order.user.login} </p>
                <p><fmt:message key="user.name" />: ${order.user.name} ${order.user.secondName} </p>
                <p>Email:${order.user.email} </p>
                <p><fmt:message key="user.phone" />:${order.user.phone} </p>
            </div>
        </div>
        <hr>
        <div class="modal-body">
            <p><fmt:message key="create.order.description" />: ${order.description}  </p>
            <p><fmt:message key="order.type" />: ${order.typeBaggage.type}  </p>
            <p><fmt:message key="create.order.address" />: <fmt:message key="order.street" />. ${order.street}, ${order.house} (${order.apartment})</p>
            <p><fmt:message key="create.order.volume" />: ${order.volume} m<sup>3</sup></p>
            <p><fmt:message key="create.order.weight" />: ${order.weight} kg</p>
            <p><fmt:message key="order.orderDate" />: ${order.orderDate}  </p>
            <p><fmt:message key="order.dispatchDate" />: ${order.dispatchDate}  </p>
            <p><fmt:message key="order.receivingDate" />: ${order.receivingDate}  </p>
            <p><fmt:message key="order.status" />: ${order.orderStatus}  </p>
            <p><fmt:message key="create.order.totalPrice" />: ${order.price} $</p>
            <hr>
        </div>

    </div>
</div>
</body>
</html>
