<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
			<title>图书列表</title>
			<style>
				body{
					font-size: 10pt;
					/* background-color: gainsboro; */
/* 					background-image: url(<c:url value='/img/beijing.jpg'/>); */
					
				}
				img{
					width: 150px;
					height: 200px;
					border: solid 1px;
				}
				a{
					font-size: 15px;
					text-decoration: none;
				}
				.icon
				{   
					width: 160px;
					height: 250px;
					float: left;
					margin-left: 80px;
					text-align: center;
					margin-top: 30px;
					
				}
				/* .icon div{
					width: 150px;
					height: 200px;
				} */
				.icon div img:hover{
					width: 175px;
					height: 225px;
					border:  solid 3px black ;
					
				}
				b{
					width: 50px;
					height: 50px;
					margin-left:-720px;
					font-size:22px;
					margin-top: -20px;
				}
			</style>
		</head>
		<body>
		<c:choose>
			<c:when test="${empty cname }">
				<b>全部分类</b>
			</c:when>
			<c:otherwise>
				<b>${cname }</b>
			</c:otherwise>
		</c:choose>
			
			<c:forEach items="${booklist }" var="books">
				<div class="icon">
				<div><a href="<c:url value='/BookServlet?method=showbook&bid=${books.bid }'/>"><img src="<c:url value='/${books.image }'/>"  border="0"/></a></div>
				<a style="margin-top: -30px;" href="<c:url value='/BookServlet?method=showbook&bid=${books.bid }'/>">${books.bname }</a>
			</div>
			</c:forEach>

		</body>

</html>