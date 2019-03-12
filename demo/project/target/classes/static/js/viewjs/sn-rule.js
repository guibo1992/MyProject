
$(function() {
	var $form = $('#ruleForm');
	
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
		fields: {
			maxPrintSequence: {
                validators: {
                    between: {
                        min: 1, max: 99,  message: '请输入一个有效的打印数量,应该在1到99之间'
                    }
                }
            }
        }
	}).on('err.field.fv', function(e, data) {
		data.element.data('fv.icon').hide();
    	data.fv.disableSubmitButtons(false);
    	
    }).on('success.field.fv', function(e, data) {
        data.element.parents('.has-success').removeClass('has-success');
        data.element.data('fv.icon').hide();
        
    }).on('success.form.fv', function(e) {
		e.preventDefault();
		
		$(e.target).ajaxSubmit({
        	url: '/serial/number/rule/update',
        	type: 'post',
        	dataType: 'json',
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
    	    	$.alert({
					title : '提示',
					content : "编码规则设置成功!",
					buttons : {
						ok : { text : '确认', btnClass : 'btn-primary' }
					}
				});
    	    }
        }); 
	});
	
	
	$('#isSpecimenType').on('change', function() {
		if ($(this).prop('checked')) {
			$('#typeList').fadeIn('slow').trigger('chosen:updated');
		} else {
			$('#typeList').fadeOut('fast').trigger('chosen:updated');
		}
	}).trigger('change');
	
});