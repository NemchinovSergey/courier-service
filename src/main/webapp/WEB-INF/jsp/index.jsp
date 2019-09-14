<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:url value="/assets/css/index.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
    <title>Служба обработки заказов</title>
</head>
<body>
    <p><a href="courier">Кабинет курьера</a></p>
    <p><a href="operator">Кабинет оператора call-центра</a></p>
</body>
</html>