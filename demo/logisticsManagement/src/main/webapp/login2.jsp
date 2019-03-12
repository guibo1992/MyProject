<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script src="js/jquery1.9.0.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form class="layui-form layui-form-pane" action="">
	<div class="layui-form-item"> 
  <label class="layui-form-label">用户名:</label>  
    <div class="layui-input-block">
      
      <input style="width:200px; margin-top:30px;" type="text" name="userName" value="${AdminInfo.wlName }" lay-verify="title" disabled="disabled"  class="layui-input">
    
    </div>
  </div>
  <div style="width: 216px; margin:0px auto;">
      <!-- layui 2.2.5 新增 -->
      <button class="layui-btn layui-btn-fluid">编辑资料</button>
    </div>

</form>
</body>
</html>