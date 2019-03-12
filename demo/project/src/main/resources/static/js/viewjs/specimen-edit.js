
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
			specimenType: {
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
			specimenSN: {
				validators : { notEmpty : { message : 'The serial number is required' } }
			}
//			name : {
//				validators : {
//					notEmpty : { message : '患者的姓名不能为空' },
//					stringLength : {
//						max : 20,
//						message : 'The summary must be less than 10 characters long'
//					}
//				}
//			},
//			birthdate: {
//	            validators: {
//	                notEmpty: { message: '患者的出生日期不能为空' },
//	                date: {
//	                    format: 'YYYY-MM-DD', message: 'The date is not a valid'
//	                }
//	            }
//	        },
//			specimenCount: {
//				validators: {
//					notEmpty : { message : 'The count is required' },
//					integer: {
//                        message: 'The value is not an integer'
//                    },
//                    between: {
//                    	min: 1,
//                    	max: 99,
//                    	message: 'The value is invalid integer'
//                    }
//                }
//			}
		}
	}).on('err.field.fv', function(e, data) {
		data.element.data('fv.icon').hide();
    	data.fv.disableSubmitButtons(false);
    	
    }).on('success.field.fv', function(e, data) {
		data.element.parents('.has-success').removeClass('has-success');
		data.element.data('fv.icon').hide();

	}).on('success.form.fv', function(e) {
		e.preventDefault();
		var id = $form.find('[name="id"]').val();
		
		$(e.target).ajaxSubmit({
			url : '/specimen/' + id + '/update',
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
					content: "标本更新成功!",
					buttons: {
						ok: { 
							text: '确定', 
							btnClass: 'btn-primary',
							action: function() {
								location.href = "/specimen/list.html";
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
	
});
