<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<title>图书详情</title>
		<style>
			body{
				font-size: 15px;
				background-image: url(<c:url value='/img/beijing.jpg'/>);
			}
			img{
				width: 150px;
				height: 200px;
				border: solid 1px;
			}
			#both{
				margin-left:50px;
				margin-top: 35px;
			}
			#both ul li{
				font-size: 20px;
				list-style: none;
				margin-left: -30px;
			}
			.number{
				width: 70px;
				height: 25px;
				font-size: 20px;
			}
			h{
				font-size: 22px;
				margin-left: 8px;
			}
			.biao{
				margin-top: -15px;
			}
			.add{
				text-decoration: none;
				margin-top: 10px;
				margin-left: 20px;
				width: 100px;
				height: 50px;
				border-radius: 15%;
				border: 0px;
				background-color: white;
				font-size: 18px;
				cursor: pointer;
			}
			.add:hover{
				background-color:aquamarine;
				border:solid 1px;
			}
			.buy
			{
				text-decoration: none;
				margin-top: 10px;
				margin-left: 20px;
				width: 100px;
				height: 50px;
				border-radius: 15%;
				border: 0px;
				background-color: white;
				font-size: 18px;
				cursor: pointer;
				float: left;
			}
			.say{
				position: absolute;
				width: 400px;
				height: 420px;
				top: 35px;
				left: 350px;
				border: solid 2px;
				background-color: white;
				font-size: 20px;
			}
			jj{
				font-size: 50px;
				text-align: center;
			}
			.li-biao{
				width: 350px;
				height: 380px;
				margin-left:20px;
				margin-top:10px;
			}
		</style>
	</head>
	<body>
		<div id="both">
			<div class="image"><img src="<c:url value='/${book.image }'/>"></div>
			<ul>
				<li>书名：${book.bname }</li>
				<li>价格：${book.price }</li>
				<li>作者：${book.author }</li>
			</ul>
			<form action="<c:url value='/CartServlet'/>" method="post" class="biao">
			<input type="hidden" name="method" value="add">
			<input type="hidden" name="bid" value="${book.bid }">
				<h>数量</h>：<input type="text" name="number" class="number" value="1"  /><br />
				<input type="submit" value="加入购物车" class="add" />
			</form>
			<div class="say"><div class="li-biao">
			<jj>简介:</jj><hr><br/> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
			${book.evaluate }
			</div></div>
		</div>
	</body>
</html>