<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>top</title>
		<style>
			body{
				text-align: center;
				background-color: burlywood;
			}
			#bt{
				margin-top: 0px;
			}
			h{
				font-size: 60px;
				text-align: center;
				
			}
			#admin{
				margin-top: 5px;
				text-align: left;
			}
			#a div{
			width: 70px;
			height: 35px;
			float: right;
			border: solid 2px;
			margin-top: -10px;
			text-align: center;
			font-size: 24px;
			background-color: white;
			color: red;
			}
			#a div:hover {
				background-color: red;
				color: white;
			}
		</style>
	</head>
	<base target="body" />
	<body>
		<div id="bt">
			<h>后台管理系统</h>
		</div>
		<div id="admin">
			<hh>欢迎上班，管理员：${sessionScope.admin.adminname }同志</hh>
			<a href="<c:url value='/AdminServlet?method=exit'/>" id="a" target="_parent"><div>退出</div></a>
		</div>
	</body>
</html>