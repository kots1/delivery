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

        <div class="row mt-5 justify-content-center align-items-center">
            <div class="col-lg-8">
                <div class="row d-flex justify-content-between">
                    <div class="col-lg-auto col-md-auto">
                        <div class="properties__button">
                            <!--Nav Button  -->
                            <nav>
                                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                    <a class="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab" href="#order" role="tab" aria-controls="nav-profile" aria-selected="false">Orders</a>
                                    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#direction" role="tab" aria-controls="nav-contact" aria-selected="false">Directions</a>
                                    <a class="nav-item nav-link" id="nav-last-tab" data-toggle="tab" href="#transport" role="tab" aria-controls="nav-contact" aria-selected="false">Transports</a>
                                    <a class="nav-item nav-link" id="nav-technology" data-toggle="tab" href="#tariff" role="tab" aria-controls="nav-contact" aria-selected="false">Tariffs</a>
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
                                <h1>TODO</h1>
                            </div>
                            <!-- Card two -->
                            <div class="tab-pane fade" id="direction" role="tabpanel" aria-labelledby="nav-contact-tab">
                                <h1>TODO</h1>
                            </div>
                            <!-- card three -->
                            <div class="tab-pane fade" id="transport" role="tabpanel" aria-labelledby="nav-last-tab">
                                <a href="manager/createTransport.jsp" class="btn btn-primary pull-right mb-2"><i class="fa fa-plus"></i>Add transport</a>
                                <jsp:include page="transport.jsp"/>
                            </div>

                            <!-- card four -->
                            <div class="tab-pane fade" id="tariff" role="tabpanel" aria-labelledby="nav-technology">
                                <a href="manager/createTariff.jsp" class="btn btn-primary pull-right mb-2"><i class="fa fa-plus"></i>Add tadiff</a>
                                <jsp:include page="tariff.jsp"/>
                            </div>
                        </div>
                        <!-- End Nav Card -->
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
