<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script src="js/jquery1.9.0.min.js"></script>
<script>
$(function(){
	

//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use(['layer', 'element'], function(){
  var element = layui.element;
  var $ = layui.jquery, layer = layui.layer;
  //…
});
	$('#layui-btn').on('click', function(){
		layer.open({
			  type: 2,
			  area: ['360px', '300px'],
			  skin: 'layui-layer-rim', //加上边框
			  content: 'denglu.jsp'
			});
	});
	$('#select').on('click', function(){
		layer.open({
			  type: 2,
			  area: ['250px', '180px'],
			  skin: 'layui-layer-rim', //加上边框
			  content: 'select.jsp'
			});
	});
	$('#add').on('click', function(){
		layer.open({
			  type: 2,
			  area: ['450px', '550px'],
			  skin: 'layui-layer-rim', //加上边框
			  content: 'add.jsp'
			});
	});
	$('#toushu').on('click', function(){
		layer.open({
			  type: 2,
			  area: ['360px', '300px'],
			  skin: 'layui-layer-rim', //加上边框
			  content: 'toushu.jsp'
			});
	});
});	



</script>
</head>
<body>
<table id="demo" lay-filter="test"></table>
<ul class="layui-nav" lay-filter="" style="padding-left:500px">
  <li class="layui-nav-item">
  	<a href="">托运</a>
  	<dl class="layui-nav-child"> <!-- 二级菜单 -->
      <dd id="add"><a>网上寄件</a></dd>
      <dd><a href="">开设账户</a></dd>
      <dd><a href="">下载价目表</a></dd>
      <dd><a href="">订购包装材料</a></dd>
      <dd><a href="">包装</a></dd>
      <dd><a href="">所有托运服务</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item layui-this">
  	<a href="">货件查询</a>
  	<dl class="layui-nav-child"> <!-- 二级菜单 -->
      <dd id="select"><a>查询</a></dd>
      <dd><a href="">管理递送</a></dd>
      <dd><a href="">利用手机查询货件</a></dd>
      <dd><a href="">所有查询服务</a></dd>     
      </dl>
  </li>
  <li class="layui-nav-item">
    <a href="javascript:;">服务</a>
    <dl class="layui-nav-child"> <!-- 二级菜单 -->
      <dd><a>开设并管理账户</a></dd>
      <dd><a href="">费率和托运时间</a></dd>
      <dd><a href="">订购包装材料</a></dd>
      <dd><a href="">包装和文件</a></dd>
      <dd><a href="">查询货件</a></dd>
      <dd><a href="">服务和解决方案</a></dd>
      <dd id="toushu"><a>投诉</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item"><a href="">支持</a>
  	<dl class="layui-nav-child"> <!-- 二级菜单 -->
      <dd><a href="">查找投递地点</a></dd>
      <dd><a href="">服务指南</a></dd>
      <dd><a href="">标准运输条件</a></dd>
      <dd><a href="">支付和账单</a></dd>
      <dd><a href="">短信通知</a></dd>
      <dd><a href="">联系我们</a></dd>
    </dl>   
  </li>
  <p class="layui-btn" id="layui-btn" style="margin-left:550px;">登陆</p>
  
</ul>
<div style="background-image:url(img/timg.jpg); width:100%; height:300px">
<div class="layui-form-item" style="width:600px; float:left; margin-left:320px; margin-top:100px">
    <div class="layui-input-block">
      <input style=" height:50px;" type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入单号" class="layui-input">
    </div>
  </div>
<button class="layui-btn layui-btn-lg" style="height:50px; width:130px; float:left; margin-left:920px; margin-top:-65px"><i class="layui-icon">&#xe615;查询货件</i></button> 
	<div id="ul1" style="">
       <ul id="xul1">
           <li>多个追踪号码</li>
           <li>服务货件查询</li>
           <li>需要帮忙?</li>
       </ul>               
	</div>
</div>   
<fieldset class="layui-elem-field layui-field-title" style="float:left; padding-left:320px; margin-top:100px;  padding-right:835px">
  <legend  style="font-size: 28px; ">新开账户优惠大放送！</legend>
  <legend  style="font-size: 20px; ">&nbsp;</legend>
  <legend  style="font-size: 12px; ">马上开户，即可享受寄件优惠！运费折扣最低可至五折！另有机会获赠超多好礼！</legend>
  <legend  style="font-size: 12px; ">&nbsp;</legend>
  <legend  style="font-size: 12px; "><a herf="">开设账号</a></legend>
</fieldset> 
         		 
</body>
</html>