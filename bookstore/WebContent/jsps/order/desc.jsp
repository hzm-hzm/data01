<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<title>订单付款</title>
	<style>
			body{
			
			}
			
			.both{
				border:solid 1px;
				position: relative;
			}
			.biao{
				width: 100%;
				height: 100%;
				position: relative;
			}
			.biao tr{
				height: 120px;
			}
			.biao tr td{
				width: 200px;
				text-align: center;
				font-size: 15px;
			}
			.biao tr td a{
				text-decoration: none;
				color: red;
			}
			.bth{
				position: absolute;
				width: 100px;
				border: solid 2px;
				text-align:  center;
				left: 650px;
				border-radius: 15%;
				bottom: 10px;
			}
			.bth:hover{
				background-color:darkorange;
				width: 120px;
				height: 30px;
				font-size: 20px;
			}
			.su_biao{
				margin-top: 10px;
				font-size: 20px;
				height: 120px;
				margin-left: 20px;
			}
			.su_bth{
				border: solid 0px;
				width: 100px;
				height: 40px;
				background-color: white;
				font-size: 20px;
				position: absolute;
				bottom: 10px;
				left: 80px;
				border: solid 2px;
				border-radius: 15%;
				cursor: pointer;
			}
			.su_bth:hover{
				width: 120px;
				height: 50px;
				background-color: gold;
			}
			.te_bth{
				height:20px;
				font-size: 20px;
			}
			#address{
				width: 500px;
			}
			img{
			width: 140px;
			height: 180px;
			}
			#heard{
			width: 100%;
			height:30px;
			background-color:gray;
			color: white;
			font-size: 20px;
			}
			hh{
/* 				margin-left: 50px; */
				position:absolute;
				right:0px;
				font-size: 15px;
			}
			qq{
 			color: blue;
		} 
		e{
			color: red;
		}
		</style>
	</head>
	<body>
	<h3>当前订单</h3>
		<div class="both">
		<div id="heard">
		订单号：${sessionScope.order.oid }
		</div>
			<table border="1px" class="biao">
				<tr  style="height: 20px;border:  0px; margin-top: 50px;">
					<td></td>
					<td>书名</td>
					<td>价钱</td>
					<td>数量</td>
					<td>金额</td>
				</tr>
				
				<c:forEach items="${sessionScope.order.orderitemlist }" var="items">
				<tr>
					<td><img  src="<c:url value='/${items.book.image }'/>"></td>
					<td>${items.book.bname }</td>
					<td>${items.book.price }</td>
					<td>${items.count }</td>
					<td>${items.subtotal }</td>
				</tr>
				</c:forEach>
				
<%-- 				<c:forEach items="${session.order.orderitemlist }" var="orderitems"> --%>
<!-- 				<tr> -->
<%-- 					<td><img  src="<c:url value='/${orderitems.book.image }'/>"></td> --%>
<%-- 					<td>${orderitems.book.bname }</td> --%>
<%-- 					<td>${orderitems.book.price }</td> --%>
<%-- 					<td>${orderitems.count }</td> --%>
<%-- 					<td>${orderitems.subtotal }</td> --%>
<!-- 				</tr> -->
<%-- 				</c:forEach> --%>
				

				<tr style="height: 50px;">
					<td colspan="7" style="text-align:left; border: 0px;font-size: 25px;">总金额：<qq>${sessionScope.order.total }</qq>
					<hh>创建日期:${sessionScope.order.ordertime }</hh></td>
					
				</tr>
			</table>
			<form action="<c:url value='/OrdersServlet'/>" method="post" class="su_biao">
			<input type="hidden" name="method" value="to_orderbuy"/>
			<input type="hidden" name="oid" value="${sessionScope.order.oid }"/>
				请输入您的电话号码：<input class="te_bth" type="text" name="cellphone" value="${order.cellphone }" /><e>${error }</e><br />
				请输入您的收货地址：<input  class="te_bth" id="address" type="text" name="address" value="${order.address }" /><br />
				<input type="submit" value="点击付款" class="su_bth" />
			</form>
		</div>
	</body>
</html>