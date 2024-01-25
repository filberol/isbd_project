<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru_RU">
<head>
    <title>Test Jsp Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>

<iframe name="noredirect" id="noredirect" style="display: none;"></iframe>

<h3>Insert new company</h3>
<form method="post" action="/admin/company" target="noredirect">
    <p>
        <label for="company_name">New company name</label>
        <input type="text" id="company_name" name="name" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Insert new railway station</h3>
<form method="post" action="/admin/railway/by_id" target="noredirect">
    <p>
        <label for="railway_name">New station name</label>
        <input type="text" id="railway_name" name="name" required>
    </p>
    <p>
        <label for="owner_id">Owner id</label>
        <input type="number" id="owner_id" name="ownerId" required>
    </p>
    <button type="submit">Create</button>
</form>
<h4>or</h4>
<form method="post" action="/admin/railway/by_name" target="noredirect">
    <p>
        <label for="railway_name2">New station name</label>
        <input type="text" id="railway_name2" name="name" required>
    </p>
    <p>
        <label for="ownerName">Owner name</label>
        <input type="text" id="ownerName" name="ownerName" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Insert new railway connections</h3>
<form method="post" action="/admin/segment/by_id" target="noredirect">
    <p>
        <label for="from_station_id">From station id</label>
        <input type="number" id="from_station_id" name="fromRs" required>
    </p>
    <p>
        <label for="to_station_id">To station id</label>
        <input type="number" id="to_station_id" name="toRs" required>
    </p>
    <p>
        <label for="length_km">Length km</label>
        <input type="number" id="length_km" name="lengthKm" required>
    </p>
    <button type="submit">Create</button>
</form>
<h4>or</h4>
<form method="post" action="/admin/segment/by_name" target="noredirect">
    <p>
        <label for="from_station_name">From station name</label>
        <input type="text" id="from_station_name" name="fromRs" required>
    </p>
    <p>
        <label for="to_station_name">To station name</label>
        <input type="text" id="to_station_name" name="toRs" required>
    </p>
    <p>
        <label for="length_km2">Length km</label>
        <input type="number" id="length_km2" name="lengthKm" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Insert new inspection site</h3>
<form method="post" action="/brigade/route_site/site" target="noredirect">
    <p>
        <label for="route_id">Route id</label>
        <input type="number" id="route_id" name="routeId" required>
    </p>
    <p>
        <label for="rw_seg_id">Railway segment</label>
        <input type="number" id="rw_seg_id" name="rwSegId" required>
    </p>
    <p>
        <label for="position_km">Position Km</label>
        <input type="number" id="position_km" name="positionKm" required>
    </p>
    <p>
        <label for="arrived_at">Arrived at</label>
        <input type="datetime-local" id="arrived_at" name="arrivedAt" required>
    </p>
    <p>
        <label for="type_site">Type site</label>
        <input type="text" id="type_site" name="typeSite" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Insert new fault fixation</h3>
<form method="post" action="/brigade/route_site/fixation" target="noredirect">
    <p>
        <label for="seg_fault_id">Route id</label>
        <input type="number" id="seg_fault_id" name="segFaultId" required>
    </p>
    <p>
        <label for="route_id2">Railway segment</label>
        <input type="number" id="route_id2" name="routeId" required>
    </p>
    <p>
        <label for="found">Found at</label>
        <input type="datetime-local" id="found" name="found" required>
    </p>
    <p>
        <label for="fault_class">Type site</label>
        <input type="text" id="fault_class" name="faultClass" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Add new repair base</h3>
<form method="post" action="/admin/base" target="noredirect">
    <p>
        <label for="company_name2">Existing company name</label>
        <input type="text" id="company_name2" name="stationName" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Add new repair team</h3>
<form method="post" action="/admin/team" target="noredirect">
    <p>
        <label for="owner_id2">Owner id</label>
        <input type="number" id="owner_id2" name="ownerId" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Add new repair team member</h3>
<form method="post" action="/admin/team/member" target="noredirect">
    <p>
        <label for="team_id">Owner id</label>
        <input type="text" id="team_id" name="teamId" required>
    </p>
    <p>
        <label for="member_name">Member name</label>
        <input type="text" id="member_name" name="memberName" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Change team affiliation</h3>
