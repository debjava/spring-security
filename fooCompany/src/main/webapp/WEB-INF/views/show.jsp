<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Your CV in fooCompany
</h1>
<p>
	<c:out value="${cv}"></c:out>
</p>

</body>
</html>