<%@ page import="com.isbd.coursework.entities.dto.TeamRouteDescription" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="routes" scope="request" type="java.util.List"/>


<%
    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
    int startIndex = (currentPage - 1) * 5;
    int endIndex = Math.min(startIndex + 5, routes.size());
    List<TeamRouteDescription> currentPageItems = routes.subList(startIndex, endIndex);
%>

<h4>Repair team routes from base</h4>
<table>
    <thead>
    <tr>
        <th>Team id</th>
        <th>Route id</th>
        <th>Planned</th>
        <th>Departed</th>
        <th>Arrived</th>
        <th>Station name</th>
    </tr>
    </thead>
    <tbody>

    <% for (int i = startIndex; i < endIndex; i++) { %>
        <tr>
            <td><%= currentPageItems.get(i).repairTeamId() %></td>
            <td><%= currentPageItems.get(i).routeId() %></td>
            <td><%= currentPageItems.get(i).plannedAt() %></td>
            <td><%= currentPageItems.get(i).departedAt() %></td>
            <td><%= currentPageItems.get(i).arrivedAt() %></td>
            <td><%= currentPageItems.get(i).name() %></td>
        </tr>
    <% } %>
    </tbody>
</table>

