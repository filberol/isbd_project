<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<h4>Resource allocation</h4>
<table>
    <thead>
    <tr>
        <th>Allocated at</th>
        <th>Resources, km</th>
    </tr>
    </thead>
    <tbody>

    <jsp:useBean id="resources" scope="request" type="java.util.List"/>
    <c:forEach items="${resources}" var="allocation">
        <tr>
            <td>${allocation.allocatedAt()}</td>
            <td>${allocation.resourceAllocatedKm()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

