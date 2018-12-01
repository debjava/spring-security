<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <a href=<c:url value="/j_spring_security_logout"/>>Logout</a><br/>

    <!-- <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <h1>Only admin can see this</h1><br/>
        <a href="admin"> Admin Home </a>
    </sec:authorize> -->

    <h1>Welcome, This page is for normal user</h1>

</body>
</html>