<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<title>admin_order</title>
		<style>
			
			.biao{
				width: 100%;
				height: 100%;
				margin-top: 20px;
			}
			.biao tr{
				height: 75px;
				margin-top: 20px;
			}
			.biao tr td{
				width: 200px;
				text-align: center;
				font-size: 13px;
			}
			.biao tr td a{
				text-decoration: none;
				color: red;
			}
			img{
			width: 65px;
			height: 70px;
			}
			
		</style>
	</head>
	<body>
	<h3>客户订单</h3>
		<div class="both">

<%-- 		<c:choose> --%>
<%-- 			<c:when test="${empty sessionScope.orderlist }"> --%>
<%-- 				<img style="width: 250px;height: 250px"  src='<c:url value="/img/pig.jpg"/>'> --%>
<!-- 				<h1>亲，您还无下单呢。</h1> -->
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
			
					<c:forEach items="${sessionScope.orderlist }" var="order">
			<table border="1px" class="biao">
				<tr style="height: 20px;background-color:gold;border: solid #000000 2px; ">
					<td colspan="7" style="text-align: left;">
						订单号:${order.oid } &nbsp; &nbsp;  时间：${order.ordertime } &nbsp; &nbsp; 
						总金额：${order.total }   &nbsp; &nbsp;
						<c:choose>
							<c:when test="${order.state eq 1 }">无付款</c:when>
							<c:when test="${order.state eq 2 }">
								<a href="<c:url value='/Admin/AdminOrdersServlet?method=updateorders&oid=${order.oid }'/>">发货</a>
							</c:when>
							<c:when test="${order.state eq 3 }">已经发货</c:when>
							<c:when test="${order.state eq 4 }">已经确认收货</c:when>	
						</c:choose>
				</tr>
				<c:forEach items="${order.orderitemlist }" var="orderitem">
					<tr>
					<td style="width: 80px"><img src="<c:url value='/${orderitem.book.image }'/>"></td>
					<td style="width: 400px">书名：${orderitem.book.bname }</td>
					<td>价格：${orderitem.book.price }</td>
					<td>数量：${orderitem.count }</td>
					<td>金额：${orderitem.subtotal }</td>
				</tr>
				</c:forEach>
			</table>
		</c:forEach>
			
<%-- 			</c:otherwise> --%>
<%-- 		</c:choose> --%>
		

		</div>
	</body>
</html>