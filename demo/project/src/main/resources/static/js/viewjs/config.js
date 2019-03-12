
$(function() {
	var $form = $('form');
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	$form.formValidation({
		framework : 'bootstrap',
		excluded : ':disabled',
		message : 'This value is not valid',
		icon : {
			valid : 'fa fa-check',
			invalid : 'fa fa-times',
			validating : 'fa fa-refresh'
		},
		fields : {
			idCode: {
                validators: {
                    notEmpty: { message: 'The code is required.' },
                    stringLength: {
                        max: 128,
                        message: 'The value is not valid id code'
                    }
                }
			},
            hospName: {
                validators: { notEmpty: { message: 'The hospital name is required' } }
            },
            hospAliase: {
                validators: { 
                	notEmpty: { message: 'The hospital shortname is required' },
                	stringLength: {
                        min: 2,
                        max: 6,
                        message: 'The shortname must be greater than 2 and less than 6 characters long'
                    }
                }
            },
            smbDomain: {
            	validators: { 
            		// notEmpty: { message: 'The value is required' },
            		ip: { message: 'The value is not valid IP address' }
            	}
            },
            ntlmUsername: {
            	validators: { 
            		// notEmpty: { message: 'The value is required' },
            		stringLength: {
                        max: 30,
                        message: 'The value must be less than 30 characters long'
                    }
            	}
            },
            ntlmPassword: {
            	validators: { 
            		stringLength: {
                        max: 30,
                        message: 'The value must be less than 30 characters long'
                    }
            	}
            },
            shareSource: {
            	validators: { 
            		// notEmpty: { message: 'The value is required' },
            		regexp: {
                        regexp: /^(\/[a-zA-Z0-9]+){2,}$/,
                        message: 'The source is not valid'
                    },
            		stringLength: {
                        max: 50,
                        message: 'The value must be less than 50 characters long'
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
        	url: '/config/update',
        	type: 'post',
        	dataType: 'json',
    	    success: function(data) {
    	    	if (!data.success) {
    	    		$.alert({
						title : '<i class="fa fa-warning"></i> 错误',
						content : data.message,
						buttons : {
							close : { text : '关闭', btnClass : 'btn-danger' }
						}
					});
    	    	   return false;
    	    	}
    	    	$.alert({
					title : '提示',
					content : "系统配置设置成功!",
					buttons : {
						ok : { 
							text : '确认', 
							btnClass : 'btn-primary',
							action: function() {
								location.reload();
							}
						}
					}
				});
    	    }
        }); 
	});
	
	$('#testNtlm').on('click', function() {
		$('#test_result').removeClass('hide').text('测试中 ...')
		var params = {
			domain: $form.find('[name="smbDomain"]').val(),
			username: $form.find('[name="ntlmUsername"]').val(),
			password: $form.find('[name="ntlmPassword"]').val(),
			source: $form.find('[name="shareSource"]').val()
		};
		$.get('/config/smb/connect/test', params, function(data) {
			if (data.success) {
				$('#test_result').text('连接成功!')
			} else {
				$('#test_result').removeClass('text-success').addClass('text-danger').text('连接失败,请检查配置!');
			}
		});
	});
	
	$('input[type="file"]').fileupload({
		url: "/transfer/image/upload",
		dataType: 'json',
        paramName: 'file',
        maxFileSize: 10485760,
        acceptFileTypes: /(\.|\/)(jpe?g|png)$/i,
        singleFileUploads: true,
		messages: {
        	acceptFileTypes: 'File type not allowed',
            maxFileSize: 'File exceeds maximum allowed size of 10MB'
        },
    	processfail: function (e, data) {
    		var file = data.files[0];
			$.alert({
				icon: 'fa fa-warning',
				title: '错误',
				content: file.error + ': ' + file.name,
				buttons: { close: { text: '关闭', btnClass: 'btn-danger' } }
			});
        }
	}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
	
	$('#uploadLogo').fileupload({
    	done: function(e, data) {
    		var result = data.result;
    		if (!result.success) {
    			$.alert({
    				icon: 'fa fa-warning',
    				title: '错误',
    				content: result.message,
    				buttons: {
    					close: { text: '关闭', btnClass: 'btn-danger' }
    				}
    			});
    			return false;
    		}
    		//$('#logo').attr('src', _static + result.data.file);
    		
    		var loadingImage = loadImage(data.files[0], 
        		function (img) { 
        			var $fileinput = $('.fileinput-button');
        			$fileinput.find('img').remove();
        			$fileinput.append($(img));
        		}
            );
    		
    		
        	$form.find('[name="hospLogo"]').val(result.data.file);
        	$form.formValidation('revalidateField', "hospLogo");
    	}
	});
	
	
});