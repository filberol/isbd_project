<%@ page import="java.util.List" %>
<%@ page import="com.isbd.coursework.entities.SegmentFault" %>
<%@ page import="java.util.Collections" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:useBean id="closeFaults" scope="request" type="java.util.List"/>

<%
    List<SegmentFault> currentPageItems = Collections.emptyList();
    int startIndex = 0;
    int endIndex = 0;
    try {
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        startIndex = Math.min((currentPage - 1) * 20, closeFaults.size() - 1);
        endIndex = Math.min(startIndex + 20, closeFaults.size() - 1);
        currentPageItems = closeFaults.subList(startIndex, endIndex);
    } catch (IndexOutOfBoundsException ignored) { }
%>

<table>
    <thead>
    <tr>
        <th>Railway segment</th>
        <th>Fault class</th>
        <th>Position, km</th>
        <th>Fault status</th>
    </tr>
    </thead>
    <tbody>
    <% for (SegmentFault currentPageItem : currentPageItems) { %>
    <tr>
        <td><%= currentPageItem.rwSegId() %></td>
        <td><%= currentPageItem.faultClass() %></td>
        <td><%= currentPageItem.positionPointKm() %></td>
        <td><%= currentPageItem.faultStatus() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

