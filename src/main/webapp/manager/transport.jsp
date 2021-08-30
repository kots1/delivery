<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-striped">
    <thead>
    <th>Id</th>
    <th>Description</th>
    <th>Max kilogram</th>
    <th>Max volume</th>
    <th>Time for 100 km</th>
    <th class="text-right">Дії</th>
    </thead>
    <tbody>
    <c:forEach var="transport" items="${transports}">
    <tr>
        <td>
                ${transport.id}
        </td>
        <td>
                ${transport.description}
        </td>
        <td>
                ${transport.maxKg}
        </td>
        <td>
                ${transport.maxM3}
        </td>
        <td>
                ${transport.timePer100km}
        </td>
    </tr>
    </c:forEach>
</table>