<form method="post" action="/admin/team/member/change" target="noredirect">
    <p>
        <label for="team_id2">Owner id</label>
        <input type="text" id="team_id2" name="teamId" required>
    </p>
    <p>
        <label for="member_name2">Member name</label>
        <input type="text" id="member_name2" name="memberName" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Set team head</h3>
<form method="post" action="/admin/head" target="noredirect">
    <p>
        <label for="team_id3">Owner id</label>
        <input type="text" id="team_id3" name="teamId" required>
    </p>
    <p>
        <label for="member_name3">Member name</label>
        <input type="text" id="member_name3" name="memberName" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Appoint route for repair team</h3>
<form method="post" action="/brigade/route/appoint" target="noredirect">
    <p>
        <label for="team_id4">Team id</label>
        <input type="number" id="team_id4" name="teamId" required>
    </p>
    <p>
        <label for="from_station_id2">From station</label>
        <input type="number" id="from_station_id2" name="fromStation" required>
    </p>
    <p>
        <label for="to_station_id2">To station</label>
        <input type="number" id="to_station_id2" name="toStation" required>
    </p>
    <p>
        <label for="plan_at">Plan at</label>
        <input type="datetime-local" id="plan_at" name="planAt" required>
    </p>
    <button type="submit">Create</button>
</form>

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

<h3>Start resources transportation</h3>
<form method="post" action="/brigade/resources/transport/start" target="noredirect">
    <p>
        <label for="from_station_id3">From station</label>
        <input type="number" id="from_station_id3" name="fromStation" required>
    </p>
    <p>
        <label for="to_station_id3">To station</label>
        <input type="number" id="to_station_id3" name="toStation" required>
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

<h3>Finish resources transportation</h3>
<form method="post" action="/brigade/resources/transport/finish" target="noredirect">
    <p>
        <label for="transportation_id">From station</label>
        <input type="number" id="transportation_id" name="transportationId" required>
    </p>
    <p>
        <label for="finish_at">Finish at</label>
        <input type="datetime-local" id="finish_at" name="finish" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Add segment fault</h3>
<form method="post" action="/brigade/fault/add" target="noredirect">
    <p>
        <label for="rw_seg_id2">Railway segment</label>
        <input type="number" id="rw_seg_id2" name="railwaySegmentId" required>
    </p>
    <p>
        <label for="fault_class2">Fault class</label>
        <input type="text" id="fault_class2" name="faultClass" required>
    </p>
    <p>
        <label for="position_km2">Position, km</label>
        <input type="number" id="position_km2" name="positionKm" required>
    </p>
    <p>
        <label for="fault_status">Fault status</label>
        <input type="text" id="fault_status" name="faultStatus" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Add segment fault on route</h3>
<form method="post" action="/brigade/fault/add_on_route" target="noredirect">
    <p>
        <label for="rw_seg_id3">Route id</label>
        <input type="number" id="rw_seg_id3" name="railwaySegmentId" required>
    </p>
    <p>
        <label for="fault_class3">Fault class</label>
        <input type="text" id="fault_class3" name="faultClass" required>
    </p>
    <p>
        <label for="position_km3">Position, km</label>
        <input type="number" id="position_km3" name="positionKm" required>
    </p>
    <p>
        <label for="route_id3">Route id</label>
        <input type="number" id="route_id3" name="routeId" required>
    </p>
    <p>
        <label for="found2">Found at</label>
        <input type="datetime-local" id="found2" name="found" required>
    </p>
    <p>
        <label for="fault_status2">Fault status</label>
        <input type="text" id="fault_status2" name="faultStatus" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Change fault status</h3>
<form method="post" action="/brigade/fault/change" target="noredirect">
    <p>
        <label for="seg_fault_id2">Route id</label>
        <input type="number" id="seg_fault_id2" name="segFaultId" required>
    </p>
    <p>
        <label for="fault_status3">Fault status</label>
        <input type="text" id="fault_status3" name="faultStatus" required>
    </p>
    <button type="submit">Create</button>
</form>

<h3>Add warehouse to base</h3>
<form method="post" action="/admin/warehouse" target="noredirect">
    <p>
        <label for="station_name2">Station name</label>
        <input type="text" id="station_name2" name="stationName" required>
    </p>
    <button type="submit">Create</button>
</form>

</body>
</html>