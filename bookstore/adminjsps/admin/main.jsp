<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>管理员页面（个人项目）</title>
		<style>
			#table{
				width: 98%;
				height: 520px;
			}
			iframe{
				width: 100%;
				height: 100%;
			}
		</style>
	</head>
	<body>
		<table id="table" align="center">
			<tr>
				<td colspan="2" height="125px">
					<iframe border="0px" src="<c:url value='/adminjsps/admin/top.jsp'/>" name="top"></iframe>
				</td>
			</tr>
			<tr >
				<td width="250px" valign="top"  >
					<iframe border="0px" src="<c:url value='/adminjsps/admin/left.jsp'/>" name="left"></iframe>
				</td>
				<td height="500px">
					<iframe border="0px" src="<c:url value='/adminjsps/admin/body.jsp'/>" name="body"></iframe>
				</td>
			</tr>
		</table>
	</body>
</html>