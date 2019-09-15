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
    <h1>Список заказов на доставку</h1>
    <p>Курьер: ${courier.name}</p>
    <p>Заказов: ${orders.size()}</p>

    <table border="1">
        <thead>
        <tr>
            <th>#</th>
            <th>Номер заказа</th>
            <th>Заказчик</th>
            <th>Адрес</th>
            <th>Состояние</th>
            <th>Дата/время доставки</th>
            <th>Перенести</th>
        </tr>
        </thead>
        <c:forEach items="${orders}" var="order" varStatus="loop">
            <jsp:useBean id="order" scope="page" type="com.nsergey.courier.db.model.Order"/>
            <tr data-orderState="${order.state}">
                <td>${loop.index+1}</td>
                <td>${order.id}</td>
                <td>${order.customerName}</td>
                <td>${order.address}</td>
                <td>${order.state.title}</td>
                <td>${fn:formatDateTimeTz(order.deliveryTime, zoneId)}</td>
                <td>
                    <form method="POST" action="actions/reschedule?orderId=${order.id}">
                        <input type="submit" value="Не успеваю">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>