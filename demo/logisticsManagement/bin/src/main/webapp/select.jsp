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
<script src="js/jquery-1.9.1.js"></script>
<body>
<form class="layui-form" action="">
	<div class="layui-form-item">    
	    <div class="layui-input-block">      
	      <input style="width:200px; margin-top:20px; margin-left:-85px;" type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输追踪号" class="layui-input">
	    </div>
    </div>
    <div style="width: 200px; margin:0px auto;">
      <!-- layui 2.2.5 新增 -->
      <button class="layui-btn layui-btn-fluid">查询</button>
    </div>
</form>
</body>
</html>