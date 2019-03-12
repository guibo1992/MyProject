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
<form class="layui-form" action="">
  <div class="layui-form-item"> 
  <label class="layui-form-label">用户名:</label>  
    <div class="layui-input-block">
      
      <input style="width:200px; margin-top:30px;" type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入账户" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
  <label class="layui-form-label">密码框:</label>   
    <div class="layui-input-inline">
      
      <input style="width:200px; margin-top:20px;" type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
  	</div>
  </div>
  <div style="width: 216px; margin:0px auto;">
      <!-- layui 2.2.5 新增 -->
      <button class="layui-btn layui-btn-fluid">登陆</button>
    </div>
   <label class="layui-form-label">注册</label>
   <label class="layui-form-label" style="margin-left:90px;">忘记密码</label>  
  </form>
</body>
</html>