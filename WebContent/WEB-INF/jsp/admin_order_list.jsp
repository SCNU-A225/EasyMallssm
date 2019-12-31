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
                                    <input class="layui-input" placeholder="开始日" name="start" id="start" autocomplete="off"></div>
                                <div class="layui-input-inline layui-show-xs-block">
                                    <input class="layui-input" placeholder="截止日" name="end" id="end" autocomplete="off"></div>
                                <div class="layui-input-inline layui-show-xs-block">
                                    <select name="contrller">
                                        <option>支付方式</option>
                                        <option>支付宝</option>
                                        <option>微信</option>
                                        <option>货到付款</option></select>
                                </div>
                                <div class="layui-input-inline layui-show-xs-block">
                                    <select name="contrller">
                                        <option value="">订单状态</option>
                                        <option value="0">未支付</option>
                                        <option value="1">已支付，待发货</option>
                                        <option value="2">已发货</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline layui-show-xs-block">
                                    <input type="text" name="username" placeholder="请输入订单ID" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-input-inline layui-show-xs-block">
                                    <button class="layui-btn" lay-submit="" lay-filter="sreach">
                                        <i class="layui-icon">&#xe615;</i></button>
                                </div>
                                <button class="layui-btn" onclick="location.reload()" type="button" style="float: right;">
                                		<i class="layui-icon layui-icon-refresh"></i>刷新</button>
                            </form>
                        </div>
                        <div class="layui-card-body ">
                            <table id="orderTable" class="layui-table layui-form" lay-filter="orderTable"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

    <script id="optionsBar">
        <button type="button" class="layui-btn" lay-event="detail">详情</button>
        <button type="button" class="layui-btn layui-btn-warm" lay-event="deliver">发货</button>
    </script>

    <script>

    /*
        private String id;
        private double money;
        private String receiverinfo;
        private int paystate;
        private Timestamp ordertime;
        private int user_id;
    */
    layui.use(['laydate','form','table'],function(){
        var form = layui.form;
        var table = layui.table;
        var laydate = layui.laydate;
        
      	//执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
        
        var infoList = JSON.parse(`${infolist}`);
        var stateMap = ['未支付','已支付，待发货','已发货'];
        var tableData = [];
        for(each of infoList){
            tableData.push({
                id: each.order.id,
                username: each.username,
                receiverinfo: each.order.receiverinfo,
                state: stateMap[each.order.paystate],
                ordertime: new Date(each.order.ordertime).Format("yyyy-MM-dd  hh:mm:ss"),
                orderitems: each.list
            })
        }
        console.log(tableData)
        

        table.render({
                elem: '#orderTable'
               	,cellMinWidth: 20
                ,cols: [[
                    {field:'id', title: '订单ID'}
                    ,{field:'username', title: '用户名'}
                    ,{field:'receiverinfo', title: '收货地址'}
                    ,{field:'state', title: '状态'}
                    ,{field:'ordertime', title: '下单时间'}
                    ,{title: '操作', toolbar: '#optionsBar'}
                ]]
                ,data: tableData
                ,page: true
            });
        
	    table.on('tool(orderTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	        var data = obj.data; //获得当前行数据
	        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	        if(layEvent == 'deliver'){ //删除
	        	deliver_order(data.id,data.state);
	        }
	    });
        
	    function deliver_order(id,state) {
	    	if(state==stateMap[0]){
	    		layer.confirm('该订单未支付！无法发货');
	    	}
	    	else if(state==stateMap[2]){
	    		layer.confirm('该订单已发货！无需发货');
	    	}
	    	else{
	            layer.confirm('确认要发货吗？',
	            function(index) {
	            	$.ajax({
	                    method:'GET',
	                    url:'${pageContext.request.contextPath}/admin/updatePayState',
	                    data:"id="+id,
	                    success:function(res){
	                    	res = JSON.parse(res)
	                    	if(res.code==200){
	                        	layer.msg('已发货!', {
	                                icon: 1,
	                                time: 1000
	                            });
	                        	location.reload();
	                    	} else {
	                    		layer.alert("发货失败", {icon: 2});
	                    	}
	                    },
	                    error:function(xhr,err){
	                    	layer.alert("发货失败", {icon: 2});
	                    }
	                })
	            });
	    	}
        }
        
	    });
    
	    
    </script>

</html>