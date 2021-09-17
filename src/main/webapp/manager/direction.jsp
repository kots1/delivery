<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-striped">
    <thead>
    <th>Id</th>
    <th>Start City</th>
    <th>Final city</th>
    <th>Distance</th>
    <th>Action</th>
    </thead>
    <tbody>
    <c:forEach var="direction" items="${directions}">
    <tr>
        <td>
                ${direction.id}
        </td>
        <td>
                ${direction.startCity}
        </td>
        <td>
                ${direction.finalCity}
        </td>
        <td>
                ${direction.distance}
        </td>
        <td>
            <div class="row">
                <form action="updateDirection" method="post">
                    <c:if test="${direction.isAlive==true}">
                        <input type="text" name="alive" value="false_${direction.id}" hidden>
                        <button type="submit" class="btn btn-primary">
                            <i class="fa fa-eye"></i>
                        </button>
                    </c:if>
                    <c:if test="${direction.isAlive==false}">
                        <input type="text" name="alive" value="true_${direction.id}" hidden>
                        <button type="submit" class="btn btn-primary">
                            <i class="fa fa-eye-slash"></i>
                        </button>
                    </c:if>
                </form>
                <form action="updateDirection" method="post">
                    <input type="text" name="delete" value="${direction.id}" hidden>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-trash"></i>
                    </button>
                </form>
            </div>

        </td>
    </tr>
    </c:forEach>
</table>