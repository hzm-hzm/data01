<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>book_add</title>
		<style>
			#table{
				width:750px;
				height: 550px;
				border: solid 1px;
				background-color: #FFE4C4;
				margin-top: -8px;
				margin-left: -7px;
				border: solid 2px;
			}
			.zuo{
				width: 600px;
				height: 400px;
				font-size: 23px;
				margin-top: 80px;
				margin-left: 0px;
			}
			#say{
				width: 280px;
				height: 360px;
				margin-left: 370px;
				margin-top:-180px;
				border: solid 2px;
				background-color: white
			}
			.jj{
				background-color: #A9A9A9;
			}
			img{
				width: 145px;
				height: 200px;
				border: solid 0.125rem;
			}
			input{
				width: 170px;
			}
			#texts{
				font-size: 20px;
			}
			#sub{
				border: none;
				width: 110px;
				height: 40px;
				font-size: 18px;
				cursor: pointer;
				margin-left: 120px;
				border: solid 2px;
			}
			#sub:hover{
				background-color: yellow;
				
			}
			h{
				font-size: 25px;
			}
			#select{
				width: 120px;	
				height: 22px;
			}
			e{
				color: red;
				font-size: 14px;
			}
		</style>
	</head>
	<body>
		<div id="table">
			<h>添加书籍:</h>
			<div class="zuo">
			<ul style="list-style: none;">
				
				<form action="<c:url value='/Admin/AdminAddBookServlet'/>" method="post" enctype="multipart/form-data">
				<li>书图：<input type="file" name="image"><e>${msg }</e></li>
				<li>书名：<input type="text" name="bname" value="${book.bname }"><e>${error.bname }</e></li>
				<li>价格：<input type="text" name="price" value="${book.price }"><e>${error.price }</e></li>
				<li>作者：<input type="text" name="author" value="${book.author }"><e>${error.author }</e></li>
				<li>类别：<select name=cid id="select">
					<c:forEach items="${categorylist }" var="category">
						<option value="${category.cid }">${category.cname }</option>
					</c:forEach>
					</select></li>
				<li id="say">
					<div class="jj">简介:</div>
					<textarea name="evaluate" id="texts"  rows="14" cols="25"></textarea>
					</li>
					<input id="sub" type="submit" value="点击添加" />
				</form>
				
			</ul>
			</div>
		</div>
	</body>
</html>