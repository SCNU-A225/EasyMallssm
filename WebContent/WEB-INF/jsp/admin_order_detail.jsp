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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
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
                    <label for="id" class="layui-form-label">订单编号</label>
                    <div class="layui-input-block"><input type="text" id="id" name="id" readonly class="layui-input" value=""></div>
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
                    <label for="receiverinfo" class="layui-form-label">收货地址</label>
                    <div class="layui-input-block"><input type="text" id="receiverinfo" name="receiverinfo" readonly class="layui-input" value=""></div>
                </div>
                <div class="layui-form-item">
                    <label for="state" class="layui-form-label">订单状态</label>
                    <div class="layui-input-block"><input type="text" id="state" name="state" readonly class="layui-input" value=""></div>
                </div>
                <div class="layui-form-item">
                    <label for="ordertime" class="layui-form-label">下单时间</label>
                    <div class="layui-input-block"><input type="text" id="ordertime" name="ordertime" readonly class="layui-input" value=""></div>
                </div>
                <table class="layui-table" lay-filter="items">
                	<thead>
                		<tr>
                			<th>商品图片</th>
                			<th>商品名称</th>
                			<th>商品单价</th>
                			<th>购买数量</th>
                			<th>总价</th>
                		</tr>
                	</thead>
                	<tbody id="items">
                	
                	</tbody>
                </table>
            </form>
        </div>
    </div>
    <script>
        layui.use(['form', 'table'],
            function () {
                var form = layui.form;
                var table = layui.table;

                var data = parent.detailInfo;
                console.log(data);
               	$("#id").val(data.id);
               	$("#username").val(data.username);
               	$("#money").val(data.money);
               	$("#receiverinfo").val(data.receiverinfo);
               	$("#state").val(data.state);
               	$("#ordertime").val(data.ordertime);
               	
               	var html = '';
               	for(each of data.orderitems){
               		html += `<tr>
                                <td><img src="${pageContext.request.contextPath }/${"${each.product.imgurl}"}" width="90" height="105"></td>
                                <td>${"${each.product.name}"}</td>
                                <td>${"${each.product.price}"}元</td>
                                <td>${"${each.buynum}"}件</td>
                                <td>${"${each.product.price * each.buynum}"}元</td>
                            </tr>`;
               	}
                $('#items').html(html)
                
                
            });
    </script>
</body>

</html>