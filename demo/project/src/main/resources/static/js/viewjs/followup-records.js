$(function() {
	'use strict';
	
	var $form = $('#followupRecordForm'), $modal = $('#followupRecordModal');
	
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
			subject: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 50,
                        message: 'The username must be less than 50 characters long'
                    }
                }
			},
            followupDate: {
            	validators: { notEmpty: { message: 'The value is required' } }
            },
            followupContent: {
            	validators: { 
            		notEmpty: { message: 'The value is required' },
            		stringLength: {
                        max: 250,
                        message: 'The username must be less than 250 characters long'
                    }
            	}
            },
            followuper: {
            	validators: { 
            		notEmpty: { message: 'The value is required' },
            		stringLength: {
                        max: 20,
                        message: 'The username must be less than 20 characters long'
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
        	url: '/followup/record/' + $form.find('[name="id"]').val() + '/update',
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
    	    	$form.find('.form-control').val('');
				$form.find('[name="followupStatus"]').first().prop('checked', true);
				location.reload();
				$modal.modal('hide');
    	    }
        }); 
	}).find('[name="followupDate"]').datepicker({
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		orientation: "bottom left",
		weekStart : 0,
		showOnFocus : true,
		keyboardNavigation : false,
		todayHighlight : true,
		autoclose : true,
		todayBtn : false,
		clearBtn : true
	}).on('changeDate', function(e) {
		$form.formValidation('revalidateField', 'followupDate');
	});
	
	
	$('[name="edit"]').on('click', function() {
		var id = $(this).attr('id');
		
		$.get('/followup/record/' + id, function(data) {
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
			
			var record = data.data;
			$form.find('[name="id"]').val(record.id);
			$form.find('[name="subject"]').val(record.subject);
			$form.find('[name="specimenNo"]').val(record.specimenNo);
			$form.find('[name="followupDate"]').datepicker('setDate', record.followupDate);
			$form.find('[name="followupContent"]').val(record.followupContent);
			$form.find('[name="followuper"]').val(record.followuper);
			$form.find('[name="followupStatus"]').each(function() {
				if ($(this).val() == record.followupStatus) {
					$(this).prop('checked', true);
				}
			});
			
			$form.formValidation('resetForm');
			$modal.modal('show');
		});
	});
	
	$('[name="remove"]').on('click', function() {
		var id = $(this).attr('id');
	
		$.confirm({
			icon: 'fa fa-question-circle',
			content: '确认要删除该随访记录?',
			buttons: {
				confirm: {
					text: '<i class="fa fa-remove"></i> 删除',
	                btnClass: 'btn-danger',
	                action: function () {
	                	$.get('/followup/recoed/' + id + '/remove/', function(data) {
							if (!data.success) {
								$.alert({ 
									title: '错误',
									content : data.message,
									buttons: { text: '关闭', close: { btnClass: 'btn-danger' } }
								});
								return false;
							}
							
							location.reload();
						}, 'json');
	                }
	            },
	            cancel: { btnClass: 'btn-default' }
	        }
		});
	});
	
	$('#back').on('click', function() {
		location.href = "/followup/list.html";
	});
});