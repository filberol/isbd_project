<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Resource allocations for base</title>
    <jsp:include page="jsp_style.jsp"/>
</head>
<body style="padding: 10px">
<br>
<h2>Viewing routes for team id ${members.get(0).repairTeamId()}</h2>
<br>
<h3>Team members:</h3>
<c:forEach items="${members}" var="member">
    Name: ${member.name()} <br>
</c:forEach>
<br>
<jsp:include page="repair_team_routes.jsp"/>
<br>
<iframe name="noredirect" id="noredirect" style="display: none;"></iframe>
<div style="flex-direction: row; display: flex; justify-content: space-around">
    <div style="border: 1px solid black; padding: 10px">
        <h3>Appoint route for repair team</h3>
        <form method="post" action="/brigade/route/appoint" target="noredirect">
            <p>
                <label for="team_id4">Team id</label>
                <input type="number" id="team_id4" name="teamId" required>
            </p>
            <p>
                <label for="from_station_id2">From station</label>
                <input type="text" id="from_station_id2" name="fromStation" required>
            </p>
            <p>
                <label for="to_station_id2">To station</label>
                <input type="text" id="to_station_id2" name="toStation" required>
            </p>
            <p>
                <label for="plan_at">Plan at</label>
                <input type="datetime-local" id="plan_at" name="planAt" required>
            </p>
            <button type="submit">Create</button>
        </form>
    </div>
    <div style="border: 1px solid black; padding: 10px">
        <h3>Start route for team</h3>
        <form method="post" action="/brigade/route/start" target="noredirect">
            <p>
                <label for="team_id6">Team id</label>
                <input type="number" id="team_id6" name="teamId" required>
            </p>
            <p>
                <label for="departed_at">Arrived at</label>
                <input type="datetime-local" id="departed_at" name="departed" required>
            </p>
            <button type="submit">Create</button>
        </form>
    </div>
    <div style="border: 1px solid black; padding: 10px">
        <h3>Finish route for team</h3>
        <form method="post" action="/brigade/route/finish" target="noredirect">
            <p>
                <label for="team_id5">Team id</label>
                <input type="number" id="team_id5" name="teamId" required>
            </p>
            <p>
                <label for="arrived_at2">Arrived at</label>
                <input type="datetime-local" id="arrived_at2" name="arrived" required>
            </p>
            <button type="submit">Create</button>
        </form>
    </div>
</div>

<br>
<button onclick="window.location.href='/home'" style="float: right">Home</button>
</body>
</html>
