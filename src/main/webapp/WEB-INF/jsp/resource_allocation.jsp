<%@ page import="java.util.List" %>
<%@ page import="com.isbd.coursework.entities.WarehouseResourceAllocation" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:useBean id="resources" scope="request" type="java.util.List"/>

<%
    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
    int startIndex = (currentPage - 1) * 5;
    int endIndex = Math.min(startIndex + 5, resources.size());
    List<WarehouseResourceAllocation> currentPageItems = resources.subList(startIndex, endIndex);
%>

<h4>Resource allocation</h4>
<table>
    <thead>
    <tr>
        <th>Allocated at</th>
        <th>Resources, km</th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = startIndex; i < endIndex; i++) { %>
        <tr>
            <td><%= currentPageItems.get(i).allocatedAt() %></td>
            <td><%= currentPageItems.get(i).resourceAllocatedKm() %></td>
        </tr>
    <% } %>
    </tbody>
</table>

