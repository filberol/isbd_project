<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.isbd.coursework.entities.SiteFaultFixation" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:useBean id="fixations" scope="request" type="java.util.List"/>

<%
    List<SiteFaultFixation> currentPageItems = Collections.emptyList();
    int startIndex = 0;
    int endIndex = 0;
    try {
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        startIndex = Math.min((currentPage - 1) * 5, fixations.size() - 1);
        endIndex = Math.min(startIndex + 5, fixations.size() - 1);
        currentPageItems = fixations.subList(startIndex, endIndex);
    } catch (IndexOutOfBoundsException ignored) { }
%>

<table>
    <thead>
    <tr>
        <th>Segment fault id</th>
        <th>Fault class</th>
        <th>Found at</th>
    </tr>
    </thead>
    <tbody>
    <% for (SiteFaultFixation currentPageItem : currentPageItems) { %>
    <tr>
        <td><a href="/api/brigade/faults/<%= currentPageItem.segmentFaultId() %>"><%= currentPageItem.segmentFaultId() %></a></td>
        <td><%= currentPageItem.faultClass() %></td>
        <td><%= currentPageItem.foundAt() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

