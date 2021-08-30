<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-striped">
    <thead>
    <th>Id</th>
    <th>Name</th>
    <th>Price per kilogram</th>
    <th>Price per cubic meter</th>
    <th>Price per kilometer</th>
    <th>Transport for tariff</th>
    </thead>
    <tbody>
    <c:forEach var="tariff" items="${tariffs}">
    <tr>
        <td>
                ${tariff.id}
        </td>
        <td>
                ${tariff.name}
        </td>
        <td>
                ${tariff.pricePerKm}
        </td>
        <td>
                ${tariff.pricePerM3}
        </td>
        <td>
                ${tariff.pricePerKg}
        </td>
        <td>
            <div class="dropdown open">
                <button class="btn btn-secondary dropdown-toggle"
                        type="button" id="dropdownMenu4" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                     Transport: ${tariff.transport.description}
                </button>
                <div class="dropdown-menu">
                    <span class="dropdown-item-text">Max weight = ${tariff.transport.maxKg} kg</span><br>
                    <span class="dropdown-item-text">Max volume = ${tariff.transport.maxM3} m3</span><br>
                    <span class="dropdown-item-text">Time per 100 km = ${tariff.transport.timePer100km} hours</span><br>
                </div>
            </div>
        </td>
    </tr>
    </c:forEach>
</table>