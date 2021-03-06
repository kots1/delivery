<%@ page import="com.delivery.CurrentLocale" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"
         language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

<html lang="<%=CurrentLocale.getLocale()%>">
<head>
    <title>registration</title>
  <jsp:include page="links.html"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container h-100">
  <div class="row h-100 justify-content-center align-items-center">
    <div class="col-md-6 col-md-offset-3">


      <form data-toggle="validator" class="form-horizontal" role="form" action="registration" method="post">
        <span class="heading"><fmt:message key="registration.title"/></span>
        <div class="form-group">
          <input type="text" class="form-control" name="login" id="inputLogin" data-error="<fmt:message key="form.shouldInput"/>" placeholder="<fmt:message key="registration.input.login"/>" value="${login}"  required>
          <i class="fa fa-user"></i>
          <div class="help-block with-errors">${loginError}</div>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" name="name" id="inputName" data-error="<fmt:message key="form.shouldInput"/>" placeholder="<fmt:message key="registration.input.name"/>" value="${name}"  required>
          <i class="fa fa-address-card"></i>
          <div class="help-block with-errors"></div>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" name="secondName" id="inputSecondName" data-error="<fmt:message key="form.shouldInput"/>" placeholder="<fmt:message key="registration.input.secondName"/>" value="${secondName}" required>
          <i class="fa fa-address-card"></i>
          <div class="help-block with-errors"></div>
        </div>
        <div class="form-group">
          <input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email" data-error="<fmt:message key="registration.incorrect.email"/>" value="${email}" required >
          <i class="fa fa-at"></i>
          <div class="help-block with-errors">${emailError}</div>
        </div>
        <div class="form-group">
          <input type="number" class="form-control" name="phone" data-minlength="10" id="inputPhone" placeholder="<fmt:message key="registration.input.phone"/>" value="${phone}" data-error="<fmt:message key="registration.incorrect.phone"/>" required >
          <i class="fa fa-phone"></i>
          <div class="help-block with-errors"></div>
        </div>
        <div class="form-group">
          <div class="form-inline row">
            <div class="form-group col-sm-6">
              <input type="password" data-toggle="validator" data-minlength="6" class="form-control" data-error="<fmt:message key="registration.incorrect.minPassword"/>" id="inputPassword" placeholder="<fmt:message key="registration.input.password"/>" name="password" required>
              <i class="fa fa-lock"></i>
              <span class="help-block with-errors" ></span>
            </div>
            <div class="form-group col-sm-6">
              <input type="password" class="form-control" id="inputPasswordConfirm" data-match="#inputPassword" data-match-error="<fmt:message key="registration.incorrect.matchPassword"/>" placeholder="<fmt:message key="registration.input.repeatPassword"/>" required>
              <i class="fa fa-lock"></i>
              <div class="help-block with-errors"></div>
            </div>
          </div>
        </div>
        <div class="form-group">
          <button type="submit" class="btn btn-default"><fmt:message key="registration.input.signIn"/></button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
