<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>book_amend</title>
		<style>
			#table{
				width: 750px;
				height: 550px;
				border: solid 1px;
				background-color: #7FFFD4;
				margin-top: -8px;
				margin-left: -7px;
				border: solid 2px;
			}
			.zuo{
				width: 600px;
				height: 400px;
				background-color: #7FFFD4;
				font-size: 23px;
				margin-top: 20px;
				margin-left: 30px;
			}
			#say{
				width: 280px;
				height: 360px;
				margin-left: 330px;
				background-color: red;
				margin-top: -300px;
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
				width: 120px;
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
				width:120px;
				height: 20px;
			}
			e{
				font-size: 15px;
				color: red;
			}
		</style>
	</head>
	<body>
		<div id="table">
			<h>修改书籍:</h>
			<div class="zuo">
			<ul style="list-style: none;">
				<li><img src="<c:url value='/${book.image }'/>"></li>
				
				<form action="<c:url value='/Admin/AdminBookServlet'/>" method="post">
				<input type="hidden" name="method" value="updatebook">
				<input type="hidden" name="bid" value="${book.bid }">
				<input type="hidden" name="image" value="${book.image }">
				<li>书名：<input type="text" name="bname" value="${book.bname }"><e>${error.bname }</e></li>
				<li>价格：<input type="text" name="price" value="${book.price }"><e>${error.price }</e></li>
				<li>作者：<input type="text" name="author" value="${book.author }"><e>${error.author }</e></li>
				<li>类别：<select id="select" name="cid">
						<c:forEach items="${categorylist }" var="category">
							<option value="${category.cid }" <c:if test="${category.cid eq book.category.cid }">
							selected="selected"</c:if>>${category.cname }</option>`
						</c:forEach>
						</select>
				</li>
				<li id="say">
					<div class="jj">简介:</div>
					<textarea name="evaluate" id="texts"  rows="14" cols="25">
					${book.evaluate }
					</textarea>
					</li>
					<input id="sub" type="submit" value="修改完成" />
				</form>
				
			</ul>
			</div>
		</div>
	</body>
</html>