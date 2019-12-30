<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="x-admin-sm">
    
    <head>
        <meta charset="UTF-8">
        <title>EasyMall后台</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/xadmin.css">
        <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
    </head>
    
    <body>

        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <form class="layui-form layui-col-space5">
                                <div class="layui-input-inline layui-show-xs-block">
                                    <input class="layui-input" placeholder="最低价" name="minPrice" id="minPrice" lay-verify="number"></div>
                                <div class="layui-input-inline">-</div>
                                <div class="layui-input-inline layui-show-xs-block">
                                    <input class="layui-input" placeholder="最高价" name="maxPrice" id="maxPrice" lay-verify="number"></div>
                                <div class="layui-input-inline layui-show-xs-block">
                                    <select name="category">
                                        <option value="">分类</option>
                                        <option value="电子数码">电子数码</option>
                                        <option value="日用百货">日用百货</option>
                                        <option value="服装服饰">服装服饰</option>
                                        <option value="家用电器">家用电器</option>
                                        <option value="图书杂志">图书杂志</option>
                                        <option value="床上用品">床上用品</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline layui-show-xs-block">
                                    <input type="text" name="productName" placeholder="请输入商品名" autocomplete="off" class="layui-input"></div>
                                <div class="layui-input-inline layui-show-xs-block">
                                    <button class="layui-btn" lay-submit="" lay-filter="sreach">
                                        <i class="layui-icon">&#xe615;</i></button>
                                </div>
                            </form>
                        </div>
                        <div class="layui-card-header">
                            <button class="layui-btn layui-btn-danger" onclick="delAll()">
                                <i class="layui-icon"></i>批量删除</button>
                            <button class="layui-btn" onclick="xadmin.open('添加商品','${pageContext.request.contextPath}/admin/toprodadd',500,600)">
                                <i class="layui-icon"></i>添加</button></div>
                        <div class="layui-card-body ">
                            <table id="productTable" class="layui-table layui-form" lay-filter="productTable"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    
    <script id="optionsBar">
        <button type="button" class="layui-btn layui-btn-danger" lay-event="delete">删除</button>
    </script>

    <script>
    layui.use(['form', 'table'],
        function() {
            var table = layui.table;
            var form = layui.form;

            form.on('checkbox(checkall)', function(data){

              if(data.elem.checked){
                $('tbody input').prop('checked',true);
              }else{
                $('tbody input').prop('checked',false);
              }
              form.render('checkbox');
            }); 
            
            table.render({
                elem: '#productTable'
               	,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [[
                	{type:'checkbox'}
                    ,{field:'id', title: '商品ID'}
                    ,{field:'name', title: '商品名', edit:"text"} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'price', title: '价格', edit:"text", sort: true}
                    ,{field:'category', title: '商品分类', sort: true}
                    ,{field:'pnum', title: '数量', edit:"text", sort: true}
                    ,{field:'description', title: '描述', edit:"text"}
                    ,{title: '操作', toolbar: '#optionsBar'}
                ]]
                ,data: JSON.parse(`${prodlist}`)
                ,page: true
            });

            table.on('tool(productTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                if(layEvent == 'delete'){ //删除
                	product_del(obj,data.id);
                }
            });

            table.on('edit(productTable)', function(obj){
                var data = obj.data;
                $.ajax({
                    method:'POST',
                    url:`${pageContext.request.contextPath}/admin/updateprod`,
                    data:data,
                    success:function(data,msg,xhr){
                        
                    },
                    error:function(xhr,err){
                    	alert("修改失败")
                        console.log(xhr)
                        console.log(err)
                    }
                })
            });

        });

        
        function product_del(obj, id) {
            layer.confirm('确认要删除吗？',
            function(index) {
                alert(id);
            	$.ajax({
                    method:'POST',
                    url:'${pageContext.request.contextPath}/admin/delproduct',
                    data:"id="+id,
                    success:function(res){
                    	res = JSON.parse(res)
                    	console.log(res);
                    	if(res.code==200){
                    		obj.del();
                        	layer.msg('已删除!', {
                                icon: 1,
                                time: 1000
                            });
                    	} else {
                    		layer.alert("删除失败", {icon: 2});
                    	}
                    },
                    error:function(xhr,err){
                    	layer.alert("删除失败", {icon: 2});
                    }
                })
            });
        }

        function delAll (argument) {
            var ids = [];

            // 获取选中的id 
            $('tbody input').each(function(index, el) {
                if($(this).prop('checked')){
                   ids.push($(this).val())
                }
            });
      
            layer.confirm('确认要删除吗？'+ids.toString(),function(index){
                //捉到所有被选中的，发异步进行删除
                layer.msg('删除成功', {icon: 1});
                $(".layui-form-checked").not('.header').parents('tr').remove();
            });
          }

        
        </script>
</html>