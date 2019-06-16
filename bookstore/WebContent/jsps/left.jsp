<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>left</title>
<base target="body"/>
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
<style type="text/css">
			.bo{
				background-image: linear-gradient(to right,gray,white);
			}
			.choose{
				text-align: center;
				}
			.choose div{
				margin-top: 5px;
				width: 170px;
				border-radius: 20%;
				
			}
			.choose div:hover{
				background-color:aquamarine;
				border:solid 1px;
			}
			.choose div:link{
				color: black;
			}
			.choose div:visited{
				color: beige;
				background-color:aquamarine;
				border:solid 1px;
			}
			a{
				text-align: center;
				text-decoration: none;
				font-size: 25px;
				width: 200px;
				height: 200px;
			} 
			.shang{
				font-size: 23px;
				text-align: center;
				border: solid 1px;
				}
		</style>
</head>
<body class="bo">
		<div class="shang">请选择分类</div>
	   <div class="choose">
	   <a href="<c:url value='/BookServlet?method=findallbook'/>"> <div style="margin-top: 50px;">全部分类</div></a><br/>
	   		<c:forEach items="${categorylist }" var="categorylist"> 
				<a href="<c:url value='/BookServlet?method=findbookbyc&cid=${categorylist.cid }&cname=${categorylist.cname }'/>"><div>${categorylist.cname }</div></a>
	   		</c:forEach>
	   		
		</div>
	</body>
</html>