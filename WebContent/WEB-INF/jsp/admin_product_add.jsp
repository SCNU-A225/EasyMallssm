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
                    <label for="name" class="layui-form-label"><span class="x-red">*</span>商品名</label>
                    <div class="layui-input-block"><input type="text" id="name" name="name" required="" lay-verify="required" class="layui-input"></div>
                </div>
                <div class="layui-form-item">
                    <label for="price" class="layui-form-label"><span class="x-red">*</span>价格</label>
                    <div class="layui-input-block"><input type="text" id="price" name="price" required="" lay-verify="number" class="layui-input"></div>
                </div>
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label"><span class="x-red">*</span>分类</label>
                    <div class="layui-input-block">
				      <select name="category">
                           <option value="其他"></option>
                           <option value="电子数码">电子数码</option>
                           <option value="日用百货">日用百货</option>
                           <option value="服装服饰">服装服饰</option>
                           <option value="家用电器">家用电器</option>
                           <option value="图书杂志">图书杂志</option>
                           <option value="床上用品">床上用品</option>
                       </select>
				    </div>
                </div>
                <div class="layui-form-item">
                    <label for="pnum" class="layui-form-label"><span class="x-red">*</span>数量</label>
                    <div class="layui-input-block"><input type="number" id="pnum" name="pnum" min="0" required="" lay-verify="number" class="layui-input"></div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label for="description" class="layui-form-label"><span class="x-red">*</span>描述</label>
                    <div class="layui-input-block">
                        <textarea name="description" placeholder="请输入内容" required="" lay-verify="required" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="pic" class="layui-form-label"><span class="x-red">*</span>封面</label>
                    <img class="layui-upload-img" id="imgPreview" width="100px" height="100px">
                	<div class="layui-upload-drag" id="pic">
                        <i class="layui-icon"></i>
                        <p>点击上传，或将文件拖拽到此处</p>
                    </div>
                    <input type="text" name="imgurl" id="imgurl" class="layui-hide" />
				</div> 
                </div>
                	
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="send">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>
        layui.use(['form', 'layer', 'upload'],
            function () {
                $ = layui.jquery;
                var form = layui.form,
                    layer = layui.layer,
                    upload = layui.upload;

                //自定义验证规则
                form.verify({
                    nikename: function (value) {
                        if (value.length < 5) {
                            return '昵称至少得5个字符啊';
                        }
                    },
                    pass: [/(.+){6,12}$/, '密码必须6到12位'],
                    repass: function (value) {
                        if ($('#L_pass').val() != $('#L_repass').val()) {
                            return '两次密码不一致';
                        }
                    }
                });

                //监听提交
                form.on('submit(send)',
                    function (data) {
                        $.ajax({
                            type: "POST",
                            url: "${pageContext.request.contextPath}/admin/addproduct" ,
                            data: $('#productForm').serialize(),
                            success: function (res) {
                                res = JSON.parse(res)
                                console.log(res);//打印服务端返回的数据(调试用)
                                if (res.code == 200) {
                                    layer.alert("增加成功", {icon: 1},
                                        function () {
                                            // 获得frame索引
                                            var index = parent.layer.getFrameIndex(window.name);
                                            //关闭当前frame
                                            parent.layer.close(index);
                                    });
                                }
                            },
                            error : function() {
                                layer.alert("增加失败", {icon: 2});
                            }
                        });
                    return false;
                });
                
                upload.render({
                    elem: '#pic'
                    ,url: '${pageContext.request.contextPath}/admin/uploadimg'
                  	,before: function(obj){
               	      //预读本地文件示例，不支持ie8
               	      obj.preview(function(index, file, result){
               	        $('#imgPreview').attr('src', result); //图片链接（base64）
               	      });
               	    }
                    ,done: function(res){
                    	if(res.code==200){
                    		layer.msg('图片上传成功！');
                    		$("#imgurl").val(res.url);
                    	} else {
                    		layer.msg('图片上传失败，请重新上传！');
                    	}
                    }
                });
            });
    </script>
</body>

</html>