<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript">
$(function(){
	$("#but1").click(function(){
		
	
	//	$(document).on("click",'#but1',function){
		var id=$("#note").val();
		var dataform = $("#form1").serialize();
		var cid=$("#orderCar").val();
		
		$.post("deleUserOrder",{"id":id,"cid":cid},function(data){
		
			if(data>0){
				
				
				$.post("addinvoice",dataform,function(data){
					if(data>0){
						
						
						alert("审查通过,成功下单")
						location.href="getallinvoice";
					}
						else{
							alert("审查不通过")	
							location.href="getalluserorder";
						}
							
				},"json");	
			}
		},'json')
		/* alert(JSON.stringify(dataform)); */
		
		/* alert(JSON.stringify(aa));
		var note=$("#note").val();
		var sendName=$("#sendName").val();
		var sendtoName=$("#sendtoName").val();
		var  sendStart=$("#sendStart").val();
		var sendtoEnd=$("#sendtoEnd").val();
		var sendTel=$("#sendTel").val();
		var sendtoTel=$("#sendtoTel").val();
		 var orderName=$("#orderName").val();
		 var orderNumber=$("#orderNumber").val();
		 var orderWeight=$("#orderWeight").val();
		 var orderBulk=$("#orderBulk").val();
		 var money=$("#money").val();
		 var sendTime=$("#sendTime").val();
		 var orderCar=$("#orderCar").val();
		 var stationId=$("#stationId").val();
		 */
		 
	
		
	});
	$("#but2").click(function(){
		var id=$("#note").val();
		var dataform = $("#form1").serialize();
		
		$.post("deleUserOrder",{"id":id},function(data){
			
			if(data>0){
				$.post("addnoinvoice",dataform,function(data){
					if(data>0){
						alert("审查不通过")
						location.href="getnoinvoice";
					}
						else{
							alert("审查失败")	
							location.href="getalluserorder";
						}
							
				},"json");	
			}
		},'json')
		
	});
});

</script>
</head>
<body>

     <div class="right"  id="mainFrame">
     
     <div class="right_cont">
<ul class="breadcrumb">当前位置：
  <a href="#">首页</a> <span class="divider">/</span>
  <a href="#">订单处理</a> <span class="divider">/</span>
  下单处理
</ul>
   
   <div class="title_right"><strong>发货单填写</strong></div>  
<div style="width:900px;margin:auto;">

   <form id="form1" name="form1" method="post">
 
       <table class="table table-bordered">
         <tr>
      <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">票号：</td>
       <td><input type="text" name="note" id="note"  class="span1-1"  value="${list.id }"/></td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">发货站：</td>
     <td width="23%"><input type="text" name="sendStart" id="sendStart" class="span1-1" value="${list.sendStart }" /></td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">收获站：</td>
     <td><input type="text" name="sendtoEnd" id="sendtoEnd" class="span1-1" value="${list.sendtoEnd }"   /></td>
     </tr>
     <tr>
     
     
     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">发货人：</td>
     <td><input type="text" name="sendName" id="sendName"  class="span1-1"  value="${list.orderSend }"/></td>
     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">电话：</td>
     <td><input type="text" name="sendTel" id="sendTel"  class="span1-1"  value="${list.sendTel }" /></td>
     </tr>
     <tr>
      
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">收货人：</td>
       <td><input type="text" name="sendtoName" id="sendtoName"  class="span1-1" value="${list.orderSendto }"  /></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">电话：</td>
       <td><input type="text" name="sendtoTel" id="sendtoTel" class="span1-1"  /></td>
       <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" >日期：</td>
     <td width="23%"><input type="text"  class="laydate-icon span1-1" id="sendTime" name="sendTime" value="<fmt:formatDate value='${list.orderTime}' pattern='yyyy-MM-dd'/>"  /></td>
     </tr>
       </table>
     
      <table class="table table-bordered">
        <tr>
           <td align="center" bgcolor="#f1f1f1"><strong>货物名称</strong></td>
           
           <td align="center" bgcolor="#f1f1f1"><strong>数量</strong></td>
          
           <td align="center" bgcolor="#f1f1f1"><strong>重量</strong></td>
           <td align="center" bgcolor="#f1f1f1"><strong>体积</strong></td>
            <td align="center" bgcolor="#f1f1f1"><strong>运费</strong></td>
          
            
         </tr>
         <tr>
           <td align="center"><input type="text" name="orderName" id="orderName" class=" span2" value="${list.orderName }"/></td>
           
           <td align="center"><input type="text" name="orderNumber" id="orderNumber" class=" span1 text-center" value="${list.orderNumber }" /></td>
           <td align="center"><input type="text" name="orderWeight" id="orderWeight" class=" span1 text-center"  /></td>
           <td align="center"><input type="text" name="orderBulk" id="orderBulk" class=" span1 text-center"  /></td>
           <td align="center"><input type="text" name="money" id="money" class=" span1 text-center"  /></td>
           <td align="center">
         
            
         </tr>
       </table>
      <table class="table table-bordered" >
        <tr>
          <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">车辆：</td>
          <td nowrap="nowrap">
           <select name="orderCar" id="orderCar" class=" span1-1">
          <c:forEach items="${carname  }" var="h">
            <option value="${h.id }">${h.carName }</option>
             </c:forEach>
          </select>
                </td>
           <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">分理站：</td>
          <td nowrap="nowrap"><select name="stationId" id="stationId" class=" span1-1">
          <c:forEach items="${sta  }" var="s">
            <option value="${s.id }">${s.stationName }</option>
            </c:forEach>
          </select>
          </td>
        </tr>
      
       
       
        
      </table>
           
     <table  class="margin-bottom-20  table no-border" >
       <tr>
     	<td class="text-center"><input type="button" name="but1" id="but1" value="提交"  style="width:80px;" /></td>
     	
     	<td class="text-center"><input type="button" name="but2" id="but2" value="回执"  style="width:80px;" /></td>
     
     </tr>
   </table>
   </form>
      <div class="alert"> 
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      温馨提示：按“Enter”键进行切换；&nbsp;&nbsp;按“F10”保存；&nbsp;&nbsp;按“F5”代收货款；&nbsp;&nbsp;按“F6”返款； 
       </div>
   
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
}();
 
</script>
</body>
</html>