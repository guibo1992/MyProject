<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    var wlPwd = null;
	 
	$("#name").blur(function() {
		var wlName=$("#name").val();
		var reg=/^[\w\d\u4e00-\u9fa5]{3,16}$/;
		 if(reg.test(wlName)&&wlName!=null){ 
			  $("#tis").text("").css("color","green");
			  return true; 
		  } else{
			   $("#tis").text("请输入用户名3-16位字符").css("color","red"); 
			   document.getElementById("name").select();
			   return false;
			  }	
	});
	
	$("#pwd").blur(function() {
		wlPwd=$("#pwd").val();
		var reg=/^[A-Za-z0-9]{6,12}$/; 
		//alert("hhhh")
		if(reg.test(wlPwd)&&wlPwd!=null){	
			 $("#tis2").text("").css("color","green");
			  return true; 
		  } else{
			   $("#tis2").text("密码必须为6-12位的数字跟字母").css("color","red");   
			   document.getElementById("pwd").select();
			   return false;
			  }
	});
	
	$("#repwd").blur(function(){
		var repwd=$("#repdw").val();
		if(repwd==wlPwd){
			 $("#tis3").text("").css("color","green");
			 return true;
		}
		else{
			 $("#tis3").text("两次密码不一致").css("color","red");   
			   document.getElementById("repwd").select();
			   return false;
			  }
	});
});
$(function(){
	$("#success").click(function(){
		/* var wladmin = $("#form1").serialize(); */
		var wlName=$("#wlName").val();
		var truename=$("#truename").val();
		var wlPwd=$("#wlPwd").val();
		alert(wlName);
		alert(truename);
		alert(wlPwd);
		var wladmin = {"wlName":wlName,"truename":truename,"wlPwd":wlPwd}
		$.post("addSucceed",wladmin,function(data){
			if(data>0){
				alert("1111111111111");
				window.parent.location.reload();
				var index = parent.layer.getFrameIndex("addEmp");
				parent.layer.close(index);
			}
		},"json");
	});
})
</script>
</head>
<body>
<form class="layui-form layui-form-pane" action="" id="form1">
	<div class="layui-form-item"> 
  <label class="layui-form-label">用户名:</label>  	
    <div class="layui-input-block">
      <input id="wlName" style="width:200px; margin-top:30px;" type="text" name="wlName" lay-verify="title" class="layui-input"><span id="tis"></span>   
    </div>
  </div>
  <div class="layui-form-item"> 
  <label class="layui-form-label">真实姓名:</label>  	
    <div class="layui-input-block">
      <input id="truename" style="width:200px; margin-top:30px;" type="text" name="truename" lay-verify="title" class="layui-input"><span id=""></span>   
    </div>
  </div>
  <div class="layui-form-item"> 
  <label class="layui-form-label">密码:</label>  
    <div class="layui-input-block">
      <input id="wlPwd" style="width:200px; margin-top:30px;" type="password" name="wlPwd" lay-verify="title" class="layui-input"><span id="tis2"></span>   
    </div>
  </div>
   <div class="layui-form-item"> 
  <label class="layui-form-label">重复密码:</label>  
    <div class="layui-input-block">
      <input id="repwd" style="width:200px; margin-top:30px;" type="password" name="repwd" lay-verify="title" class="layui-input"><span id="tis3"></span>   
    </div>
  </div>
  <div style="width: 216px;">
      <!-- layui 2.2.5 新增 -->
      <button id="success"  class="layui-btn layui-btn-fluid" style="width:100px">确认注册</button>
      <button class="layui-btn layui-btn-fluid" style="width:100px">取消</button>
    </div>

</form>
</body>
</html>