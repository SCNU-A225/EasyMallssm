<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>EasyMall后台</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
        content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/xadmin.css">
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
            <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
            <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>
    <div class="layui-fluid">
        <div class="layui-row">
            <form class="layui-form layui-form-pane" id="productForm">
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label">订单编号</label>
                    <div class="layui-input-block"><input type="text" id="name" name="name" readonly class="layui-input" value="cessd发的说法三"></div>
                </div>
                <div class="layui-form-item">
                    <label for="username" class="layui-form-label">所属用户</label>
                    <div class="layui-input-block"><input type="text" id="username" name="username" readonly class="layui-input" value=""></div>
                </div>
                <div class="layui-form-item">
                    <label for="money" class="layui-form-label">订单金额</label>
                    <div class="layui-input-block"><input type="text" id="money" name="money" readonly class="layui-input" value=""></div>
                </div>
                <div class="layui-form-item">
                    <label for="address" class="layui-form-label">收货地址</label>
                    <div class="layui-input-block"><input type="text" id="address" name="address" readonly class="layui-input" value=""></div>
                </div>
                <div class="layui-form-item">
                    <label for="state" class="layui-form-label">订单状态</label>
                    <div class="layui-input-block"><input type="text" id="state" name="state" readonly class="layui-input" value=""></div>
                </div>
                <table width="1200" border="0" cellpadding="0"
				cellspacing="1" style="background:#d8d8d8;color:#333333">
				<tr>
					<th width="276" height="30" align="center" valign="middle" bgcolor="#f3f3f3">商品图片</th>
					<th width="247" align="center" valign="middle" bgcolor="#f3f3f3">商品名称</th>
					<th width="231" align="center" valign="middle" bgcolor="#f3f3f3">商品单价</th>
					<th width="214" align="center" valign="middle" bgcolor="#f3f3f3">购买数量</th>
					<th width="232" align="center" valign="middle" bgcolor="#f3f3f3">总价</th>
				</tr>
				<c:forEach items="${ orderInfo.list }" var="entry">
					<tr>
						<td align="center" valign="middle" bgcolor="#FFFFFF">
							<img src="${pageContext.request.contextPath }/${entry.product.imgurl}" width="90" height="105">
						</td>
						<td align="center" valign="middle" bgcolor="#FFFFFF">${ entry.product.name }</td>
						<td align="center" valign="middle" bgcolor="#FFFFFF">${ entry.product.price }元</td>
						<td align="center" valign="middle" bgcolor="#FFFFFF">${ entry.buynum }件</td>
						<td align="center" valign="middle" bgcolor="#FFFFFF">${ entry.product.price * entry.buynum }元</td>
					</tr>
				</c:forEach>
			</table>
            </form>
        </div>
    </div>
    <script>
        layui.use(['form', 'layer'],
            function () {
                $ = layui.jquery;
                var form = layui.form,

               
            });
    </script>
</body>

</html>