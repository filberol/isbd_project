<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru_RU">
<head>
    <title>Test Jsp Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>

<h3>Insert new company</h3>
<iframe name="noredirect" id="noredirect" style="display: none;"></iframe>
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

</body>
</html>