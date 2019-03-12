<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<title>GPS监控平台</title>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script src="js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style type="text/css">
body{ background:#0066A8 url(img/login_bg.png) no-repeat center 0px;}
.tit{ margin:auto; margin-top:170px; text-align:center; width:350px; padding-bottom:20px;}
.login-wrap{ width:220px; padding:30px 50px 0 330px; height:220px; background:#fff url(img/20150212154319.jpg) no-repeat 30px 40px; margin:auto; overflow: hidden;}
.login_input{ display:block;width:210px;}
.login_user{ background: url(img/input_icon_1.png) no-repeat 200px center; font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif}
.login_password{ background: url(img/input_icon_2.png) no-repeat 200px center; font-family:"Courier New", Courier, monospace}
.btn-login{ background:#40454B; box-shadow:none; text-shadow:none; color:#fff; border:none;height:35px; line-height:26px; font-size:14px; font-family:"microsoft yahei";}
.btn-login:hover{ background:#333; color:#fff;}
.copyright{ margin:auto; margin-top:10px; text-align:center; width:370px; color:#CCC}
@media (max-height: 700px) {.tit{ margin:auto; margin-top:100px; }}
@media (max-height: 500px) {.tit{ margin:auto; margin-top:50px; }}
</style>

<script type="text/javascript">

	


 $(function() {
	 layui.use(['layer', 'element'], function(){
		  var element = layui.element;
		  var $ = layui.jquery, layer = layui.layer;
		  //…
		});
	 
	 $("#addEmp").on("click",function(){
		 layer.open({
			  type: 2,
			  area: ['380px', '400px'],
			  skin: 'layui-layer-rim', //加上边框
			  content: 'addEmpInfo.jsp'
			});
	 });

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
		var wlPwd=$("#pwd").val();
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

});

</script>
</head>
<body>
<div class="tit"><img src="img/tit.png" alt="" /></div>
<div class="login-wrap">
<form action="loginUser" method="post">
  <table id="tab1" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25" valign="bottom">用户名：</td>
    </tr>
    <tr>
      <td><input type="text" id="name" name="wlName" class="login_input login_user" placeholder="请输入3-16用户名" /><br><span id="tis"></span></td>
    </tr>
    <tr>
      <td height="35" valign="bottom">密  码：</td>
    </tr>
    <tr>
      <td><input type="password" id="pwd" name="wlPwd"  class="login_input login_password" placeholder="请输入6-12位密码" /><span id="tis2"></span></td>
    </tr>
    <tr>
       <td height="60" valign="bottom"><input type="submit" id="but" class="btn btn-block btn-login" value="登录"><font color="red">${info }</font></td>
    </tr>
   
  </table>
  <p id="addEmp"><a>账号注册</a></p>
  </form>
</div>
</body>
</html>