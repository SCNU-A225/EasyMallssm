<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加商品</title>
<link href="${pageContext.request.contextPath}/css/admin/common.css" type="text/css" rel="stylesheet">
</head>    
<body>
	<form action="${pageContext.request.contextPath}/admin/addproduct" method="post" enctype="multipart/form-data">
		<table border=1 style="border-collapse: collapse">
			<caption>
				<font size=4 face=华文新魏>添加商品</font>
			</caption>
			<tr>
				<td>名称</td>
				<td>
					<input name="name" type="text"/>
				</td>
			</tr>
			<tr>
				<td>价格</td>
				<td>
					<input name="price" type="number"/>
				</td>
			</tr>
			<tr>
				<td>库存</td>
				<td>
					<input name="pnum" type="number"/>
				</td>
			</tr>
			<tr>
				<td>描述</td>
				<td>
					<input name="description" type="text"/>
				</td>
			</tr>
			<tr>
				<td>图片</td>
				<td>
					<input type="file" name="image"/>
				</td>
			</tr>
			<tr>
				<td>类型</td>
				<td>
					<select name="category">
						<c:forEach items="${ productType }" var="entry">
							<option value="${entry }"> ${entry }</option>
						</c:forEach>
   					</select>
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="submit" value="提交"/>
				</td>
				<td align="left">
					<input type="reset" value="重置"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
