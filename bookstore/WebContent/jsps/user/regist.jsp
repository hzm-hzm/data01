<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册界面</title>
	<base target="body">
		<style type="text/css">
			body{
				background-color: graytext;
			}
				#regist_table{
					margin-left: auto;
					margin-right: auto;
					text-align:center;
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
					width: 600px;
					margin-left: 230px;
					text-align: left;
				}
				
		</style>
</head>
<body>
		<div id="regist_table">
		<form action="<c:url value='/UserServlet'/>" method="post">
		<input type="hidden" name="method" value="regist"/>
			<h2>注册界面</h2>
			<h style="color: red">${msg }</h><br/>
			<div class="li_biao">
			用户账号：<input type="text" name="username" 
				value="${form.username }"/> <e>${error.username }</e><br />
			用户密码：<input type="password" name="password"
				value="${form.password }" /> <e>${error.password }</e><br />
			用户邮箱：<input type="text" name="email" 
				value="${form.email }"/>   <e>${error.email }</e><br /><br />
			</div>
			<input type="submit" value="注册"  />
<%-- 			<a href="<c:url value='/jsps/main.jsp'/>">返回首页</a> --%>
		</form>
		</div>
	</body>
</html>