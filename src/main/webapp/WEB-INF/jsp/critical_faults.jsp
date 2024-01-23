<%@ page import="java.util.List" %>
<%@ page import="com.isbd.coursework.entities.SegmentFault" %>
<%@ page import="java.util.Collections" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:useBean id="criticalFaults" scope="request" type="java.util.List"/>

<%
    List<SegmentFault> currentPageItems = Collections.emptyList();
    int startIndex = 0;
    int endIndex = 0;
    try {
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        startIndex = Math.min((currentPage - 1) * 20, criticalFaults.size() - 1);
        endIndex = Math.min(startIndex + 20, criticalFaults.size() - 1);
        currentPageItems = criticalFaults.subList(startIndex, endIndex);
    } catch (IndexOutOfBoundsException ignored) { }
%>

<input type="number" id="filterInput" placeholder="Filter by segment">

<table id="filterTable">
    <thead>
    <tr>
        <th>Railway segment</th>
        <th>Position, km</th>
    </tr>
    </thead>
    <tbody>
    <% for (SegmentFault currentPageItem : currentPageItems) { %>
    <tr>
        <td><%= currentPageItem.rwSegId() %>
        </td>
        <td><%= currentPageItem.positionPointKm() %>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

<script>
    // Function to filter the table rows
    function filterTable() {
        let input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("filterInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("filterTable");
        tr = table.getElementsByTagName("tr");

        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0]; // Change the index based on the column you want to filter
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }

    // Attach the filter function to the input element
    document.getElementById("filterInput").addEventListener("input", filterTable);
</script>

