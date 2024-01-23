<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<form method="get" action="/home">
    <label for="station_search">Station name</label>
    <input
            list="station_suggests"
            type="text"
            id="station_search"
            name="stationName"
            placeholder="Type station name"
    >
    <button type="submit">Search</button>
</form>
<datalist id="station_suggests">
    <c:forEach items="${suggested}" var="option">
    <option value="${option.name()}">
        </c:forEach>
</datalist>