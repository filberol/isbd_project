<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html lang="ru_RU">
<head>
    <title>Test Jsp Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<h3>
    Some content
</h3>
<c:forEach items="${workers}" var="worker">
    <h1>${worker}</h1>
</c:forEach>
</body>
</html>