<%@ page import="java.util.List" %>
<%@ page import="com.isbd.coursework.entities.WarehouseResourceAllocation" %>
<%@ page import="java.util.Collections" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:useBean id="resources" scope="request" type="java.util.List"/>

<%
    List<WarehouseResourceAllocation> currentPageItems = Collections.emptyList();
    int startIndex = 0;
    int endIndex = 0;
    try {
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        startIndex = Math.min((currentPage - 1) * 5, resources.size() - 1);
        endIndex = Math.min(startIndex + 5, resources.size() - 1);
        currentPageItems = resources.subList(startIndex, endIndex);
    } catch (IndexOutOfBoundsException ignored) { }
%>

<table>
    <thead>
    <tr>
        <th>Allocated at</th>
        <th>Resources, km</th>
    </tr>
    </thead>
    <tbody>
    <% for (WarehouseResourceAllocation currentPageItem : currentPageItems) { %>
    <tr>
        <td><%= currentPageItem.allocatedAt() %></td>
        <td><%= currentPageItem.resourceAllocatedKm() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

