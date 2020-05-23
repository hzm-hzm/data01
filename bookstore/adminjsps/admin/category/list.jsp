<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>category_list</title>
		<style>
			body{
				background-color: beige;
			}
			#tr{
				background-color: #FF8C00;
			}
			table{
				font-size: 20px;
				border: solid 2px;
				width: 700px;
				text-align: center;
				margin-left: 10px;
				margin-top: 20px;
			}
			table tr td{
				border: solid 1px;
				height: 40px;
			}
			a div{
				float: left;	
				width: 80px;
				height: 28px;
				background-color: #FFE4C4;
				border:  solid 2px black;
			}
			a div:hover{
				background-color: #A9A9A9;
				color: white;
			}
			#delect:hover{
				background-color: red;
				color: white;
			}
			#desc{
				margin-left: 30px;
			}
			#delect{
				margin-left: 30px;
				color: red;
			}
			e{
				font-size: 20px;
				margin-left: 100px;
				color: red;
			}
			/* table tr td {
				border-right: solid 2px;
				border-top: solid 2px;
			} */
			#count{
				background-color: white;
			}
			#count div{
				width: 100%;
				height: 100%;
				background-color: beige;
				border: solid 0px;
				font-size: 25px;
			}
			#count div:hover{
				background-color: gray;
			}
		</style>
	</head>
	<body>
		<h2>查看分类(点击数量查看相关类别书籍)</h2>
		<e>${msg }</e>
			<div id="category_main">
				<table>
					<tr id="tr">
						<td style="width: 300px;">类别名</td>
						<td>书籍数量</td>
						<td style="width: 250px;">操作</td>
					</tr>
					<c:forEach items="${categorylist }" var="categorylist">
					<tr>
						<td>${categorylist.cname }</td>
						<td><a href="<c:url value='/Admin/AdminBookServlet?method=findallbookbycid&cid=${categorylist.cid }'/>" id="count"><div>${categorylist.count }</div></a></td>
						<td>
							<a href="<c:url value='/Admin/AdminCategoryServlet?method=desccategory&cid=${categorylist.cid }'/>"><div id="desc">修改</div></a>
							<a href="<c:url value='/Admin/AdminCategoryServlet?method=delectcategory&cid=${categorylist.cid }'/>"><div id="delect">删除</div></a>
						</td>
					</tr>
					</c:forEach>
				
					
				</table>
			</div>
	</body>
</html>