<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
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
		
		$("#but").click(function() {
			/* var wlName=$("#userName").val();
			var wlPwd=$("#userPwd").val(); 
			alert(wlName)
			alert(wlPwd) */
			var wlAdmin=$("#form").serialize();
			//var wlAdmin={"wlName":wlName,"wlPwd":wlPwd};
			$.post("updatepwd",wlAdmin,function(data){
				
				if (data==1) {
					alert("修改成功")
					location.href="main.jsp";
				}else {
					alert("修改失败")
					location.href="login.jsp";
				}
			},"json");
		});
	}); 

</script>
</head>
<body>
<form id="form" name="form1">
		
		<p>
			<input type="hidden" id="userId" name="wlId" value="${AdminInfo.wlId }">
		</p>
			
		<p>
			<input type="hidden" id="userName" name="wlName" value="${AdminInfo.wlName }">
			<span id="tis"></span>
		</p>
		
		<p>密码:
			<input type="password" id="userPwd" name="wlPwd" placeholder="请输入新密码">
			<span id="tis2"></span>
		</p>

		<p>
			<input type="button" id="but" value="确定">
		</p>
	</form>
</body>
</html>