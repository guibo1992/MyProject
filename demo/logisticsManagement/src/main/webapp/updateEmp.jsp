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
		$(document).on('click','#but',function(){

			location.href="updateEmpInfo.jsp";
			/* $.post("getAdmin",wlId,function(data){
				alert("hhhhh")
			},"json"); */
		});
	}); 

</script>
</head>
<body>
<form class="form">
		<p>
			<input type="button" id="but" value="编辑资料">
		</p>
		
		<p>
			<input type="hidden" id="userId" name="userId" value="${AdminInfo.wlId }">
		</p>
			
		<p>用户名:
			<input type="text" id="userName" name="userName" value="${AdminInfo.wlName }">
		</p>
		
		<p>密码:
			<input type="password" id="userPwd" name="userPwd" value="${AdminInfo.wlPwd}">
		</p>

	</form>
</body>
</html>