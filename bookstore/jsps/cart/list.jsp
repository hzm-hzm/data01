<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<title>购物车条目</title>
		<style>
			body{
			
			}
			.clean{
				height: 40px;
				text-align: right;
			}
			.clean a{
				text-decoration: none;
				
			}
			.clean_cat{
				font-size: 20px;
				position: absolute;
				top: 5px;
				left: 650px;
				width: 120px;
				border-radius: 15%;
				border:solid 2px #000000;
				text-align: center;
				color: #000000;
			}
			.clean_cat:hover{
				background-color: #FF0000;
			}
			.both{
				border:solid 2px;
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
			img{
			width: 140px;
			height: 180px;
			}
			.i1{
			width: 450px;
			height: 280px;
			float: left;
			}
			k{
			font-size: 45px;
			float: left;
			margin-top: 50px;
			}
			x{
			font-size: 35px;
			float: left;
			margin-left: 50px;
			}
		</style>
	</head>
	<body>
	<h3>我的购物车</h3>
	<c:choose>
		<c:when test="${empty sessionScope.cart or fn:length(sessionScope.cart.cartItems) eq 0 }">
			<img class="i1" src="<c:url value='/img/cart.jpg'/>" >
			<k>空空如也~</k>
			<br/>
			<x>心动不如行动！</x>
		</c:when>
		<c:otherwise>
			<div class="both">
			<div class="clean"><a href="<c:url value='/CartServlet?method=clean'/>"><div class="clean_cat">清空购物车</div></a></div>
			<table border="1px" class="biao">
				<tr  style="height: 20px;border:  0px; margin-top: 50px;">
					<td></td>
					<td>书名</td>
					<td>价钱</td>
					<td>数量</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				
				<c:forEach items="${sessionScope.cart.cartItems}" var="cartitem">
				<tr> 
					<td><img src="<c:url value='/${cartitem.book.image }'/>"></td>
					<td>${cartitem.book.bname }</td>
					<td>${cartitem.book.price }</td>
					<td>${cartitem.count }</td>
					<td>${cartitem.subtotal }</td>
					<td><a href="<c:url value='/CartServlet?method=delect&bid=${cartitem.book.bid }'/>">删除</a></td>
				</tr>
				</c:forEach>
				
				<tr style="height: 50px;">
					<td colspan="7" style="text-align:left; border: 0px;font-size: 25px;">总金额：${sessionScope.cart.subtotal }</td>
				</tr>
			</table>
			<a href="<c:url value='/OrdersServlet?method=creat_order'/>" style="color: black;text-decoration: none; "><div class="bth">付 款</div></a>
		</c:otherwise>
	</c:choose>

		</div>
	</body>
</html>