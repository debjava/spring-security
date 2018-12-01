<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="css/app.css" />" rel="stylesheet"
	type="text/css">
<title>Admin</title>
</head>
<body class="security-app">
	<div class="details">
		<h2>Admin Page </h2>
		<a href="http://www.programming-free.com/2015/08/spring-security-for-spring-mvc-4.html class="button green small">Tutorial</a> 
		<a href="https://github.com/priyadb/SpringSecurityApp/archive/master.zip"
			class="button red small">Download</a>
	</div>
	<div class="lc-block">
		<h1>Welcome!</h1>
		<div class="alert-normal">
			Click <a href="<spring:url value='/hello' />">here</a> to see a
			greeting.
		</div>
	</div>
</body>
</html>
