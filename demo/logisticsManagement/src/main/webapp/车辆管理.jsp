<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script language="javascript" type="text/javascript"
	src="js/jquery1.9.0.min.js"></script>
<script language="javascript" type="text/javascript">
$(function(){
	$("#but1").click(function(){
		var dataform = $("#form1").serialize();
		$.post("addcar",dataform,function(data){
			if(data>0){
				alert("添加成功")
				location.href="车辆管理.jsp";
			}
				else{
					alert("添加失败")	
					location.href="车辆管理.jsp";
				}
					
		},"json");	
		
	});

});

/*	$(function() {
		 
			$('#tsName').blur(function() {
			var a = $("#tsName").val();
			var reg = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
			if (reg.test(a)) {
				alert(111);
				f1=true;
			} else {
				alert(222);
				$('#btn').attr("style","display:none;");
				f1=false;
			}
		});
		
		
		$('#tsTel').blur(function() {
			var a = $("#tsTel").val();
			var reg = /^\d{11}$/;
			if (reg.test(a)) {
				alert(111);
				f2=true;
				
			} else {
				f2=false;
				alert(222);
				
			}
		})  	
		 function zong(){
		if(f1 && f2==true){
			return true;
		}else{
			return false;	
		}	
		 
			 
	}
	});*/
	
/*
	 function tsName(){
	 var a=$("#tsming").val();
	 var reg=/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
	 if(reg.test(a)){
	 return true;
	 }else{
	 return false;
	 } 		 
	 } 
	 function tsTel(){
	 var b=$("#tst").val();
	 var reg=/^\d{11}$/;
	 if(reg.test(b)){
	 return true;
	 }else{
	 return false;
	 } 		 
	 }
	 function slName(){
	 var a=$("#slming").val();
	 var reg=/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
	 if(reg.test(a)){
	 return true;
	 }else{
	 return false;
	 } 		 
	 }
	 function allzhuce(){
	 if(tsName()&&tsTel()&&slName())
	 return true;
	 else
	 return false;
	 } 
 */
</script>
</head>
<body>

	

		<div class="right" id="mainFrame">

			<div class="right_cont">
				<ul class="breadcrumb">
					当前位置：
					<a href="#">首页</a>
					<span class="divider">/</span>
					<a href="#">车辆管理</a>
					<span class="divider">/</span> 电脑开票
				</ul>

				<div class="title_right">
					<strong>添加车辆</strong>
				</div>
				<div style="width: 900px; margin: auto">

					<form id="form1" name="form1"  method="post" >
						<table class="table table-bordered">
							<tr>
								<td width="30%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">车牌号：</td>
								<td width="38%"><input type="text" name="carName" id="carName"
									class="span1-1" /></td>
							</tr>	
						</table>
						<table class="margin-bottom-20 table  no-border">
							<tr>
								<td class="text-center"><input id="but1" type="submit" value="提交"
									class="btn btn-info " style="width: 80px;" /></td>
							</tr>
						</table>
				</div>
			</div>
		</div>
	</div>
	</form>
	<!-- 底部 -->
	<div id="footer">版权所有：晶科客流 &copy;
		2015&nbsp;&nbsp;&nbsp;&nbsp;服务热线：0371-88888888</div>



	<script>
		!function() {
			laydate.skin('molv');
			laydate({
				elem : '#Calendar'
			});
			laydate.skin('molv');
			laydate({
				elem : '#Calendar2'
			});
		}();
	</script>
</body>
</html>