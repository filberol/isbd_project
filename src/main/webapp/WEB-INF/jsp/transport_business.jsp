<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Repair team routes page</title>
    <jsp:include page="jsp_style.jsp"/>
</head>
<body style="padding: 10px">
<br>
<h2>Viewing resource transportations for station ${station.name()}</h2>
<br>
<h3>Current resources balance: ${warehouse.resourceAvailableKm()}</h3>
<br>
<jsp:include page="resource_transportation.jsp"/>
<br>
<iframe name="noredirect" id="noredirect" style="display: none;"></iframe>
<div style="flex-direction: row; display: flex; justify-content: space-around">
    <div style="border: 1px solid black; padding: 10px">
        <h3>Start resources transportation</h3>
        <form method="post" action="/brigade/resources/transport/start" target="noredirect">
            <p>
                <label for="from_station_id3">From station</label>
                <input type="text" id="from_station_id3" name="fromStation" required>
            </p>
            <p>
                <label for="to_station_id3">To station</label>
                <input type="text" id="to_station_id3" name="toStation" required>
            </p>
            <p>
                <label for="resources_km2">Resources, km</label>
                <input type="number" id="resources_km2" name="resourceKm" required>
            </p>
            <p>
                <label for="start_at">Start at</label>
                <input type="datetime-local" id="start_at" name="start" required>
            </p>
            <button type="submit">Create</button>
        </form>
    </div>
    <div style="border: 1px solid black; padding: 10px">
        <h3>Finish resources transportation</h3>
        <form method="post" action="/brigade/resources/transport/finish" target="noredirect">
            <p>
                <label for="transportation_id">Transportation id</label>
                <input type="number" id="transportation_id" name="transportationId" required>
            </p>
            <p>
                <label for="finish_at">Finish at</label>
                <input type="datetime-local" id="finish_at" name="finish" required>
            </p>
            <button type="submit">Create</button>
        </form>
    </div>
    </div>

<br>
<button onclick="window.location.href='/home'" style="float: right">Home</button>
<button onclick="window.location.href='/api/brigade/resources'" style="float: right">Manage resources</button>
</body>
</html>
