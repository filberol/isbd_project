<%@ taglib prefix="security" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html lang="ru-RU">
<head>
    <title>About user</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<h2>Username: ${userDetails.name}</h2>
<h3>Authorities: ${userDetails.authorities}</h3>
<h3>Session: ${userDetails.details.sessionId}</h3>
</body>
</html>
