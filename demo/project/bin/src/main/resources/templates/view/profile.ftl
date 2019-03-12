<head>
  <title>用户信息</title>
  <link href="/static/css/bootstrap-checkbox.min.css" rel="stylesheet">
</head>
<body>
  <div class="content-wrapper">
	<section class="content-header">
	  <h1> 用户信息
	    <small> 用户管理</small>
	  </h1>
	  <ol class="breadcrumb">
	    <li><a href="/index.htm"><i class="fa fa-home"></i> Dashboard</a></li>
	    <li class="active"> 用户信息</li>
	  </ol>
	</section>
	
	<section class="content">
	  <div class="row">
	    <div class="col-lg-6 col-md-10">
		  <div class="box box-success">
		    <form id="userForm" method="post" class="form-horizontal">
		      <div class="box-header with-border">
		        <h3 class="box-title col-md-12">用户信息详情</h3>
		      </div>
		      <div class="box-body">
			    <input type="hidden" name="id" value="${_active_user.id}" />
			    
			    <div class="row">
			      <div class="col-md-10">
			        <div class="form-group">
	                  <span class="col-md-3 control-label">用户名</span>
	                  <div class="col-md-8">
	                    <input type="text" name="username" value="${_active_user.username}" placeholder="用户唯一登录账号" class="form-control">
	                  </div>
	                </div>
	                <div class="form-group">
	                  <span class="col-md-3 control-label" for="name">真实姓名</span>
	                  <div class="col-md-8">
	                    <input type="text" name="name" value="${_active_user.name}" placeholder="用户的真实姓名" id="name" class="form-control">
	                  </div>
	                </div>
	                <div class="form-group">
	                  <span class="col-md-3 control-label">手机号码</span>
	                  <div class="col-md-8">
	                    <input type="text" name="phone" value="${_active_user.phone}" placeholder="用户手机号" id="phone" class="form-control">
	                  </div>
	                </div>
	              
		            <div class="form-group border-bottom"></div>
	                <div class="form-group">
	                  <span class="col-md-3 control-label"></span>
	                  <div class="col-md-8">
		      	        <button type="submit" id="save" class="btn btn-primary"> <i class="fa fa-floppy-o"></i> 保 存 </button>
		      	      </div>
	                </div>
                  </div>
	            </div>
			  </form>
		    </div>
		  </div>
		</div>
 	  </div>
	</section>
  </div>
</body>
<imports>
  <script src="/static/js/jquery.form.min.js"></script>
  <script>
	$(function() {
		var $form = $('#userForm');
		$form.formValidation({
			framework: 'bootstrap',
			excluded: ':disabled',
        	message: 'This value is not valid',
        	icon: { valid: 'fa fa-check', invalid: 'fa fa-times', validating: 'fa fa-refresh' },
        	fields: {
	            'username': {
	                validators: {
	                    notEmpty: { message: 'The username is required.' },
	                    stringLength: {
                            min: 4,
                            max: 30,
                            message: 'The username must be more than 4 and less than 30 characters long'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The username can only consist of alphabetical, number, dot and underscore'
                        }
	                }
	            },
	            'name': {
	                validators: { notEmpty: { message: 'The name is required' } }
	            },
	            'phone': {
	                validators: { notEmpty: { message: 'The phone is required.' } }
	            }
        	}
    	}).on('success.field.fv', function(e, data) {
	        data.element.parents('.has-success').removeClass('has-success');
	        data.element.data('fv.icon').hide();
	        
	    }).on('success.form.fv', function(e) {
            e.preventDefault();
			$(e.target).ajaxSubmit({
	        	url: '/user/update',
	        	type: 'post',
	        	dataType: 'json',
	    	    success: function(data) {
					if (!data.success) {
						$.alert({
							title: '错误!',
							content: data.message,
							buttons: {
								close: { text: '关闭', btnClass: 'btn-danger'}
							}
						});
						return false;
					} 
					$.alert({
						title: '提示!',
						content: '用户资料更新成功!',
						buttons: {
							ok: { text: '确定', btnClass: 'btn-primary'}
						}
					});
				}
			});
        });
	});
  </script>
</imports>
