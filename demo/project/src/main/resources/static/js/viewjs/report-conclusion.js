
$(function() {
	'use strict';
	
	var $table = $('#table'), $form = $('#conclusionForm'), $modal = $('#conclusionModal');
	var $type = $('#type');
	
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
			typeId: {
                validators: {
                	notEmpty: { message: 'The value is required.' }
                }
            },
            title: {
            	validators: {
            		notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 50,
                        message: 'The value must be less than 50 characters long'
                    }
            	}
            },
			conclusion: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 250,
                        message: 'The value must be less than 250 characters long'
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
        	url: '/report/conclusion/add',
        	type: 'post',
        	dataType: 'json',
        	beforeSubmit: function(arr, $form, options) {
        		if ( $form.find('[name="id"]').val() ) {
        			options.url = '/report/conclusion/' + $form.find('[name="id"]').val() + '/update';
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
    	    	$modal.modal('hide');
    	    }
        }); 
	});
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	$('#add').on('click', function() {
		$form.find('.form-control').val('');
		$form.find('[role="data-remain"]').trigger('keyup');
		$form.find('.chosen-select').trigger('chosen:updated');
		$form.formValidation('resetForm');
		$modal.modal('show');
	});
	
	$type.on('change', function() {
		$table.bootstrapTable('refresh', { query : { typeId: $type.val() } });
	});
	
	var requestParams = function(params) {
		return {
			limit: params.limit,
			offset: params.offset,
			search: params.search,
			typeId: $type.val()
		}
	}

	function operateFormatter(value, row, index) {
		var array = new Array();
		array.push('<a class="edit btn btn-primary margin-r-5 margin-btm-5" href="#" data-placement="left" title="编辑结论">');
		array.push('<i class="fa fa-edit"></i> 编辑');
		array.push('</a> ');
	
		array.push('<a class="delete btn btn-danger margin-btm-5" href="#" data-placement="left" title="删除结论">');
		array.push('<i class="fa fa-remove"></i> 删除');
		array.push('</a> ');
		
		return array.join('');
	}
	window.operateEvents = {
		'click .edit' : function(e, value, row, index) {
			e.preventDefault();
			
			$form.find('.form-control').val('');
			$form.find('.chosen-select').trigger('chosen:updated');
			
			$.get('/report/conclusion/' + row.id, function(data) {
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
				
				var conclusion = data.data;
				$form.find('.form-control').val('');
				$form.find('[name="id"]').val(conclusion.id);
				$form.find('[name="typeId"]').val(conclusion.typeId).trigger('chosen:updated');
				$form.find('[name="conclusion"]').val(conclusion.conclusion).trigger('keyup');
				$form.formValidation('resetForm');
				$modal.modal('show');
			});
		},
		'click .delete' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				content: '确认删除?',
				buttons: {
					confirm: {
						text: '<i class="fa fa-remove"></i> 删除',
		                btnClass: 'btn-danger',
		                action: function () {
		                	$.get('/report/conclusion/' + row.id + '/delete', function(data) {
								if (!data.success) {
									$.alert({ 
										title: '错误',
										content : data.message,
										buttons: { text: '关闭', close: { btnClass: 'btn-danger' } }
									});
									return false;
								}
								$table.bootstrapTable('refresh');
							}, 'json');
	                    }
		            },
		            cancel: { text: '取消',  btnClass: 'btn-default' }
		        }
			});
		}
	};
	
	$table.bootstrapTable({
		url : '/report/conclusion/list',
		search : true,
		showRefresh : true,
		showToggle : false,
		showColumns : false,
		detailView : false,
		striped : true,
		pagination : true,
		paginationLoop : false,
		sidePagination : 'server',
		pageSize : 10,
		pageList: [10, 20, 50],
		toolbar : '#toolbar',
		queryParams : requestParams,
		columns : [ {
			field : 'id',
			title : '#'
		}, {
			field : 'type.name',
			title : '类型'
		}, {
			field : 'title',
			title : '标题'
		}, {
			field : 'conclusion',
			title : '结论',
			class: 'col-xs-4'
		}, {
			field : 'gmtModified',
			title : '更新时间'
		}, {
			events : operateEvents,
			class: 'col-xs-2',
			formatter : operateFormatter
		} ]
	});
	
});