<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<title>管理员登录</title>
		<style>
			body{
				background-color: darkgray;
			}
			#main{
				width: 600px;
				height: 300px; 
				border: solid 1px #000000;
				margin-top: 100px;
				margin-left: auto;
				margin-right: auto;
				text-align: center;
				background-color: blanchedalmond;
			}
			.biao{
				margin-top: 70px;
				font-size: 25px;
			}
			#sub{
				border: 0px;
				width: 70px;
				height: 35px;
				border: solid 1px;
				border-radius: 15%;
				font-size: 20px;
			}
			#sub:hover{
				background-color: aquamarine;
				cursor: pointer;
			}
			m,e,d{
				color: red;
			}
		</style>
	</head>
	<body>
		<div id="main">
			<form action="<c:url value='/AdminServlet'/>" method="post" class="biao" target="_top">
			<input type="hidden" name="method" value="adminlogin"/>
					<m>${msg }</m>
					<e>${error }</e>
					<d>${d }</d><br/>
				账号：<input type="text" name="adminname" value="" /><br/>
				密码：<input type="password" name="adminpassword" value="" /><br /><br />
				<input type="submit" value="登录" id="sub" />
			</form>
		</div>
	</body>
</html>