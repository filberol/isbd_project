<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru-RU">
<head>
    <title>Атлант расправил плечи: Курсовая работа - Чернова Анна, Миху Вадим - P33301</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://d3js.org/d3.v6.min.js"></script>
    <jsp:include page="jsp_style.jsp"/>
</head>
<body>
<div id="container">
    <div id="left-panel" class="resizablePanel">
        <h2>${station.name()}</h2>
        <div>Owner: ${company.name()}</div>
        <hr/>
        <c:if test="${warehouse != null}">
            <h3>Warehouse</h3>
            <div>Resources available: ${warehouse.resourceAvailableKm()}</div>
            <div class="table-container">
                <h4>Last resource allocation</h4>
                <jsp:include page="resource_allocation.jsp">
                    <jsp:param name="page" value="1"/>
                </jsp:include>
            </div>
        </c:if>
        <hr/>
        <c:if test="${repairBase != null}">
            <h3>Repair base</h3>
            <div>Team capacity: ${repairBase.sizeTeams()}</div>
            <div>Teams hosted: ${repairBase.currTeamsHosted()}</div>
            <div class="table-container">
                <h4>Last repair team routes from base</h4>
                <jsp:include page="repair_team_routes.jsp">
                    <jsp:param name="page" value="1"/>
                </jsp:include>
            </div>
        </c:if>
    </div>
    <div id="center">
        <div id="search-bar1">
            <jsp:include page="station_search.jsp"/>
        </div>
        <div id="canvas">
            <svg width="550" height="550"></svg>
        </div>
    </div>
    <div id="right-panel">
        <h2>Info for brigades</h2>
        <hr/>
        <c:if test="${closeFaults != null}">
            <h3>Segment faults on closest railways</h3>
            <div class="table-container">
                <jsp:include page="segment_faults.jsp">
                    <jsp:param name="page" value="1"/>
                </jsp:include>
            </div>
        </c:if>
        <hr/>
        <c:if test="${criticalFaults != null}">
            <h3>Critical not repaired faults</h3>
            <div class="table-container" style="max-height: 400px">
                <jsp:include page="critical_faults.jsp">
                    <jsp:param name="page" value="1"/>
                </jsp:include>
            </div>
        </c:if>
    </div>
</div>

<jsp:include page="graph_script.jsp"/>
<jsp:include page="drag_script.jsp"/>

</body>
</html>
