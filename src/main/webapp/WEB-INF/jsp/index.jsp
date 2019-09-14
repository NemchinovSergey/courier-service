<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:url value="/assets/css/index.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
    <title>Main page</title>
</head>
<body>
    <p><a href="${pageContext.request.contextPath}/courier">Courier cabinet</a></p>
    <p><a href="${pageContext.request.contextPath}/operator">Operator cabinet</a></p>
</body>
</html>