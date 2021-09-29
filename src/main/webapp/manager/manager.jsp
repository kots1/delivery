<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../links.html"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stepForm.css"/>

</head>
<body>
<jsp:include page="../header.jsp"/>

        <div class="row mt-5 justify-content-center align-items-center">
            <div class="col-lg-8">
                <div class="row d-flex justify-content-between">
                    <div class="col-lg-auto col-md-auto">
                        <div class="properties__button">
                            <!--Nav Button  -->
                            <nav>
                                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                    <a class="nav-item nav-link <c:if test="${part == 'order'}">active</c:if>"   href="manager?part=order&page=1" role="tab" ><fmt:message key="main.title.order"/></a>
                                    <a class="nav-item nav-link <c:if test="${part == 'direction'}">active</c:if>"   href="manager?part=direction&page=1" role="tab" ><fmt:message key="main.title.direction"/></a>
                                    <a class="nav-item nav-link <c:if test="${part == 'tariff'}">active</c:if>"   href="manager?part=tariff&page=1" role="tab" ><fmt:message key="main.title.tariff"/></a>
                                    <a class="nav-item nav-link <c:if test="${part == 'type'}">active</c:if>"   href="manager?part=type&page=1" role="tab" ><fmt:message key="main.title.type"/></a>
                                </div>
                            </nav>
                            <!--End Nav Button  -->
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 mt-5">
                        <!-- Nav Card -->
                        <div class="tab-content" id="nav-tabContent">
                            <!-- Card one -->
                            <div  class="tab-pane fade  <c:if test="${part == 'order'}">show active</c:if>"id="order" role="tabpanel" aria-labelledby="nav-home-tab">
                                <%@ include file="order.jspf" %>
                            </div>
                            <!-- Card two -->
                            <div  class="tab-pane fade <c:if test="${part == 'direction'}">show active</c:if>" id="direction" role="tabpanel" aria-labelledby="nav-contact-tab">
                                <a href="manager/createDirection.jsp" class="btn btn-primary pull-right mb-2"><i class="fa fa-plus"></i><fmt:message key="manager.add.direction"/></a>
                                <%@ include file="direction.jsp" %>

                            </div>

                            <!-- card three -->
                            <div  class="tab-pane fade <c:if test="${part == 'tariff'}">show active</c:if>" id="tariff" role="tabpanel" aria-labelledby="nav-technology">
                                <a href="manager/createTariff.jsp" class="btn btn-primary pull-right mb-2"><i class="fa fa-plus"></i><fmt:message key="manager.add.tariff"/></a>
                                <%@ include file="tariff.jspf" %>
                            </div>
                            <!-- card four -->
                            <div  class="tab-pane fade <c:if test="${part == 'type'}">show active</c:if>" id="type" role="tabpanel" aria-labelledby="nav-technology">
                                <a href="manager/createTypeBaggage.jsp" class="btn btn-primary pull-right mb-2"><i class="fa fa-plus"></i><fmt:message key="manager.add.type"/></a>
                                <%@ include file="typeBaggage.jspf" %>
                            </div>
                        </div>
                        <!-- End Nav Card -->
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
