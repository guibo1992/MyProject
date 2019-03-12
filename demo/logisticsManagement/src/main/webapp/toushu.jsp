<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script src="js/jquery1.9.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<form class="layui-form layui-form-pane" action="" id="form1">
<div style="margin-left: 65px;margin-top: 30px">
  <div class="layui-form-item" >
    <label class="layui-form-label">投诉人姓名</label>
    <div class="layui-input-inline">
      <input style="width: 215px" type="text" name="tsName" lay-verify="sendname" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>    
  </div>
  <div class="layui-form-item">
  	<label class="layui-form-label">投诉人电话</label>
    <div class="layui-input-inline">
      <input style="width: 215px" type="text" name="tsTel" lay-verify="tel" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  
  <div class="layui-form-item">
  	<label class="layui-form-label">货单号</label>
    <div class="layui-input-inline">
      <input style="width: 215px" type="text" name="note" lay-verify="numb" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item layui-form-text" style="width: 560px;">
    <label class="layui-form-label">投诉内容</label>
    <div class="layui-input-block" style="height: 200px">
      <textarea class="layui-textarea layui-hide" name="tsCount" lay-verify="content" id="LAY_demo_editor" ></textarea>
    </div>
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">投诉建议</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入内容" class="layui-textarea" name="tsIdear" ></textarea>
    </div>
  </div>   
</div>
   <div class="layui-form-item">
    <button class="layui-btn" lay-submit="" lay-filter="demo2" style="margin-left: 250px " id="tijiao">提交</button>
  </div>
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
	  ,numb: function(value){
	      if(value.length < 5){
	        return '货单号不能为空';
	      }
	    }
    ,sendname:[/^[\u4E00-\u9FA5A-Za-z]+$/,'只能输入中文和英文']
    ,tel:[/^1[3|4|5|8][0-9]\d{4,8}$/,'不是完整的11位手机号或者正确的手机号前七位']
  });
  
  
  //监听提交  
});

$(function(){
	$("#tijiao").on('click',function(){
		var value=$("#form1").serialize();
		$.post("addtousu",value,function(data){
			if(data>0){
				
				window.parent.location.reload();
        	 	var index = parent.layer.getFrameIndex(window.name);
        	 	parent.layer.close(index);
			}
		});
	});
});
</script>
</body>
</html>