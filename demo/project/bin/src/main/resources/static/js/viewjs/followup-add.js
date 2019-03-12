$(function() {
	'use strict';
	
	var $form = $('#followupForm');
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	$('#specimenNo').typeahead({
        source: function(query, process) { 
        	if (!$.trim(query)) {
        		return false;
        	}
            $.post("/specimen/sn/list", {keyword: query}, function(data) {
                process(data.map(function(s) {
                	return s.specimenNo;
                }));
            });
        },
        delay: 500,
        minLength: 1
        // afterSelect: function (item) { },
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
			subject: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 50,
                        message: 'The username must be less than 50 characters long'
                    }
                }
			},
            specimenNo: {
                validators: { notEmpty: { message: 'The value is required' } }
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
        	url: '/followup/record/add',
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
					content : "操作已成功!",
					buttons : {
						close : { 
							text : '确定', 
							btnClass : 'btn-success',
							action: function() {
								$form.find('.form-control').val('');
								$form.find('[name="followupStatus"]').first().prop('checked', true);
							}
						}
					}
				});
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
	
	$('#back').on('click', function() {
		location.href = "/followup/list.html";
	});
});