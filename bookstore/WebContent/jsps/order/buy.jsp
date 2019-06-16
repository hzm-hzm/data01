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
				background-color:yellow;
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
		<form action="<c:url value='/OrdersServlet'/>" method="post" target="_top">
		<input type="hidden" name="method" value="orderbuy"/>
		<input type="hidden" name="oid" value="${oid }">
		
			<h2>模拟银行付款界面（随便输入）</h2>
			<h style="color:red ">${msg }</h><br/>
				<div class="li_biao">
			银行卡账号：<input type="text" value="${username }"
			name="username"/><e>${error.username }</e><br />
			银行卡密码：<input type="password" value=""
			name="password" /><e>${error.password }</e><br /><br />
				</div>
			<input type="submit" value="确定"  />
		</form>
		</div>
	</body>
</html>