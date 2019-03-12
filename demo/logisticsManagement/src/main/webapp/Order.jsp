
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
<script type="text/javascript">
$(function(){
	
	alert(s2)
})
	
</script>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
  <legend>订单状态</legend>
</fieldset>  
 
<table class="layui-table" lay-even="" lay-skin="row">
  <colgroup>
    <col width="150">
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>订单号</th>
      <th>${userOrder.id}</th>
    </tr> 
  </thead>
  <tbody>
    <tr>
      <td>货物名称</td>
      <td>${userOrder.orderName }</td>     
    </tr>
    <tr>
      <td>费用</td>
      <td>${invoice.money }</td>
    </tr>
    <tr>
      <td>发货时间</td>
      <td>${invoice.sendTime }</td>
    </tr>
    <tr>
      <td>货物状态</td>
      <c:if test="${userOrder.orderStatic==2 }">
      <td><span class="layui-badge">未通过</span></td> 
      </c:if>
       <c:if test="${userOrder.orderStatic==1 }">
      <td> <span class="layui-badge layui-bg-blue" >待审核</span>
     
     
     </td> 
      </c:if> 
      <c:if test="${userOrder.orderStatic==3 }">
      <td> <span class="layui-badge layui-bg-green">已审核</span></td> 
       <td><img alt="" src="${invoice.zfbWm}">aaa</td>
      </c:if>  
    </tr>
      
  </tbody>
</table>  
</body>
</html>