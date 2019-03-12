$(function() {
	'use strict';
	
	var $form = $('#auditForm'), $modal = $('#reasonModal');
	
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
			rejectReason: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 200,
                        message: 'The username must be less than 200 characters long'
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
        	url: '/report/audit/' + $('#reportId').val() + '/submit',
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
					content : "审核驳回成功!",
					buttons : {
						close : { 
							text : '确定', 
							btnClass : 'btn-success',
							action: function() {
								location.href = "/report/audit/list.html";
							}
						}
					}
				});
    	    }
        }); 
	});
	
	$modal.on('show.bs.modal', function (e) {
		$form.find('[name="rejectReason"]').val('');
		$form.formValidation('resetForm');
	});
	
	$('#auditPass').on('click', function() {
		$.get('/report/audit/' + $('#reportId').val() + '/submit', {auditStatus: true}, function(data) {
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
				content : "审核成功!",
				buttons : {
					close : { 
						text : '确定', 
						btnClass : 'btn-success',
						action: function() {
							location.href = "/report/audit/list.html";
						}
					}
				}
			});
		});
	})
	
	
});