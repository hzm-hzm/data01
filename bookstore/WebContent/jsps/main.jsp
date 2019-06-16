<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的书店（个人项目）</title>
<style type="text/css">
				*{
					font-size:10pt;
				}
				body{
					text-align:center;
					height: 600px;
				}
				.table{
					width:1024px;
					height:100%;
					border:3px solid gray;/*固定边框,1像素*/
				    border-collapse: collapse;/*单线的列表边框*/
				}
				.table td{
					border:1px solid gray;/*固定边框,1像素*/
				}
				iframe {
					width: 100%;
					height: 100%;
					}
				.top
				{
						height: 120px;
						background-image: url(<c:url value='/img/top_image.jpg'/>);
				}
			</style>
</head>
<body>
<table class="table" align="center" >
			<tr class="top">
				<td colspan="2" align="center">
					<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
				</td>
			</tr>
			<tr >
				<td width="180" style="padding-top:5px;"  valign="top" >
					<iframe frameborder="0" width="250px" src="<c:url value='/CategoryServlet?method=findallcategory'/>" name="left"></iframe>
				</td>
				<td style="padding-top: 5px">
					<iframe frameborder="0" src="<c:url value='/jsps/body.jsp'/>" name="body"></iframe>
				</td>
			</tr>
		</table>
</body>
</html>