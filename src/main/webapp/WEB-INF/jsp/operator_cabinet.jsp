<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://courier.nsergey.com/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
	<c:url value="/assets/css/index.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
    <title>${title}</title>
</head>
<body>
    <h1>Список заявок для прозвона клиентов</h1>
    <p>Заявок: ${tasks.size()}</p>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>#</th>
            <th>Номер заявки</th>
            <th>Номер заказа</th>
            <th>Заявка создана</th>
            <th>Согласована</th>
            <th>Действия</th>
        </tr>
        </thead>
        <c:forEach items="${tasks}" var="task" varStatus="loop">
            <jsp:useBean id="task" scope="page" type="com.nsergey.courier.db.model.Task"/>
            <tr data-taskDone="${task.done}">
                <td>${loop.index+1}</td>
                <td>${task.id}</td>
                <td>${task.orderId}</td>
                <td>${fn:formatDateTimeTz(task.created, zoneId)}</td>
                <td>${task.done}</td>
                <td>
                    <form method="POST" action="actions/schedule?taskId=${task.id}">
                        <input type="submit" value="Завершить согласование">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="${pageContext.request.contextPath}/">На главную</a></p>
</body>
</html>