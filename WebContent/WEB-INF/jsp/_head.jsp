<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

<div id="common_head">
	<div id="line1">
<div id="content">


<c:if test="${sessionScope.user!=null }">
		欢迎&nbsp;${sessionScope.user.username}&nbsp;回来！&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/index/exit">退出</a>
	</c:if>	
	<c:if test="${sessionScope.user==null }">
<a href="${pageContext.request.contextPath }/index/login">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
<a href="${pageContext.request.contextPath }/index/register">注册</a>

	</c:if>	


</div>

	</div>
	<div id="line2">
		<img id="logo" src="${pageContext.request.contextPath}/img/head/logo.jpg"/>
		<input type="text" name=""/>
		<input type="button" value="搜 索"/>
		<span id="goto">
			<a id="goto_order" href="${pageContext.request.contextPath }/order/toOrder">我的订单</a>
			<a id="goto_cart" href="${pageContext.request.contextPath }/cart/getCart">我的购物车</a>
		</span>
		<img id="erwm" src="${pageContext.request.contextPath}/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="${ pageContext.request.contextPath }/index/toIndex">首页</a></li>
				<li><a href="${ pageContext.request.contextPath }/product/getAll">全部商品</a></li>
				<li><a href="${ pageContext.request.contextPath }/product/getCategory?c=0">手机数码</a></li>
				<li><a href="${ pageContext.request.contextPath }/product/getCategory?c=1">电脑平板</a></li>
				<li><a href="${ pageContext.request.contextPath }/product/getCategory?c=2">家用电器</a></li>
				<li><a href="${ pageContext.request.contextPath }/product/getCategory?c=3">日用百货</a></li>
				<li><a href="${ pageContext.request.contextPath }/product/getCategory?c=4">食品饮料</a></li>
				<li><a href="${ pageContext.request.contextPath }/product/getCategory?c=5">图书杂志</a></li>
				<li><a href="${ pageContext.request.contextPath }/product/getCategory?c=6">服装服饰</a></li>
				<li><a href="${ pageContext.request.contextPath }/product/getCategory?c=7">床上用品</a></li>
			</ul>
		</div>
	</div>
</div>