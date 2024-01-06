<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Test Jsp Page</title>
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