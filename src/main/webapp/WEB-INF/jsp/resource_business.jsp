<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Repair team routes page</title>
    <jsp:include page="jsp_style.jsp"/>
</head>
<body style="padding: 10px">
<br>
<h2>Viewing resource allocations for station ${station.name()}</h2>
<br>
<h3>Current resources balance: ${warehouse.resourceAvailableKm()}</h3>
<br>
<jsp:include page="resource_allocation.jsp"/>
<br>
<iframe name="noredirect" id="noredirect" style="display: none;"></iframe>
<div style="flex-direction: row; display: flex; justify-content: space-around">
    <div style="border: 1px solid black; padding: 10px">
        <h3>Add resource allocation to base</h3>
        <form method="post" action="/brigade/resources" target="noredirect">
            <p>
                <label for="station_name">Station name</label>
                <input type="text" id="station_name" name="stationName" required>
            </p>
            <p>
                <label for="resources_km">Resources km</label>
                <input type="number" id="resources_km" name="resourcesKm" required>
            </p>
            <p>
                <label for="allocated_at">Allocated at</label>
                <input type="datetime-local" id="allocated_at" name="allocatedAt" required>
            </p>
            <button type="submit">Create</button>
        </form>
    </div>
</div>
<br>
<button onclick="window.location.href='/home'" style="float: right">Home</button>
<button onclick="window.location.href='/api/brigade/resources/transport'" style="float: right">Transport resources</button>
</body>
</html>
