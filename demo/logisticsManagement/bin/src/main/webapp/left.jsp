<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script src="js/jquery1.9.0.min.js"></script>
<script>
//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function(){
  var element = layui.element;
  
  //…
  
});
</script>
<body style="margin:0px;padding: 0px;">
<ul class="layui-nav layui-nav-tree layui-bg-cyan " lay-filter="test" id="test1" style="width:800px;">
<!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
<li class="layui-nav-item layui-nav-itemed">
    <a href="main.jsp" target="main">首页</a>
    
  </li>
  <li class="layui-nav-item layui-nav-itemed">
    <a href="线路管理.jsp" target="main">业务处理</a>
    <dl class="layui-nav-child" >
      <dd><a href="">订单查询</a></dd>
      <dd><a href="">订单确认</a></dd>
      <dd><a href="">跳转</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item layui-nav-itemed">
    <a href="线路管理.jsp" target="main">业务处理</a>
    <dl class="layui-nav-child" >
      <dd><a href="">订单查询</a></dd>
      <dd><a href="getAllCoun?">统计</a></dd>
      <dd><a href="">跳转</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item layui-nav-itemed">
    <a href="线路管理.jsp" target="main">业务处理</a>
    <dl class="layui-nav-child" >
      <dd><a href="">订单查询</a></dd>
      <dd><a href="">订单确认</a></dd>
      <dd><a href="">跳转</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item layui-nav-itemed">
    <a href="线路管理.jsp" target="main">业务处理</a>
    <dl class="layui-nav-child" >
      <dd><a href="">订单查询</a></dd>
      <dd><a href="">订单确认</a></dd>
      <dd><a href="">跳转</a></dd>
    </dl>
  </li>
  <shiro:hasRole name="admin">
  <li class="layui-nav-item layui-nav-itemed">
    <a href="personalCenter.jsp" target="main">个人中心</a>
  </li>
  </shiro:hasRole>
</ul>
</body>
</html>