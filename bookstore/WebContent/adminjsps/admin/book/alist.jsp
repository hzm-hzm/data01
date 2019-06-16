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
			
				#book1{
				width:150px;
				height: 220px;
				border: solid 2px white;
				text-align: center;
				font-size: 18px;
				margin-top: 20px;
				margin-left: 20px;
				float: left;
			}
			#book1 img{
				width: 140px;
				height: 190px;
			}
			#book1 a{
				text-decoration: none;
			}
			#book1:hover{
				border: solid 2px red;
			}
			
			#down_books{
				margin-top:-80px;
				width: 100%;
				height: 100%;
			}
			#books{
				width: 100%;
				height: 100%;
			}
			hh{
				float: left;
				font-size: 16px;
			}
		</style>
	</head>
		<h2>分类：${category.cname }</h2>
		
		<div id="books">
		<hr/>
			<hh>上架书籍：</hh><br/>
			<c:choose>
				<c:when test="${map.booklist eq null }">
					无。<br/>
				</c:when>
				<c:otherwise>	
			<c:forEach items="${map.booklist }" var="book">
			<div id="book">
				<a href="<c:url value='/Admin/AdminBookServlet?method=findonebook&bid=${book.bid }&down=null'/>">
				<img src="<c:url value='/${book.image }'/>" />
						${book.bname }
				</a>
			</div>
			</c:forEach>	
				</c:otherwise>
			</c:choose>
		</div>
		
		
		<div id="down_books">
		<hr/>
			<hh>下架书架：</hh>
			<c:choose>
				<c:when test="${map.down_booklist eq null }">
					无。<br/>
				</c:when>
			<c:otherwise>	
			<br/>
			<c:forEach items="${map.down_booklist }" var="book">
			<div id="book1">
				<a href="<c:url value='/Admin/AdminBookServlet?method=findonebook&bid=${book.bid }&down=down'/>">
				<img src="<c:url value='/${book.image }'/>" />
						${book.bname }
				</a>
			</div>
			</c:forEach>
				</c:otherwise>
					</c:choose>
			
		</div>
	</body>
</html>