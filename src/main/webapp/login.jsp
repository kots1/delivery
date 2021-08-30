<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <jsp:include page="links.html"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />
    <title>Title</title>

</head>
<body>
<jsp:include page="header.jsp"/>

<c:if test="${sessionScope.user==null}">
    <div class="container">
        <div class="row h-100 justify-content-center align-items-center">

            <div class="col-md-offset-3 col-md-6">
                <form data-toggle="validator" class="form-horizontal" action="login" method="post">
                    <span class="heading">АВТОРИЗАЦІЯ</span>
                    <div class="form-group">
                        <input type="text" class="form-control" id="inputEmail"   placeholder="Login" name="login" required>
                        <i class="fa fa-user"></i>
                        <span class="help-block with-errors">${loginError}</span>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password" required>
                        <i class="fa fa-lock"></i>
                        <span class="help-block with-errors">${passwordError}</span>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">ВХОД</button>
                    </div>
                </form>
            </div>

        </div>
    </div>

</c:if>
<c:if test="${sessionScope.user!=null}">
    <h1>Ви увійшли у аккаунт</h1>
    <h2>Ваш аккаунт ${sessionScope.user.login}</h2>
    <a href="logout.jsp">Logout</a>
</c:if>

</body>
</html>
