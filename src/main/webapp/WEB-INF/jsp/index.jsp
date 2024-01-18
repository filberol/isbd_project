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
                <h4>Resource allocation</h4>
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
            <form method="get" action="/home">
                <label for="station_search">Station name</label>
                <input type="text" id="station_search" name="stationName" placeholder="Type station name">
                <button type="submit">Search</button>
            </form>
        </div>
        <div id="search-bar2">
            <label for="brigade_search">Brigade name</label>
            <input type="text" id="brigade_search" placeholder="Type brigade name">
        </div>
        <div id="canvas">
            <svg width="550" height="550"></svg>
        </div>
    </div>
    <div id="right-panel"></div>
</div>

<jsp:include page="graph_script.jsp"/>
<jsp:include page="drag_script.jsp"/>

</body>
</html>
