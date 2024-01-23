<%@ page import="com.isbd.coursework.entities.dto.TeamRouteDescription" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="routes" scope="request" type="java.util.List"/>


<%
    List<TeamRouteDescription> currentPageItems = Collections.emptyList();
    int startIndex = 0;
    int endIndex = 0;
    try {
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        startIndex = Math.min((currentPage - 1) * 5, routes.size() - 1);
        endIndex = Math.min(startIndex + 5, routes.size() - 1);
        currentPageItems = routes.subList(startIndex, endIndex);
    } catch (IndexOutOfBoundsException ignored) { }
%>


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

    <% for (TeamRouteDescription currentPageItem : currentPageItems) { %>
    <tr>
        <td><%= currentPageItem.repairTeamId() %></td>
        <td><%= currentPageItem.routeId() %></td>
        <td><%= currentPageItem.plannedAt() %></td>
        <td><%= currentPageItem.departedAt() %></td>
        <td><%= currentPageItem.arrivedAt() %></td>
        <td><%= currentPageItem.name() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

