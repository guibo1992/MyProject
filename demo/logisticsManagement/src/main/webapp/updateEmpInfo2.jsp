<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

 $(function() {
		
		$("#userName").blur(function() {
			var wlName=$("#userName").val();
			var reg=/^[\w\d\u4e00-\u9fa5]{3,16}$/;
			 if(reg.test(wlName)&&wlName!=null){ 
				  $("#tis").text("").css("color","green");
				  return true; 
			  } else{
				   $("#tis").text("请输入用户名3-16位字符").css("color","red"); 
				   document.getElementById("userName").select();
				   return false;
				  }	
		});
		
		$("#userPwd").blur(function() {
			var wlPwd=$("#userPwd").val();
			var reg=/^[A-Za-z0-9]{6,12}$/; 
			//alert("hhhh")
			if(reg.test(wlPwd)&&wlPwd!=null){	
				 $("#tis2").text("").css("color","green");
				  return true; 
			  } else{
				   $("#tis2").text("密码必须为6-12位的数字跟字母").css("color","red");   
				   document.getElementById("userPwd").select();
				   return false;
				  }
		});

});

</script>
</head>
<body>
<form class="layui-form layui-form-pane" action="updatepwd">
<input type="hidden" id="userId" name="wlId" value="${AdminInfo.wlId }">
	<div class="layui-form-item"> 
  <label class="layui-form-label">用户名:</label>  
    <div class="layui-input-block">
      <input id="name" style="width:200px; margin-top:30px;" type="text" disabled="disabled" lay-verify="title" class="layui-input" name="wlName" value="${AdminInfo.wlName }">
    </div>
  </div>
  <div class="layui-form-item"> 
  <label class="layui-form-label">密码:</label>  
    <div class="layui-input-block">
      <input id="pwd" style="width:200px; margin-top:30px;" type="password" name="wlPwd" lay-verify="title" class="layui-input" placeholder="${AdminInfo.wlPwd }">   
    </div>
  </div>
  <div style="width: 216px;">
      <!-- layui 2.2.5 新增 -->
      <button class="layui-btn layui-btn-fluid">确定修改</button>
    </div>

</form>
</body>
</html>