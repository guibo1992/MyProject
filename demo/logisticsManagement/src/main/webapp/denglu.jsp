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
<script>
$(function(){	
	$("#loginBtn").on('click', function(){		
		 var username = $("#dd").val();//获取用户名
         var password = $("#pwd").val();			
         $.post("loginqt",{"username":username,"password":password}, function(data) {
        	 if (data!=null) {
        	 	window.parent.location.reload();
        	 	var index = parent.layer.getFrameIndex(window.name);
        	 	parent.layer.close(index);
             } else {
            	 alert("登陆失败")
             }               
         });
		});			
	$('#aaa').on('click', function(){
		layer.open({
			  type: 1,
			  area: ['450px', '380px'],
			  skin: 'layui-layer-rim', //加上边框
			  content: 'add1.jsp'
			});		
	});		
	/*$('#loginBtn').click(function () {
        
		alert("hhhh")
		var username = $.trim($("#username").val());//获取用户名
        var password = $.trim($("#password").val());
        

  
        var param = {"username": username, "password": password };

        $.post("loginqt", param, function(data) {
            if (data>0) {               
            	var index = parent.layer.getFrameIndex(window.name);
            	parent.layer.close(index);
            	
            } else {
                alert("登录失败！");
            }
        });*/
        
        
    });
</script>
</head>

<body>
<form class="layui-form " >
<div>
  <div class="layui-form-item"> 
  <label class="layui-form-label">用户名:</label>  
    <div class="layui-input-block">     
      <input id="dd" style="width:300px; margin-top:40px;" type="text" name="username" lay-verify="title" autocomplete="off" placeholder="请输入账户" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
  <label class="layui-form-label" style=" margin-top:30px;">密码框:</label>   
    <div class="layui-input-inline">    
      <input id="pwd" style="width:300px; margin-top:30px;" type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
  	</div>
  </div>
  
  <div style="width: 216px; margin:0px auto;">
      <!-- layui 2.2.5 新增 -->
      <button class="layui-btn layui-btn-fluid" id="loginBtn">登陆</button>
    </div>
   <label class="layui-form-label" id="aaa"><a href="add1.jsp">注册</a></label>
   <label class="layui-form-label" style="margin-left:190px;">忘记密码</label>
   </div>  
  </form>
</body>
</html>