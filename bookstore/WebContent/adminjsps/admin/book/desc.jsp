<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>book_desc</title>
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
				background-color: white;
				font-size: 20px;
			}
			.jj{
				background-color: #A9A9A9;
			}
			img{
				width: 145px;
				height: 200px;
				border: solid 0.125rem;
			}
			a{
				text-decoration: none;
				float: left;
				margin-left: 50px;
				text-align: center;
				color: #000000;
			}
			a div{
				width: 90px;
				height: 35px;
				border: solid 2px;
				border-radius: 15%;
				background-color: #FFE4C4;
			}
			#desc:hover{
				background-color: #FFFF00;
				color: white;
			}
			#delect:hover{
				background-color: #FF0000;
				color: white;
			}
			h{
				font-size: 25px;
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
			#select{
				width:120px;
				height: 20px;
			}
			eee{
			color: red;
			}
		</style>
	</head>
	<body>
		<div id="table">
			<h>书籍详情：</h>
			<div class="zuo">
			<ul style="list-style: none;">
				<li><img src='<c:url value='/${book.image }'/>'></li>
				<li>书名：${book.bname }</li>
				<li>价格：${book.price }</li>
				<li>作者：${book.author }</li>
				<li>类别：${book.category.cname }</li>
						<li id="say">
							<div class="jj">简介:</div>
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;${book.evaluate }
						</li>
				
				
						<c:choose>
							<c:when test="${down eq null}">
								<a href="<c:url value='/Admin/AdminBookServlet?method=to_updatebook&bid=${book.bid }'/>" ><div id="desc">修改</div></a>
								<a href="<c:url value='/Admin/AdminBookServlet?method=downbook&bid=${book.bid }'/>" ><div id="delect">下架</div></a>
							</c:when>
							<c:otherwise>
							<eee>${msg }</eee>
								<a href="<c:url value='/Admin/AdminBookServlet?method=recoverbook&bid=${book.bid }'/>" ><div id="desc">恢复</div></a>
								<a href="<c:url value='/Admin/AdminBookServlet?method=delectbook&bid=${book.bid }'/>" ><div id="delect">删除</div></a>
							</c:otherwise>
						</c:choose>



				
			</ul>
			</div>
		</div>
	</body>
</html>