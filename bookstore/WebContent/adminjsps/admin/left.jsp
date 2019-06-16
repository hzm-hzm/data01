<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>left</title>
		<base target="body" />
	</head>
	<style>
		body{
			background-color: burlywood;
		}
		.biao{
			width: 220px;
			height: 300px;
			text-align: center;
			margin-top: 50px;
			font-size: 25px;
		}
		.biao ul{
			margin-top: -20px;
		}
		.biao ul div{
			width: 210px;
			height: 35px;
			border: solid 1px;
			margin-left: -40px;
			background-color: darkgray;
		
		}
		.biao ul div:hover{
			cursor: pointer;
		}
		.biao ul li{
			list-style-type: none;
			width: 210px;
			height: 35px;
			border: solid 1px;
			margin-left:-40px;
			margin-top: 3px;
			border-radius: 15%;
			background-color: bisque;
			display: none;
		}
		.biao ul li:hover{
			background-color: darkorange;
			cursor: pointer;
		}
		a{
			text-decoration: none;
			color: chocolate;
		}
		a:hover{
			color: white;
		}
	</style>
	
	<body>
		
		<div id="left_main">
			<div class="biao">
			<ul id="fl_ul">
				<div id="fl">分类模块管理</div>
				<a href="<c:url value='/Admin/AdminCategoryServlet?method=findallcategory'/>"><li>查看分类</li></a>
				<a href="<c:url value='/adminjsps/admin/category/desc.jsp'/>"><li>添加分类</li></a>
			</ul>
			<ul id="ts_ul">
				<div id="ts">图书模块管理</div>
				<a href="<c:url value='/Admin/AdminBookServlet?method=findallbook'/>"><li>查看图书</li></a>
				<a href="<c:url value='/Admin/AdminBookServlet?method=to_addbook'/>"><li>添加图书</li></a>
				<a href="<c:url value='/Admin/AdminBookServlet?method=down_findallbook'/>"><li>下架图书</li></a>
			</ul>
			<ul id="dd_ul">
				<div id="dd">订单模块管理</div>
				<a href="<c:url value='/Admin/AdminOrdersServlet?method=findallorders'/>"><li>查看订单</li></a>
				<a href="<c:url value='/Admin/AdminOrdersServlet?method=findordersbystate&state=1'/>"><li>无付款订单</li></a>
				<a href="<c:url value='/Admin/AdminOrdersServlet?method=findordersbystate&state=2'/>"><li>等发货订单</li></a>
				<a href="<c:url value='/Admin/AdminOrdersServlet?method=findordersbystate&state=3'/>"><li>已发货订单</li></a>
				<a href="<c:url value='/Admin/AdminOrdersServlet?method=findordersbystate&state=4'/>"><li>已结束订单</li></a>
			</ul>
			</div>
		</div>
		
		
		
		<script type="text/javascript">
			
				var fl=document.getElementById("fl");
				var fl_li=document.getElementById("fl_ul").getElementsByTagName("li");
				var ts=document.getElementById("ts");
				var ts_li=document.getElementById("ts_ul").getElementsByTagName("li");
				var dd=document.getElementById("dd");
				var dd_li=document.getElementById("dd_ul").getElementsByTagName("li")
				window.onload=function init(){
					fl.onclick=function fl(){
						for(var i=0;i<fl_li.length;i++){
							if(fl_li[i].style.display=="none"){
								fl_li[i].style.display="block"
							}else{
								fl_li[i].style.display="none"
							}
						}
					}
						ts.onclick=function ts(){
							for(var i=0;i<ts_li.length;i++){
								if(ts_li[i].style.display=="none"){
									ts_li[i].style.display="block"
								}else{
									ts_li[i].style.display="none"
								}
							}
					}
					dd.onclick=function dd(){
							for(var i=0;i<dd_li.length;i++){
								if(dd_li[i].style.display=="none"){
									dd_li[i].style.display="block"
								}else{
									dd_li[i].style.display="none"
								}
							}
					}
			}
		</script>
	</body>
</html>