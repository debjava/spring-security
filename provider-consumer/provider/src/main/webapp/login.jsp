<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="javax.servlet.jsp.PageContext" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Spring Security Tutorial - 3-Legged OAuth 1.0 - Provider Server!</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link href="<c:url value='/static/fav.png'/>" rel="shortcut icon">
		<link href="<c:url value='/static/style.css'/>" rel="stylesheet" type="text/css"/>
	</head>
	
	<body>
		<H1>Spring Security Tutorial - 3-Legged OAuth 1.0 - Provider Login!</H1>
		
		<p>You must login in order to Authorize the Request Token.</p>
		<p><strong>Username</strong>: user</p>
		<p><strong>Password</strong>: user<br><br></p>
		
		<form id="form" action="<c:url value='/login.do'/>" method="POST">
		
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				<div class="msg-container error">
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
				</div>
			</c:if>
			<c:if test="${not empty param.out}">
				<div class="msg-container logout">
					You've logged out successfully.
				</div>
			</c:if>
			
			Username:<br>
			<input type="text" name="username" value="" class="input-text input-email<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}"> input-error</c:if>"/><br><br>
			Password:<br>
			<input type="password" name="password" value="" class="input-text input-pass<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}"> input-error</c:if>"/>
			
			<div class="submit-container">
				<input value="Login" name="submit" type="submit" class="submit-btn"/>
			</div>
		</form>
	</body>
</html>
