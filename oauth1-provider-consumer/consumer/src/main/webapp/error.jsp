<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="javax.servlet.jsp.PageContext" %>
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
		<H1>Oops! OAuth threw a Wobbly!</H1>
		
		<p>Go back to the <a href="<c:url value="/index.jsp"/>">index page</a></p>
		
		<p>There was a problem with the OAuth mechanism: </p>
		
		<c:if test="${not empty OAUTH_FAILURE_KEY}">
			<p><c:out value="${OAUTH_FAILURE_KEY.message}"/></p>
        </c:if>
	</body>
</html>
