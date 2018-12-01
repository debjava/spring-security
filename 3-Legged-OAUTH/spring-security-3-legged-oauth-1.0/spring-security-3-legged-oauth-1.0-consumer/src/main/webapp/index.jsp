<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Spring Security Tutorial - 3-Legged OAuth 1.0 - Consumer Server!</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Expires" content="Mon, 22 Jul 1970 11:12:01 GMT">

		<link href="<c:url value='/static/fav.png'/>" rel="shortcut icon">
		<link href="<c:url value='/static/style.css'/>" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<H1>Spring Security Tutorial - 3-Legged OAuth 1.0 - Consumer Server!</H1>
		
		<p>Click the link below to make a call to the Provider Server's protected API end point.</p>

		<p><a href="<c:url value="/api/proxy"/>">Get Protected Resource</a></p>
	</body>
</html>
