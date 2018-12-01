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
		<H1>Authorize the Request Token!</H1>
		
		<p>Please authorize the Request Token.</p>
		
		<form action="<c:url value="/oauth/authorize_token"/>" method="post">
	        <input name="oauth_token" value="<c:out value="${oauth_token}"/>" type="hidden"/>
	        <c:if test="${!empty oauth_callback}">
	        	<input name="callbackURL" value="<c:out value="${oauth_callback}"/>" type="hidden"/>
	        </c:if>
			<div class="submit-container">
				<input value="Authorize" name="submit" type="submit" />
			</div>
	      </form>
	</body>
</html>
