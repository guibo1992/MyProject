<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>晶科物流管理系统</title>
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
  <a href="#">订单处理</a> <span class="divider">/</span>
  所有下单
</ul>
   
  
       <table class="table table-bordered table-hover table-striped">
         <tbody>
           <tr align="center">
             <td><strong>No</strong></td>
             <td><strong>发货人</strong></td>
             <td><strong>收货人</strong></td>
             <td><strong>发货时间</strong></td>
             
             <td><strong> 操作</strong></td>
           </tr>
           <c:forEach items="${list  }" var="l">
           <tr align="center">
             <td>${l.id }</td>
             <td>${l.orderSend }</td>
             <td>${l.orderSendto }</td>
             <td><fmt:formatDate value="${l.orderTime }" type="date" pattern="yyyy-MM-dd"/></td>
             <td><a id="DataGrid1_ctl03_LinkButton1" href="getoneuserorder?id=${l.id }">详细</a></td>
           </tr>  
           </c:forEach>    
         </tbody>
       </table>
       
       
   </div>
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