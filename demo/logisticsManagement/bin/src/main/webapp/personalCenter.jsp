<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

	<table class="layui-hide" id="test" lay-filter="test"></table>

	<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
  </div>
</script>

	<script type="text/html" id="bar1">
  <a class="layui-btn layui-btn-xs" lay-event="edit">授权</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">清除</a>
</script>


	<script src="layui/layui.js" charset="utf-8"></script>
	<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

	<script>
	
			layui.use('table', function() {
				var table = layui.table;

				table.render({
					elem : '#test',
					url : 'selectAllEmp',
					toolbar : '#toolbarDemo',
					title : '用户数据表',
					limit : 10,
					cols : [ [ {
						type : 'checkbox',
						fixed : 'left'
					}, {
						field : 'wlId',
						title : '工号',
						sort : true
					}, {
						field : 'wlName',
						title : '用户名',
						sort : true
					}, {
						field : 'truename',
						title : '姓名',
						sort : true
					}, {
						field : 'name',
						title : '职位',
						sort : true
					}, {
						fixed : 'right',
						width : '10%',
						title : '操作',
						toolbar : "#bar1"
					} ] ],
					page : true,
					parseData : function(res) {
						return {
							"code" : 0,
							"msg" : "",
							"count" : res.total,
							"data" : res.rows
						};
					}
				});

				//头工具栏事件
				table.on('toolbar(test)', function(obj) {
					var checkStatus = table.checkStatus(obj.config.id);
					switch (obj.event) {
					case 'getCheckData':
						var data = checkStatus.data;
						layer.alert(JSON.stringify(data));
						break;
					case 'getCheckLength':
						var data = checkStatus.data;
						layer.msg('选中了：' + data.length + ' 个');
						break;
					case 'isAll':
						layer.msg(checkStatus.isAll ? '全选' : '未全选');
						break;
					}
					;
				});

				//监听行工具事件
				table.on('tool(test)', function(obj) {
					var data = obj.data;
					//console.log(obj)
					if (obj.event === 'del') {
						layer.confirm('真的删除行么', function(index) {
							$.post("delAdmin", {"wlId" : data.wlId}, function(result) {
								if (result == 1) {
									obj.del();
									location.reload();
								}else{
								}
							}, "json");
							layer.close(index);
						});
					} else if (obj.event === 'edit') {
						alert("oooooooooooooo");
						layer.open({
							type : 2,
							area : [ '500px', '400px' ],
							skin : 'layui-layer-rim', //加上边框
							content : 'selectUser?wlId=' + data.wlId
						});
					}
				});
			});
		
	</script>

</body>
</html>