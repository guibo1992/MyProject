<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/css.css" />
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/sdmenu.js"></script>
<script type="text/javascript" src="js/laydate/laydate.js"></script>
</head>
<body>


     <div class="right"  id="mainFrame">
     
     <div class="right_cont">
 <ul class="breadcrumb">当前位置：
  <a href="#">首页</a> <span class="divider">/</span>
  <a href="#">订单查询</a> <span class="divider">/</span>
  所有未审核通过订单
</ul>
 
   
   <table class="table table-bordered table-striped table-hover">
     <tbody>
       <tr align="center">
         <td nowrap="nowrap"><strong>票号</strong></td>
         <td nowrap="nowrap"><strong>发货站</strong></td>
         <td nowrap="nowrap"><strong>发货人</strong></td>
         <td nowrap="nowrap"><strong>电话</strong></td>
         <td><strong>收获站</strong></td>
         <td><strong>收货人</strong></td>
         <td><strong>收货人电话</strong></td>
         <td><strong>货物名称</strong></td>
         <td><strong>货物数量</strong></td>
         <td><strong>货物重量（公斤㎏）</strong></td>
         <td><strong>货物体积（立方m3）</strong></td>
         <td><strong>发货时间</strong></td>
        
         <td><strong>费用（元）</strong></td>     
         <td width="80" nowrap="nowrap"><strong> 操作 </strong></td>
         </tr>
    <c:forEach items="${list  }" var="l">
       <tr align="center">
         <td nowrap="nowrap">${l.note}</td>
         <td nowrap="nowrap">${l.sendStart} </td>
         <td nowrap="nowrap">${l.sendName} </td>
         <td nowrap="nowrap">${l.sendTel}</td>
         <td nowrap="nowrap">${l.sendtoEnd}</td>
         <td nowrap="nowrap">${l.sendtoName}</td>
         <td nowrap="nowrap">${l.sendtoTel}</td>
         <td nowrap="nowrap">${l.orderName}</td>
         <td nowrap="nowrap">${l.orderNumber}</td>
         <td nowrap="nowrap">${l.orderWeight}</td>
         <td nowrap="nowrap">${l.orderBulk}</td>
         <td nowrap="nowrap"><fmt:formatDate value="${l.sendTime}" type="date" pattern="yyyy-MM-dd"/></td>
         
         <td nowrap="nowrap">${l.money}</td>
         <td nowrap="nowrap"><a href="delectnoinvoice?id=${l.id}">删除</a></td>
         </tr>
         
     </tbody>
     </c:forEach> 
   </table>
   
      
   
     </div>     
     </div>
    </div>
    
<!-- 底部 -->
<div id="footer">版权所有：晶科客流 &copy; 2015&nbsp;&nbsp;&nbsp;&nbsp;服务热线：0371-88888888</div>
    
    

 <script>
	!function(){
		laydate.skin('molv');
		laydate({elem: '#Calendar'});
		laydate.skin('molv');
		laydate({elem: '#Calendar2'});
	}();
 
</script>
</body>
</html>