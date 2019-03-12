<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script src="js/jquery1.9.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		
		$("#add1").on('click', function(){
			
			var username=$("#username").val();			
			var password=$("#password").val();
			var tel=$("#tel").val();
			var six=$("input[name='six']:checked").val();
			
			$.post("adduserqt",{"username":username,"password":password,"tel":tel,"six":six},function(data){
				if(data>0){
						alert("添加成功");
					window.parent.location.reload();
	        	 	var index = parent.layer.getFrameIndex(window.name);
	        	 	parent.layer.close(index);
					}
			},"json")
			
		});
	});
</script>
<body>
<form class="layui-form layui-form-pane" >
<div style="margin-left: 65px;margin-top: 30px">
  <div class="layui-form-item" >
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-inline">
      <input id="username" style="width: 215px" type="text" name="username" lay-verify="send" placeholder="请输入"  class="layui-input" />
    </div>    
  </div>
  <div class="layui-form-item">
  <label class="layui-form-label">密码框:</label>   
    <div class="layui-input-inline">     
      <input id="password" style="width:215px;" type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
  	</div>
  </div>
  <div class="layui-form-item">
  	<label class="layui-form-label">电话</label>
    <div class="layui-input-inline">
      <input id="tel" style="width: 215px" type="text" name="tel" lay-verify="tel" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item" pane=""style="width: 322px">
    <label class="layui-form-label">单选框</label>
    <div class="layui-input-block"style="width: 215px">
      <input id="sex" type="radio" name="six" value="男" title="男" checked="">
      <input id="sex" type="radio" name="six" value="女" title="女">
    </div>
  </div> 
</div>
   <div class="layui-form-item">
    <button id="add1" class="layui-btn"  lay-filter="demo2" style="margin-left: 180px ">提交</button>
  </div>
</form>
          
<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  
  //创建一个编辑器
  var editIndex = layedit.build('LAY_demo_editor');
 
  //自定义验证规则
  form.verify({
	  content: function(value){
      layedit.sync(editIndex);
    }
    ,send:[/^[\u4E00-\u9FA5A-Za-z]+$/,'只能输入中文和英文']
    //,tel:[/^1[3|4|5|8][0-9]\d{4,8}$/,'不是完整的11位手机号或者正确的手机号前七位']
  });
  
  
  //监听提交  
});

 
</script>
</body>
</html>