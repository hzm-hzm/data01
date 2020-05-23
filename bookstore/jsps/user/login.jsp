<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>登录界面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
		<style>
			body{
				background-color: graytext;
			}
			#login_table{
				margin-left: auto;
				margin-right: auto;
				text-align: center;
				font-size: 20px;
				margin-top: 50px;
				border: solid 2px;
				width: 800px;
				height: 300px;
				background-color: scrollbar;
			}
			e{
				color: red;
			}
			.li_biao{
				width: 500px;
				margin-left:  250px;
				text-align: left;
			}
		</style>
</head>
<body>
		<div id="login_table">
		<form action="<c:url value='/UserServlet'/>" method="post" target="_top">
		<input type="hidden" name="method" value="login">
			<h2>登录界面</h2>
			<h style="color:red ">${msg }</h><br/>
				<div class="li_biao">
			用户账号：<input type="text" value="${form.username }"
			name="username"/><e>${error.username }</e><br />
			用户密码：<input type="password" value="${form.password }"
			name="password" /><e>${error.password }</e><br /><br />
				</div>
			<input type="submit" value="登录"  />
		</form>
		</div>
	</body>
</html>