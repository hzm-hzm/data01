<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
		<meta charset="utf-8">
		<title>book_list</title>
		<style>
			#book{
				width:150px;
				height: 220px;
				border: solid 2px white;
				text-align: center;
				font-size: 18px;
				margin-top: 20px;
				margin-left: 20px;
				float: left;
			}
			#book img{
				width: 140px;
				height: 190px;
			}
			#book a{
				text-decoration: none;
			}
			#book:hover{
				border: solid 2px red;
			}
		</style>
	</head>
	<body>
	<c:choose>
		<c:when test="${down eq null}">
			<h2>查看图书（点击后详情）</h2>
		</c:when>
		<c:otherwise>
			<h2>下架图书（点击后详情）</h2>
		</c:otherwise>
	</c:choose>
		
		<hr />
		<c:forEach items="${booklist }" var="book">
		<div id="book">
			
	<c:choose>
		<c:when test="${down eq null}">
			<a href="<c:url value='/Admin/AdminBookServlet?method=findonebook&bid=${book.bid }&down=null'/>">
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/Admin/AdminBookServlet?method=findonebook&bid=${book.bid }&down=down'/>">
		</c:otherwise>
	</c:choose>
			
			<img src="<c:url value='/${book.image }'/>" />
			${book.bname }
			</a>
		</div>
		</c:forEach>
	</body>
</html>