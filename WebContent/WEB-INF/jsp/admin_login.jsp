<!DOCTYPE HTML>
<html  class="x-admin-sm">
<head>
	<title>EasyMall后台</title>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/login.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/xadmin.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-bg">
    <div class="login layui-anim layui-anim-up">
        <div class="message">EasyMall后台登陆</div>
        <div id="darkbannerwrap"></div>
        
        <form class="layui-form" action="${pageContext.request.contextPath}/admin/login" method="POST">
            <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20">
        </form>
    </div>
    <script>
        $(function  () {
            layui.use('form', function(){
              var form = layui.form;
            });
        })
    </script>
</body>
</html>