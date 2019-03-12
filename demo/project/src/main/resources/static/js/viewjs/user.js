$(function() {
	'use strict';
	
	var $table = $('#table'), $status = $('#status');
	var $form = $('#userForm'), $passwdForm = $('#passwdForm');
	
	$form.formValidation({
		framework : 'bootstrap',
		//excluded : ':disabled',
		message : 'This value is not valid',
		icon : {
			valid : 'fa fa-check',
			invalid : 'fa fa-times',
			validating : 'fa fa-refresh'
		},
		fields : {
			username: {
                validators: {
                    notEmpty: { message: 'The username is required.' },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: 'The username must be more than 3 and less than 30 characters long'
                    }
                }
			},
            name: {
                validators: { notEmpty: { message: 'The name is required' } }
            },
            phone: {
                validators: {
                	phone: {
                        country: 'CN',
                        message: 'The value is not valid %s phone number'
                    }
                }
            },
            password: {
            	validators: {
            		notEmpty: { message: 'The value is required.' },
            		regexp: {
            			regexp: /^[a-zA-Z][a-zA-Z0-9_]{5,17}$/,
            			message: '密码 必需以字母开头，可包含字母、数字、下划线的组合的6-18位字符'
            		}
              	}
			},
			repassword: {
				validators: { 
					notEmpty: { message: 'The value is required' },
					identical: {
						field: 'password',
						message: 'The value and its confirm are not the same'
					}
				}
			}
		}
	}).on('err.field.fv', function(e, data) {
    	data.fv.disableSubmitButtons(false);
    	
    }).on('success.field.fv', function(e, data) {
        data.element.parents('.has-success').removeClass('has-success');
        data.element.data('fv.icon').hide();
        
    }).on('success.form.fv', function(e) {
		e.preventDefault();
		
		$(e.target).ajaxSubmit({
        	url: '/user/add',
        	type: 'post',
        	dataType: 'json',
        	beforeSubmit: function(arr, $form, options) {
        		if ( $form.find('[name="id"]').val() ) {
        			options.url = '/user/' + $form.find('[name="id"]').val() + '/update';
        		}
        	},
    	    success: function(data) {
    	    	$form.formValidation('resetForm');
    	    	
    	    	if (!data.success) {
    	    		$.alert({
						title : '错误',
						content : data.message,
						buttons : {
							close : { text : '关闭', btnClass : 'btn-warning' }
						}
					});
    	    	   return false;
    	    	}
    	       
    	    	$table.bootstrapTable('refresh');
    	    	$('[data-dismiss="modal"]').trigger('click');
    	    }
        }); 
	});
	$('#resetpassword').on('change', function(){
		var $password = $form.find('[name="password"]'), $repassword = $form.find('[name="repassword"]');
		
		if ($(this).prop('checked')) {
			$form.find('.password-control').removeClass('hide');
		} else {
			$form.find('.password-control').addClass('hide');
		}
		$form.formValidation('resetForm');
		$form.data('formValidation').resetField("password", '');
		$form.data('formValidation').resetField("repassword", '');
	});
	
	$('#addUser').on('click', function() {
		$.get('/user/total/ceiling', function(data) {
			if (!data.success) {
				$.alert({
					title : '<i class="fa fa-warning"></i> 警告',
					content : data.message,
					buttons : {
						close : { text : '关闭', btnClass : 'btn-warning' }
					}
				});
				return false;
			}
			
			$('#resetPwd').addClass('hide');
			$form.find('.form-control').val('');
			$('#sign_img > img').attr('src', '');
			$form.find('[type="radio"]:first').prop('checked', true);
			
			$form.formValidation('resetForm');
			$('#userModal').modal('toggle');
		});
	});
	
	$status.find('label.btn').on('click', function() {
		var status = $(this).find('[name="status"]').val();
		$table.bootstrapTable('refresh', { query : { status : status } });
	});
		
	var requestParams = function(params) {
		return {
			limit : params.limit,
			offset : params.offset,
			search : params.search,
			status : $status.find('label.active > input').val()
		}
	}
	var typeFormatter = function(val, row, index) {
		if (val > 5) {
			return '管理员';
		} else if (val > 3) {
			return '审核员';
		} else if (val > 1) {
			return '标本分析员';
		} else {
			return '标本录入员';
		}
	}
	
	var statusFormatter = function(value, row, index) {
		return row.status > 0 ? '<span class="label label-success">正常</span>' : '<span class="text-muted">失效</span>';
	}
	function operateFormatter(value, row, index) {
		var array = new Array();
		array.push('<a class="edit btn btn-primary btn-sm margin-r-5" href="#" data-placement="left" title="编辑">');
		array.push('<i class="fa fa-edit"></i> 编辑');
		array.push('</a> ');

		return array.join('');
	}

	window.operateEvents = {
		'click .edit' : function(e, value, row, index) {
			e.preventDefault();
			
			$.get('/user/' + row.id + '/profile', function(data) {
				if (!(data.success)) {
					$.alert({
						title : '错误',
						content : data.message,
						buttons : {
							close : { text : '关闭', btnClass : 'btn-warning' }
						}
					});
					return false;
				} 
				
				var user = data.data;
				$form.find('[name="id"]').val(user.id);
				$form.find('[name="username"]').val(user.username);
				$form.find('[name="type"]').each(function() {
					if ($(this).val() == user.type) {
						$(this).prop('checked', true);
					}
				});
				$form.find('[name="name"]').val(user.name);
				$form.find('[name="signImg"]').val(user.signImg);
				$form.find('[name="dept"]').val(user.dept);
				$form.find('[name="phone"]').val(user.phone);
				$('#sign_img > img').attr('src', _static + user.signImg);
				
				$('#resetPwd').removeClass('hide');
				$('#resetpassword').prop('checked', false);
				$form.find('.password-control').addClass('hide').find('.form-control').val('');
				
				$form.formValidation('resetForm');
				$('#userModal').modal('show');
			});
		},
		'click .remove' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				buttons: {
					confirm: {
						text: '<i class="fa fa-remove"></i> 删除',
		                btnClass: 'btn-danger',
		                action: function () {
		                	$.get('/user/' + row.id + '/remove/', function(data) {
								if (!data.success) {
									$.alert({ 
										content : data.message,
										buttons: { close: { text: '关闭', btnClass: 'btn-danger' } }
									});
									return false;
								}
								$table.bootstrapTable('refresh');
							}, 'json');
	                    }
		            },
		            cancel: { text: '取消', btnClass: 'btn-success' }
		        }
			});
		},
		'click .undelete' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				buttons: {
					confirm: {
						text: '<i class="fa fa-undo"></i> 撤消删除',
		                btnClass: 'btn-danger',
		                action: function () {
		                	$.get('/user/' + row.id + '/undelete/', function(data) {
								if (!data.success) {
									$.alert({ 
										content : data.message,
										buttons: { close: { text: '关闭', btnClass: 'btn-danger' } }
									});
									return false;
								}
								$table.bootstrapTable('refresh');
							}, 'json');
	                    }
		            },
		            cancel: { text: '取消', btnClass: 'btn-success' }
		        }
			});
		}
	};

	$table.bootstrapTable({
		url : '/user/list',
		search : true,
		showRefresh : true,
		showToggle : true,
		showColumns : true,
		detailView : false,
		striped : true,
		pagination : true,
		paginationLoop : false,
		sidePagination : 'server',
		pageSize : 20,
		pageList: [20, 50],
		toolbar : '#toolbar',
		queryParams : requestParams,
		columns : [ {
			field : 'id',
			title : '#'
		}, {
			field : 'username',
			title : '用户名'
		}, {
			field : 'name',
			title : '姓名'
		}, {
			field : 'dept',
			title : '科室'
		}, {
			field : 'type',
			title : '职位',
			formatter: typeFormatter
		}, {
			field : 'phone',
			title : '手机'
		}, {
			field : 'loginTime',
			title : '最后登录时间'
		}, {
			field : 'gmtModified',
			title : '更新时间',
			visible: false
		}, {
			events : operateEvents,
			class: 'col-xs-1',
			formatter : operateFormatter
		} ]
	});
	
	$('input[type="file"]').fileupload({
		url: "/transfer/image/upload",
		dataType: 'json',
        paramName: 'file',
        acceptFileTypes: /(\.|\/)(jpe?g|png)$/i,
        maxFileSize: 10485760,
        singleFileUploads: true,
        formData: { param: Math.random() * 1000, thumb: false },
		messages: {
        	acceptFileTypes: 'File type not allowed',
            maxFileSize: 'File exceeds maximum allowed size of 10MB'
        },
        send: function(e, data) {
    		$(this).closest('.form-group').find(".progress").show(100);
    	},
    	progressall: function(e, data) {
    		let progress = parseInt(data.loaded / data.total * 100, 10);  
    		$(this).closest('.form-group').find(".progress-bar").css('width', progress + '%'); 
    	},
    	fail: function(e, data) {
    		$(this).closest('.form-group').find(".progress").hide();
    	},
    	always: function (e, data) {
    		var $progress = $(this).closest('.form-group').find(".progress");
    		setTimeout(function() { 
        		$progress.fadeOut(200, function() {
            		$(this).hide().find(".progress-bar").css('width', '0%'); 
            	});
    		}, 800);
    	},
    	processfail: function (e, data) {
    		var file = data.files[0];
			$.alert({
				icon: 'fa fa-warning',
				title: '错误',
				content: file.error + ': ' + file.name,
				buttons: { close: { text: '关闭', btnClass: 'btn-danger' } }
			});
        },
        done: function(e, data) {
    		var result = data.result;
        	$form.find('[name="signImg"]').val(result.data.file);
        	
        	loadImage(data.files[0], 
        		function (img) { 
        			$('.fileinput-button > img').remove(); 
        			$('.fileinput-button').append(img);
        			//$(img).addClass('img-thumbnail sign-image ');
        		},
            	{maxWidth: 120, maxHeight: 32}
            );
        	
        	$form.formValidation('revalidateField', "image");
    	}
	}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
});