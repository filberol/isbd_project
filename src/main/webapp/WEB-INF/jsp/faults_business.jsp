<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Repair team routes page</title>
    <jsp:include page="jsp_style.jsp"/>
</head>
<body style="padding: 10px">
<br>
<c:if test="${fixations.size() != 0}">
    <h2>Viewing fault fixations for route ${fixations.get(0).routeId()}</h2>
</c:if>
<br>
<jsp:include page="fault_fixations.jsp"/>
<br>
<iframe name="noredirect" id="noredirect" style="display: none;"></iframe>
<div style="flex-direction: row; display: flex; justify-content: space-around">
    <div style="border: 1px solid black; padding: 10px">
        <h3>Add segment fault on route</h3>
        <form method="post" action="/brigade/fault/add_on_route" target="noredirect">
            <p>
                <label for="rw_seg_id3">Railway segment id</label>
                <input type="number" id="rw_seg_id3" name="railwaySegmentId" required>
            </p>
            <p>
                <label for="fault_class3">Fault class</label>
                <input type="text" id="fault_class3" name="faultClass" list="class_suggests" required>
                <datalist id="class_suggests">
                    <option value="critical">
                    <option value="non_critical">
                </datalist>
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
                <datalist>
                    <option value="not_repaired">
                    <option value="repaired">
                </datalist>
            </p>
            <button type="submit">Create</button>
        </form>
    </div>
    <div style="border: 1px solid black; padding: 10px">
        <h3>Change fault status</h3>
        <form method="post" action="/brigade/fault/change" target="noredirect">
            <p>
                <label for="seg_fault_id2">Segment fault id</label>
                <input type="number" id="seg_fault_id2" name="segFaultId" required>
            </p>
            <p>
                <label for="fault_status3">Fault status</label>
                <input type="text" id="fault_status3" name="faultStatus" required>
            </p>
            <button type="submit">Create</button>
        </form>
    </div>
</div>
<br>
<button onclick="window.location.href='/home'" style="float: right">Home</button>
<button onclick="window.location.href='/api/brigade/routes'" style="float: right">Manage Routes</button>
</body>
</html>
