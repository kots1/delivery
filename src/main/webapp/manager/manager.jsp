<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../links.html"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />

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
                                    <a class="nav-item nav-link active"  data-toggle="tab" href="#order" role="tab" >Orders</a>
                                    <a class="nav-item nav-link"  data-toggle="tab" href="#direction" role="tab" >Directions</a>
                                    <a class="nav-item nav-link"  data-toggle="tab" href="#tariff" role="tab" >Tariffs</a>
                                    <a class="nav-item nav-link"  data-toggle="tab" href="#type" role="tab" >Types</a>
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
                            <div class="tab-pane fade show active" id="order" role="tabpanel" aria-labelledby="nav-home-tab">
                                <%@ include file="order.jspf" %>
                            </div>
                            <!-- Card two -->
                            <div class="tab-pane fade" id="direction" role="tabpanel" aria-labelledby="nav-contact-tab">
                                <a href="manager/createDirection.jsp" class="btn btn-primary pull-right mb-2"><i class="fa fa-plus"></i>Add transport</a>
                                <%@ include file="direction.jsp" %>

                            </div>

                            <!-- card three -->
                            <div class="tab-pane fade" id="tariff" role="tabpanel" aria-labelledby="nav-technology">
                                <a href="manager/createTariff.jsp" class="btn btn-primary pull-right mb-2"><i class="fa fa-plus"></i>Add tariff</a>
                                <%@ include file="tariff.jspf" %>
                            </div>
                            <!-- card four -->
                            <div class="tab-pane fade" id="type" role="tabpanel" aria-labelledby="nav-technology">
                                <a href="manager/createTypeBaggage.jsp" class="btn btn-primary pull-right mb-2"><i class="fa fa-plus"></i>Add type</a>
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
