<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<script>
//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function(){
  var element = layui.element;
  
  //…
  
 
  
});

</script>
<body style="margin: 0;padding: 0;border: 0;">
<ul class="layui-nav" style="padding-top: 13px;padding-bottom: 13px">
  <li class="layui-nav-item">
    <a><span style=" font-size: 28px">晶科物流管理系统</span></a>
  </li>
  <li class="layui-nav-item" style="margin-left: 850px">
    <a href="delAdmin?wlId=${AdminInfo.wlId }" id="rm">注销</a>
  </li>
  <li class="layui-nav-item">
    <a href=""><img src="//t.cn/RCzsdCq" class="layui-nav-img">${AdminInfo.wlName }</a>
    <dl class="layui-nav-child">
      <dd><a href="javascript:;">修改信息</a></dd>
      <dd><a href="javascript:;">安全管理</a></dd>
      <dd><a href="javascript:;">退出</a></dd>
    </dl>
  </li>
</ul>
</body>
</html>