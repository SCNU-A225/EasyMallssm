<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath}/css/admin/common.css" type="text/css" rel="stylesheet">
		<!-- 引入jQuery函数库 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>	
<title>后台主页面</title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

body {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	margin: 0px auto;
	height: auto;
	width: 800px;
	border: 1px solid #006633;
}

#header {
	height: 90px;
	width: 800px;
	background-image: url(${pageContext.request.contextPath}/img/admin/bb.jpg);
	margin: 0px 0px 3px 0px;
}

#header h1 {
	text-align: center;
	font-family: 华文彩云;
	color: #000000;
	font-size: 30px；
}

#navigator {
	height: 25px;
	width: 800px;
	font-size: 14px;
	background-image: url(${pageContext.request.contextPath}/img/admin/bb.jpg);
}
#navigator ul {
	list-style-type: none;
}
#navigator li {
	float: left;
	position: relative;
}
#navigator li a {
	color: #000000;
	text-decoration: none;
	padding-top: 4px;
	display: block;
	width: 98px;
	height: 22px;
	text-align: center;
	background-color: PaleGreen;
	margin-left: 2px;
}
#navigator li a:hover {
	background-color: #006633;
	color: #FFFFFF;
}
#navigator ul li ul {
   visibility: hidden;
   position: absolute;
}

#navigator ul li:hover ul,
#navigator ul a:hover ul{
   visibility: visible;
}

#content {
	height: auto;
	width: 780px;
	padding: 10px;
}

#content iframe {
	height: 300px;
	width: 780px;
}

#footer {
	height: 30px;
	width: 780px;
	line-height: 2em;
	text-align: center;
	background-color: PaleGreen;
	padding: 10px;
}

table{
	text-align: center;
	border-collapse: collapse;
}
.bgcolor{
  	background-color: PaleGreen;
}
</style>
	<script type="text/javascript">
		function changeColor(obj){
			obj.className = "bgcolor";
		}
		function changeColor1(obj){
			obj.className = "";
		}
	</script>
</head>
<body>
	<div id="header">
		<br>
		<br>
		<h1>欢迎${sessionScope.user.username}进入后台管理系统！</h1>
	</div>
	<div id="navigator">
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}/admin/toAdd" target="_blank">添加商品</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/admin/exit">安全退出</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/admin/refresh">刷新列表</a>
			</li>
		</ul>
		
	</div>
	<div id="content">
	<c:if test="${ productList.size() == 0 }">
		您还没有商品。
	</c:if>
	<c:if test="${productList.size() != 0 }">
		<table border="1" bordercolor="PaleGreen">
			<tr>
				<th width="200px">ID</th>
				<th width="120px">名称</th>
				<th width="100px">价格</th>
				<th width="100px">类别</th>
				<th width="100px">库存</th>
				<th width="40px">详情</th>
				<th width="40px">修改</th>
				<th width="40px">删除</th>
			</tr>
			<c:forEach items="${productList }" var="product">
				<tr onmousemove="changeColor(this)" onmouseout="changeColor1(this)">
					<td>${product.id }</td>
					<td>${product.name }</td>
					<td>${product.price }</td>
					<td>${product.category }</td>
					<td>${product.pnum }</td>
					<td><a href="${pageContext.request.contextPath}/admin/detail?id=${product.id }" target="_blank">详情</a></td>
					<td><a href="${pageContext.request.contextPath}/admin/detail?id=${product.id }" target="_blank">修改</a></td>
					<td><a href="${pageContext.request.contextPath}/admin/detail?id=${product.id }" target="_blank">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
	<div id="footer">Copyright ©scnu花开富贵制作委员会</div>
</body>
</html>


