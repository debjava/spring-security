<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Spring Security Tutorial - 3-Legged OAuth 1.0 - Provider Server!</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Expires" content="Mon, 22 Jul 1970 11:12:01 GMT">
        
		<link href="<c:url value='/static/fav.png'/>" rel="shortcut icon">
		<link href="<c:url value='/static/style.css'/>" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<H1>Spring Security Tutorial - 3-Legged OAuth 1.0 - Provider Server!</H1>
		
		<p>Welcome to the Provider server. The Provider's OAuth authenticated API endpoint is protected by the ROLE_OAUTH role
		which means it can only be accessed through an OAuth authenticated request (which assigns the relevant role).
		Click the link below to check the protection on the endpoint.</p>
		<p><a href="http://localhost:8080/api/protected" target="_blank">http://localhost:8080/api/protected</a></p>
		
		<p>The Provider uses Spring Security Form Login which is required in order to complete the 3-Legged authentication.
		The second step (authenticating the request token) requires the user to login as the Authentication object used comes
		from the User's session. Click the link below to navigate to the login page.</p>
		<p><a href="http://localhost:8080/login.jsp">http://localhost:8080/login.jsp</a></p>
		
		<p>If you wish to logout, click the link below.</p>
		<p><a href="http://localhost:8080/logout.do">http://localhost:8080/logout.do</a></p>
		
		<p>Use the Consumer server (link below) to make a request to the OAuth protected resource on the Provider server.</p>
		<p><a href="https://localhost:8444/" target="_blank">Consumer Server</a></p>

	</body>
</html>
