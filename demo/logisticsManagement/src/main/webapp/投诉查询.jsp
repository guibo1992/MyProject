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
/*	$(function(){
		
		$(".del").click(function(){
			var ss=$(this).next().val();
			
			$.get('deletetousu?id='+ss,function(data){
				alert(data.temp);
			},'json')
			
		});
	});*/
</script> 	
</head>
<body>

	<div class="right_cont">
		<ul class="breadcrumb">
			当前位置：
			<a href="#">首页</a>
			<span class="divider">/</span>
			<a href="#">投诉处理</a>
			<span class="divider">/</span> 投诉查询
		</ul>

		<div class="title_right">
			<strong>投诉查询</strong>
		</div>
		<div style="width: 900px; margin: auto">

			<table class="table table-bordered table-hover table-striped">
				<tbody>
					<tr align="center">
						<td><strong>No</strong></td>
						<td><strong>投诉人</strong></td>
						<td><strong>票号</strong></td>
						<td><strong>投诉时间</strong></td>
						<td><strong>内容</strong></td>
						<td><strong>建议</strong></td>
						<td><strong>操作 </strong></td>
					</tr>
					<c:forEach items="${list  }" var="l">
						<tr align="center">
							<td>
								${l.id }
							</td>
							<td>${l.tsName }</td>
							<td>${l.note }</td>
							<td><fmt:formatDate value="${l.tsTime }" type="date" pattern="yyyy-MM-dd"/></td>
							<td><textarea  style="width:100%;height:100%;" name="textarea" id="textarea" cols="45" rows="5" >${l.tsCount }</textarea></td>
							<td><textarea  style="width:100%;height:100%;" name="textarea" id="textarea" cols="45" rows="5" >${l.tsIdear }</textarea></td>
							<td>
								
								<a  href="deletetousu?id=${l.id }">删除</a> 
				<!--  			<input type="button" value="删除" class="del"> 
								<input type="hidden" value="${l.id }" >  -->
							</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>

			
		</div>
	</div>
	</div>
	</div>

	<!-- 底部 -->
	<div id="footer">版权所有：晶科客流 &copy;
		2015&nbsp;&nbsp;&nbsp;&nbsp;服务热线：0371-88888888</div>



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