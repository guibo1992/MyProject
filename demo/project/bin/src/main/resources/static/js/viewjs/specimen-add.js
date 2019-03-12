
$(function() {
	'use strict';

	var $form = $('#specimenForm');
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});

	$form.formValidation({
		framework : 'bootstrap',
		excluded : [':disabled'],
		message : 'This value is not valid',
		icon : {
			valid : 'fa fa-check',
			invalid : 'fa fa-times',
			validating : 'fa fa-refresh'
		},
		fields : {
			typeId: {
				validators : { notEmpty : { message : 'The type is required' } }
			},
			hisId : {
				validators : {
					notEmpty : { message : 'The HIS ID is required' },
					stringLength : {
						max : 20,
						message : 'The HIS ID must be less than 20 characters long'
					}
				}
			},
			'patient.idcard': {
                validators: {
					regexp: {
		                regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
		                message: 'The value is not a valid ID'
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
			url : '/specimen/add',
			type: 'post',
			dataType : 'json',
			success : function(data) {
				if (!data.success) {
					$.alert({
						title: '错误',
						content: data.message,
						buttons: {
							close: { text: '关闭', btnClass: 'btn-danger' }
						}
					});
					return false;
				}
				$.alert({
					title: '提示',
					content: "标本创建成功!",
					autoClose: 'ok|1000',
					buttons: {
						ok: { 
							text: '确定', 
							btnClass: 'btn-primary',
							action: function() {
								$form.find('.form-control').not('.chosen-select').val('');
								$form.find('[name="patient.sex"]').prop('checked', false);
								$('#clinicalInfos').find('[type="checkbox"]').prop('checked', false);
								$('#clinicalInfos').find('.collapse > input').val('').parent().removeClass('in');
							}
						}
					}
				});
				$form.formValidation('resetForm');
			}
		});
	}).find('[name="patient.birthdate"]').datepicker({
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		weekStart : 0,
		showOnFocus : true,
		keyboardNavigation : false,
		todayHighlight : true,
		autoclose : true,
		todayBtn : false
	}).on('change', function(e) {
		$form.formValidation('revalidateField', 'patient.birthday');
	});
	
	var interval, run = false;
	var count = 0, listenCount;
	
//	$('#hisId').bind('input propertychange', function() { 
//		if ($('input[type="radio"]:checked').val() == 0) {
//			return false;
//		}
//		
//		count++;
//		if (run) {
//			return false;
//		}
//		
//		// 提示正在保存
//		interval = setInterval(function() {
//			if (listenCount == count) {
//				// 自动保存
//				var fv = $form.data('formValidation').validate();
//		        if (fv.isValid()) {
//		        	clearInterval(interval);
//		        	run = false;
//		        }
//			} else {
//				listenCount = count;
//			}
//		}, 600);
//		run = true;
//	});
	
});
