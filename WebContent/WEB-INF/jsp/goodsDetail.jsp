<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
</head>
<body>
	<table border=1 style="border-collapse: collapse">
		<caption>
			<font size=4 face=华文新魏>商品详情</font>
		</caption>
		<tr>
			<td>ID</td>
			<td>
				${prod.id }
			</td>
		</tr>
		<tr>
			<td>名称</td>
			<td>
				${prod.name }
			</td>
		</tr>
		<tr>
			<td>价格</td>
			<td>
				${prod.price }
			</td>
		</tr>
		<tr>
			<td>库存</td>
			<td>
				${prod.pnum }
			</td>
		</tr>
		<tr>
			<td>描述</td>
			<td>
				${prod.description }
			</td>
		</tr>
		<tr>
			<td>图片</td>
			<td>
				<c:if test="${prod.imgurl != '' }">
					<img alt="" width="250" height="250"
					src="${pageContext.request.contextPath}/${prod.imgurl}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>类型</td>
			<td>
				${prod.category }
			</td>
		</tr>
	</table>
</body>
</html>