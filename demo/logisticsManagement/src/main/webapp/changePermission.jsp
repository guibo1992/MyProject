<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;
					});
	$(function(){
		$("#bot1").click(function(){
		/* var wlId = $("#wlId").val();
		var wlName = $("#wlName").val();
		var truename = $("#truename").val();
		var roleid = $("#roleid").val();
		var wlPwd = $("#wlPwd").val();
		alert(roleid); */
		var wladmin = $("#fomr1").serialize();
		/* var wladmin = {"wlId":wlId,"wlName":wlName,"truename":truename,"roleid":roleid,"wlPwd":wlPwd}; */
		$.post("changeAdminRole",wladmin,function(data){
			if(data>0){
				 window.parent.location.reload();
				var index = parent.layer.getFrameIndex("changePermission.jsp");
				parent.layer.close(index);
			}else{
				
			}
		},"json");
		});
	})
</script>
<body>
	<%-- 	<span>${wlAdmin.wlId }</span>
	<span>${wlAdmin.wlName }</span>
	<span>${wlAdmin.name}</span> --%>

	<form class="layui-form layui-form-pane" id="fomr1">
	
		<div class="layui-form layui-form-pane">
			<div class="layui-input-block">
			<label class="layui-form-label">工号:</label>
				 <input id="wlId" style="width: 200px; margin-top: 30px;" type="text" name="wlId"
					lay-verify="title" readonly="true" class="layui-input" value="${wlAdmin.wlId }"><span
					id="tis"></span>
			</div>
			<div class="layui-input-block">
				<label class="layui-form-label">用户名:</label>
				 <input id="wlName" style="width: 200px; margin-top: 30px;" type="text" name="wlName"
					lay-verify="title" readonly="true" class="layui-input" value="${wlAdmin.wlName }"><span
					id="tis"></span>
			</div>
			<div class="layui-input-block">
				<label class="layui-form-label">真实姓名:</label> 
				<input id="truename" style="width: 200px; margin-top: 30px;" type="text" name="truename"
					lay-verify="title" readonly="true" class="layui-input" value="${wlAdmin.truename}"><span
					id="tis"></span>
			</div>

		<br/><br/>
			<input type="hidden" id="wlPwd" name="wlPwd" value="${wlAdmin.wlPwd }"/>
			 <div class="layui-inline" style="margin-left: 105px;">
      			<label class="layui-form-label">职位选择</label>
   				   <div class="layui-input-inline">
    			    <select id="roleid" name="roleid" lay-verify="required" lay-search="">
       				   <option value="">-------选择该员工的职位-------</option>
     				    <c:forEach items="${roles }" var="s">
						<option  value="${s.rodeId }" >${s.name }</option>
					    </c:forEach>
       			    </select>
     			   </div>
  			  </div>
		

		</div>
		<br/><br/>
		
		 <button type="button"  id="bot1" style="margin-left: 180px;" class="layui-btn">确认修改</button>
	</form>

</body>
</html>