<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:url value="/assets/css/index.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
    <title>${title}</title>
</head>
<body>
    <h1>${title}</h1>
</body>
</html>