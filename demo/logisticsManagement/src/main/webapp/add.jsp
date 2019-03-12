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
<script src="js/jquery1.9.0.min.js"></script>
<body>
<form class="layui-form layui-form-pane" action="adduserorder">
<div style="margin-left: 65px;margin-top: 30px">
  <div class="layui-form-item" >
    <label class="layui-form-label">发货人姓名</label>
    <div class="layui-input-inline">
      <input style="width: 215px" type="text" name="orderSend" lay-verify="send" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>    
  </div>
  <div class="layui-form-item">
  	<label class="layui-form-label">发货人电话</label>
    <div class="layui-input-inline">
      <input style="width: 215px" type="text" name="sendTel" lay-verify="tel" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
  	<label class="layui-form-label">收货人</label>
    <div class="layui-input-inline">
      <input style="width: 215px" type="text" name="orderSendto" lay-verify="send" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
  	<label class="layui-form-label">货物名称</label>
    <div class="layui-input-inline">
      <input style="width: 215px" type="text" name="orderName" lay-verify="name" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
  	<label class="layui-form-label">发货数量</label>
    <div class="layui-input-inline">
      <input style="width: 215px" type="text" name="orderNumber" lay-verify="numb" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <label class="layui-form-label">发货站</label>
  	<div class="layui-input-inline">
  	
      <select name="sendStart">
        <option value="浙江" selected="">浙江省</option>
        <option value="你的工号">江西省</option>
        <option value="福建省">福建省</option>
      </select>
    </div>
    <div>&nbsp;</div>
    <label class="layui-form-label">收货站</label>
  	<div class="layui-input-inline">
  	
      <select name="sendtoEnd">
        <option value="浙江" selected="">浙江省</option>
        <option value="江西省">江西省</option>
        <option value="福建省">福建省</option>
      </select>
    </div>
    <div>&nbsp;</div>
</div>
   <div class="layui-form-item">
    <button class="layui-btn" lay-submit="" lay-filter="demo2" style="margin-left: 180px ">提交</button>
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
    ,tel:[/^1[3|4|5|8][0-9]\d{4,8}$/,'不是完整的11位手机号或者正确的手机号前七位']
  });
  
  
  //监听提交  
});

 
</script>
</body>
</html>