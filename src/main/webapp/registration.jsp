<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
         language="java"%>
<html>
<head>
    <title>registration</title>
  <jsp:include page="links.html"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth.css" />
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
  <div class="row">
    <div class="col-md-6 col-md-offset-3">


      <form data-toggle="validator" class="form-horizontal" role="form" action="registration" method="post">
        <span class="heading">РЕЄСТРАЦІЯ</span>
        <div class="form-group">
          <input type="text" class="form-control" name="login" id="inputLogin" placeholder="Ведіть Ваш Логін"  required>
          <i class="fa fa-user"></i>
          <div class="help-block with-errors"></div>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" name="name" id="inputName" placeholder="Ведіть Ваше ім'я"  required>
          <i class="fa fa-address-card"></i>
          <div class="help-block with-errors"></div>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" name="secondName" id="inputSecondName" placeholder="Ведіть Ваше Прівзище"  required>
          <i class="fa fa-address-card"></i>
          <div class="help-block with-errors"></div>
        </div>
        <div class="form-group">
          <input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email" data-error="Ви не правильно ввели E-mail" required >
          <i class="fa fa-at"></i>
          <div class="help-block with-errors"></div>
        </div>
        <div class="form-group">
          <div class="form-inline row">
            <div class="form-group col-sm-6">
              <input type="password" data-toggle="validator" data-minlength="6" class="form-control" data-error="Мінімум 6 елементів" id="inputPassword" placeholder="Ведіть пароль" name="password" required>
              <i class="fa fa-lock"></i>
              <span class="help-block with-errors" ></span>
            </div>
            <div class="form-group col-sm-6">
              <input type="password" class="form-control" id="inputPasswordConfirm" data-match="#inputPassword" data-match-error="Помилка! Паролі не співпадають!" placeholder="Повторіть пароль" required>
              <i class="fa fa-lock"></i>
              <div class="help-block with-errors"></div>
            </div>
          </div>
        </div>
        <div class="form-group">
          <button type="submit" class="btn btn-default">Зареєстуватися</button>
        </div>
      </form>
<%--<form action="registration" method="post">
  <label>
    <p>Login</p>
    <input type="text" name="login">
  </label>
  <label>
    <p>Name</p>
    <input type="text" name="name">
  </label>
  <label>
    <p>Second Name</p>
    <input type="text" name="secondName">
  </label>
  <label>
    <p>Password</p>
    <input type="password" name="password">
  </label>
  <label>
    <p>Email</p>
    <input type="text" name="email">
  </label>
  <input type="submit">
</form>--%>
</body>
</html>
