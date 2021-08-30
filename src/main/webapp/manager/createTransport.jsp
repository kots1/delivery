<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <jsp:include page="../links.html"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />

</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-md-6 col-md-offset-3">


            <form data-toggle="validator" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/transport" method="post">
                <span class="heading">Create transport</span>
                <div class="form-group">
                    <input type="text" class="form-control" name="desc" placeholder="Input description"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="kg" placeholder="Input max kg"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="m3" placeholder="Input max volume"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <input type="number" min="0" class="form-control" name="time" placeholder="Input time for 100 km"  required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Create</button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>
