<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>晶科物流管理系统</title>
<link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/css.css" />
	<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/sdmenu.js"></script>
	<script type="text/javascript" src="js/laydate/laydate.js"></script>
	<link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
    <script src="js/jquery1.9.0.min.js"></script>
	<!--jquery.easyui.min.js包含了easyUI中的所有插件-->
	<script src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	$(function(){
		var datetime=new Date();
		var sendTime=datetime.toLocaleDateString();
		
		$("#endtime").val(sendTime);
		layui.use(['laypage', 'layer','table'], function(){
			  var table = layui.table,
			  laypage = layui.laypage
			  ,layer = layui.layer;
			  
			  
			
			  table.render({
				    elem: '#test'
				    ,url:'layuigetAllCount'
				    ,toolbar:true
				    ,where:{sendTime:sendTime}
				    ,page:true
				    
				    //,limit:2
				    //,limits:[5,10,15,20]
				    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
				    ,cols: [[
				      {field:'name', width:'30%', title: '分理处', sort: true,align:'center'}
				      ,{field:'value', width:'30%', title: '运费合计',align:'center'}
				      ,{field:'sendTime', width:'30%', title: '时间', sort: true,align:'center'},
				    ]]
				    ,parseData:function(res){
				    	return{
				    		"code":0,
				    		"msg":"",
				    		"count":res.total,
				    		"data":res.rows
				    	};
				    }
				  });
			  
			  
		});
		 //月统计
		 $("#monthbtn").click(function(){
			 var y=datetime.getFullYear();
				var m=datetime.getMonth()+1;
				var name="";
				var starttime=y+"-"+m+"-01";
				if(m==1||m==3||m==5||m==7||m==8||m==10||m==12){
					var endtime=y+"-"+m+"-31";
				}else{
					var endtime=y+"-"+m+"-30";
				}
				layui.use(['laypage', 'layer','table'], function(){
					  var table = layui.table,
					  laypage = layui.laypage
					  ,layer = layui.layer;
					  
					  
					
					  table.render({
						    elem: '#test'
						    ,url:'getSomeCount'
						    ,toolbar:true
						    ,where:{starttime:starttime,endtime:endtime,name:name}
						    ,page:true
						    //,limit:2
						    //,limits:[5,10,15,20]
						    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
						    ,cols: [[
						      {field:'name', width:'30%', title: '分理处', sort: true,align:'center'}
						      ,{field:'value', width:'30%', title: '运费合计',align:'center'}
						      ,{field:'time', width:'30%', title: '时间', sort: true,align:'center'},
						    ]]
						    ,parseData:function(res){
						    	return{
						    		"code":0,
						    		"msg":"",
						    		"count":50,
						    		"data":res
						    	};
						    }
						  });
					  
					  
				});
			
		 });
		 
		//日统计
		$("#daybtn").click(function(){
			
			layui.use(['laypage', 'layer','table'], function(){
				  var table = layui.table,
				  laypage = layui.laypage
				  ,layer = layui.layer;
				  
				  table.render({
					    elem: '#test'
					    ,url:'layuigetAllCount'
					    ,toolbar:true
					    ,where:{sendTime:sendTime}
					    ,page:true
					    //,limit:2
					    //,limits:[5,10,15,20]
					    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
					    
					    ,cols: [[
					      {field:'name', width:'30%', title: '分理处', sort: true,align:'center'}
					      ,{field:'value', width:'30%', title: '运费合计',sort: true,align:'center'}
					      ,{field:'sendTime', width:'30%', title: '时间', sort: true,align:'center'},
					    ]]
					    ,parseData:function(res){
					    	return{
					    		"code":0,
					    		"msg":"",
					    		"count":res.total,
					    		"data":res.rows
					    	};
					    }
					  });
				  
				  
			});
		})
		//动态搜索
		$("#selbut").click(function(){
			var starttime=$("#starttime").val();
			var endtime=$("#endtime").val();
			var name=$("#name").val();
			
			layui.use(['laypage', 'layer','table'], function(){
				  var table = layui.table,
				  laypage = layui.laypage
				  ,layer = layui.layer;
	
				  table.render({
					    elem: '#test'
					    ,url:'getSomeCount'
					    ,toolbar:true
					    ,where:{starttime:starttime,endtime:endtime,name:name}
					    ,page:true
					    ,limit:10
					    //,limits:[5,10,15,20]
					    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
					    ,cols: [[
					      {field:'name', width:'30%', title: '分理处', sort: true,align:'center'}
					      ,{field:'value', width:'30%', title: '运费合计',align:'center'}
					      ,{field:'time', width:'30%', title: '时间', sort: true,align:'center'},
					    ]]
					    ,parseData:function(res){
					    	return{
					    		"code":0,
					    		"msg":"",
					    		"count":50,
					    		"data":res
					    	};
					    }
					  });
				  
				  
			});
		})

		
	});
			
	</script>
	
	
</head>
<body>
<div class="Switch"></div>
     <script type="text/javascript">
	$(document).ready(function(e) {
    $(".Switch").click(function(){
	$(".left").toggle();
	 
		});
});
</script>
<div class="right"  id="mainFrame">
     
     <div class="right_cont">
<ul class="breadcrumb">当前位置：
  <a href="#">首页</a> <span class="divider">/</span>
  <a href="#">统计</a> <span class="divider">/</span>
  分理处排行
</ul>

   
   <!-- 搜索框 -->
   <table class="table table-bordered">
         <tr>
         <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">开始时间:</td>
     <td width="23%"><input type="text"  class="laydate-icon  span1-1" id="starttime"/></td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">结束时间:</td>
     <td width="23%"><input type="text"  class="laydate-icon  span1-1" id="endtime" value=""/></td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">分理处:</td>
     <td><input type="text" id="name"  class=" span1-1" /></td>
     </tr>
       </table>
       
       <table  class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="submit" value="查询" id="selbut" class="btn btn-info " style="width:80px;" /></td>
     </tr>
 </table>
 
   <!-- 数据表格 -->
<div class="title_right"><strong>分理处发货排行榜</strong></div>
<!-- 日统计 -->
<a id="daybtn" class="easyui-linkbutton" >日统计</a><a id="monthbtn" class="easyui-linkbutton" >月统计</a>
<!-- toolbar -->

<div id="test"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
		  
		</fieldset>
<div id="demo7"></div>

 </div>
 </div>
</body>
<script>
!function(){
laydate.skin('molv');
laydate({elem: '#starttime'});
laydate.skin('molv');
laydate({elem: '#endtime'});
}();
 
</script>
</html>