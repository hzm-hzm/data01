<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
		<style>
			.choose{
				position: absolute;
				top: 0px;
				right: 3px;
				width: 100px;
				height:35px;
			}
			.choose1{
				position: absolute;
				top: 0px;
				left: 2px;
				font-size: 18px;
				width: 190px;
				height: 110px;
				border: solid 1px;
				background-color: white;
				background-image: linear-gradient(to right,gray,white);
				border-radius: 10%;
			}
			.choose a{
				text-decoration: none;
				font-size: 20px;
			}
			.choose1 a{
				text-decoration: none;
				top: 0px;
			}
			.choose1 div{
				width: 185px;
				/* height: 200px; */
				margin-top: 0px;
				border-radius: 10%;
				text-align: center;
			}
			.choose1 div:hover{
				background-color:aquamarine;
				border:solid 1px;
			}
			.zi{
				position: absolute;
				bottom: 0px;
				font-size: 25px;
				-webkit-animation:aa 8s linear 0s  infinite ;
				color: red;
			}
			     @-webkit-keyframes aa {
            0%{
              left: 0px;
            }
            50%
            {
                left: 750px;
            }
            100%
            {
                left: 0px;
            }
        }
		</style>
</head>
<body>

<h1 align="center" style="margin-top: 20px; font-size: 50px;">我 的 书 店</h1>
		<div class="zi">今天有多本新出书籍!!!</div>
		
		<c:choose>
		<c:when test="${empty session_user }">
			<div class="choose">
			<a href="<c:url value='/jsps/user/login.jsp'/>" style="border-right:solid 2px"target="body">登录</a>
			<a href="<c:url value='/jsps/user/regist.jsp'/>"target="body">注册</a>
		</div>
		</c:when>
		<c:otherwise>
			<div class="choose1">
			&nbsp;&nbsp;你好，${session_user.username }用户
			&nbsp;&nbsp;<a href="<c:url value='/jsps/cart/list.jsp'/>"target="body"><div>我的购物车</div></a>
			<a href="<c:url value='/OrdersServlet?method=showitemsbyuid'/>" target="body" ><div>我的订单</div></a>
			<a href="<c:url value='/UserServlet?method=exit'/>" style="color: red;" target="_parent"><div>退出</div></a>
		</div>
		</c:otherwise>
		</c:choose>
		
		
</body>
</html>