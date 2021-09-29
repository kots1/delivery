<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

<table class="table table-striped">
    <thead>
    <th>Id</th>
    <th><fmt:message key="direction.startCity"/></th>
    <th><fmt:message key="direction.finalCity"/></th>
    <th><fmt:message key="direction.distance"/></th>
    <th><fmt:message key="main.action"/></th>
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
<div class="row mt-5 justify-content-center align-items-center">
    <nav aria-label="Navigation for direction">
        <c:if test="${noOfPages!=1}">
                <ul class="pagination">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item">
                            <a href="manager?part=direction&page=${currentPage-1}" class="page-link"
                            ><fmt:message key="pagination.prev"/>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item active"><a class="page-link">
                                        ${i} <span class="sr-only">(current)</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a href="manager?part=direction&page=${i}" class="page-link" > ${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage lt noOfPages}">
                        <li class="page-item">
                            <a href="manager?part=direction&page=${currentPage+1}" class="page-link bg-light" ><fmt:message key="pagination.next"/>
                            </a>
                        </li>
                    </c:if>
                </ul>
        </c:if>
    </nav>
</div>