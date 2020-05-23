<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>category_desc</title>
		<style>
			body{
				font-size:30px;
			}
			#main{
				width: 500px;
				height: 300px;
				border: solid 2px;
				margin-left: -7px;
				margin-top: -8px;
				background-color: bisque;
			}
			form{
				width: 450px;
				height: 200px;
				margin-top: 40px;
				margin-left: 25px;
			}
			#input{
				float: right;
				border: 0px;
				/* background-color: white; */
				width: 80px;
				height: 35px;
				font-size: 20px;
				margin-top: 6px;
			}
			#input:hover{
				cursor: pointer;
				background-color: aquamarine;
				color: white;
				border: solid 2px;
			}
			#text{
				height: 25px;
				width: 220px;
				font-size: 20px;
			}
			er{
				color: red;
				margin-left: 130px;
			}
			
		</style>
	</head>
	<body>
<div id="main">

	<c:choose>
		<c:when test="${!empty desc }">
			<h5>修改类别</h5>
			<form action="<c:url value='/Admin/AdminCategoryServlet'/>" method="post">
			<input type="hidden" name="method" value="updatecategory">
			<input type="hidden" name="cid" value="${category.cid }">
				类别名：<input id="text" type="text" name="cname" value="${category.cname }" />
				<br><er>${error }</er>	
				<input type="submit" value="修改" id="input" />
			</form>
		</c:when>
		<c:otherwise>
		<h5>添加类别</h5>
			<form action="<c:url value='/Admin/AdminCategoryServlet'/>" method="post">
			<input type="hidden" name="method" value="addcategory">
				类别名：<input id="text" type="text" name="cname"  />
				<br><er>${error }</er>	
				<input type="submit" value="添加" id="input" />
			</form>
		</c:otherwise>
	</c:choose>
	
	</div>

		
	
	</body>
</html>