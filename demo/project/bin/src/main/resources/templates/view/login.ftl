<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Chromsys | Log in</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
	<link rel="stylesheet" href="/static/css/font-awesome.min.css">
	<link rel="stylesheet" href="/static/css/animate.min.css">
  	<link rel="stylesheet" href="/static/css/pace-theme-minimal.min.css">
	<link rel="stylesheet" href="/static/css/bootstrap-checkbox.min.css">
	<link rel="stylesheet" href="/static/css/login.css">
	<link rel="stylesheet" href="/static/css/formValidation.css">
    <!--[if lt IE 9]>
        <script src="/static/js/html5shiv.min.js"></script>
        <script src="/static/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition login-page">
    
    <div class="login-box">
      <div class="login-box-body">
        <div class="login-logo">
          <h3><span>${_sysConfig.hospAliase}</span><br>细胞遗传管理系统</h3>
          <#if _sysConfig.logoUrl?has_content><img class="logo img-circle" src="${_static + _sysConfig.logoUrl}" /></#if>
        </div>
        <#-- <p class="login-box-msg">Sign in to start your session</p> -->
        <form id="loginForm" action="" method="post">
          <div class="form-group has-feedback">
            <input type="text" name="username" class="form-control input-lg" value="adminuser" placeholder="用户名">
            <span class="fa fa-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input name="password" type="password" class="form-control input-lg" value="123456" placeholder="密码">
            <span class="fa fa-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <#-- 
              <div class="checkbox checkbox-primary">
                <input type="checkbox" name="rememberMe" id="rememberMe" value="true"> 
                <label for="rememberMe">Remember Me</label>
              </div>
               -->
               <div class="error">${errorMsg}</div>
            </div>
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-lg">登录</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/fastclick.min.js"></script>
<script src="/static/js/pace.min.js"></script>
<script src="/static/js/formValidation.min.js"></script>
<script type= "text/javascript">
	$(function() {
	  FastClick.attach(document.body);
	  	
		$('#loginForm').formValidation({
			framework : 'bootstrap',
			excluded : ':disabled',
			message : 'This value is not valid',
			icon : {
				valid : 'fa fa-check',
				invalid : 'fa fa-times',
				validating : 'fa fa-refresh'
			},
			fields : {
				username: {
					validators : {
						notEmpty : {
							message : '用户名不能为空'
						},
						stringLength : {
							min : 2,
							max : 30,
							message : '登录用户名长度在2-30个字符之间'
						}
					}
				},
				password: {
					validators : {
						notEmpty : { message : '密码不能为空' }
					}
				}
			}
		}).on('err.field.fv', function(e, data) {
			data.element.data('fv.icon').hide();
	    	//data.fv.disableSubmitButtons(false);
	    	
	    }).on('success.field.fv', function(e, data) {
	        data.element.parents('.has-success').removeClass('has-success');
	        data.element.data('fv.icon').hide();
	    }).find('input').on('change', function() {
	    	$('.error').text('');
	    });
	});		
</script>