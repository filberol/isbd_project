<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.isbd.coursework.entities.ResourceTransportation" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:useBean id="transportations" scope="request" type="java.util.List"/>

<%
    List<ResourceTransportation> currentPageItems = Collections.emptyList();
    int startIndex = 0;
    int endIndex = 0;
    try {
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        startIndex = Math.min((currentPage - 1) * 10, transportations.size() - 1);
        endIndex = Math.min(startIndex + 10, transportations.size() - 1);
        currentPageItems = transportations.subList(startIndex, endIndex);
    } catch (IndexOutOfBoundsException ignored) { }
%>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>To warehouse</th>
        <th>Start at</th>
        <th>Finish at</th>
        <th>Resources, km</th>
    </tr>
    </thead>
    <tbody>
    <% for (ResourceTransportation currentPageItem : currentPageItems) { %>
    <tr>
        <td><%= currentPageItem.id() %></td>
        <td><%= currentPageItem.toWarehouseId() %></td>
        <td><%= currentPageItem.startAt() %></td>
        <td><%= currentPageItem.finishAt() %></td>
        <td><%= currentPageItem.resourceTransportationKm() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

