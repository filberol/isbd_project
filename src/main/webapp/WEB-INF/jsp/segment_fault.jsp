<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:useBean id="fault" scope="request" type="com.isbd.coursework.entities.SegmentFault"/>

<html>
<head>
    <title>Resource allocations for base</title>
    <jsp:include page="jsp_style.jsp"/>
</head>
<body style="padding: 10px">
<br>
<h2>Viewing segment fault  id ${fault.id()}</h2>
<h3>Class - ${fault.faultClass()}</h3>
<h3>Status - ${fault.faultStatus()}</h3>
<h3>Railway segment id - ${fault.rwSegId()}</h3>
<h3>Position, km - ${fault.positionPointKm()}</h3>
<br>
<button onclick="window.location.href='/api/brigade/faults'" style="float: right">Back to faults</button>
</body>
</html>

