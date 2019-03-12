
$(function() {
	'use strict';
	
	var $table = $('#table'), $form = $('#typeForm');
	var $status = $('#status');
	
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
			typeHead: {
                validators: {
                	notEmpty: { message: 'The value is required.' },
                	stringLength: {
                        max: 2,
                        message: 'The value must be less than 2 characters long'
                    }
//                	remote: {
//                        message: 'The value is not available',
//                        url: '/path/to/backend/',
//                        type: 'POST'
//                    }
                }
            },
			name: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 30,
                        message: 'The value must be less than 30 characters long'
                    }
                }
			},
			caseTemplate: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 30,
                        message: 'The value must be less than 30 characters long'
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
        	url: '/specimen/type/add',
        	type: 'post',
        	dataType: 'json',
        	beforeSubmit: function(arr, $form, options) {
        		if ( $form.find('[name="id"]').val() ) {
        			options.url = '/specimen/type/' + $form.find('[name="id"]').val() + '/update';
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
	
	$('#add').on('click', function() {
		$form.find('.form-control').val('');
		$form.formValidation('resetForm');
		$('#typeModal').modal('show');
	});
	
	$status.find('label.btn').on('change', function() {
		var status = $(this).find('[name="status"]').val();
		$table.bootstrapTable('refresh', { query : { status : status } });
	});
	
	var requestParams = function(params) {
		return {
			limit: params.limit,
			offset: params.offset,
			search: params.search,
			status: $status.find('label.active input').val()
		}
	}
	var statusFormatter = function(value, row, index) {
		return row.status > 0 ? '<span class="label label-success">正常</span>' : '<span class="text-muted">失效</span>';
	}

	function operateFormatter(value, row, index) {
		var array = new Array();
		if (row.status) {
			array.push('<a class="edit btn btn-primary margin-r-5" href="#" data-placement="left" title="编辑标本类型">');
			array.push('<i class="fa fa-edit"></i> 编辑');
			array.push('</a> ');
		
			array.push('<a class="delete btn btn-danger" href="#" data-placement="left" title="删除标本类型">');
			array.push('<i class="fa fa-remove"></i> 删除');
			array.push('</a> ');
		} else {
			array.push('<a class="undelete btn btn-default" href="#" data-placement="left" title="恢复标本类型">');
			array.push('<i class="fa fa-undo"></i> 撤消删除');
			array.push('</a> ');
		}
		

		return array.join('');
	}
	window.operateEvents = {
		'click .edit' : function(e, value, row, index) {
			e.preventDefault();
			
			$.get('/specimen/type/' + row.id, function(data) {
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
				
				var type = data.data;
				$form.find('[name="id"]').val(type.id);
				$form.find('[name="name"]').val(type.name);
				$form.find('[name="typeHead"]').val(type.typeHead);
				$form.find('[name="caseTemplate"]').val(type.caseTemplate);
				$form.find('[name="remark"]').val(type.remark);
				
				$form.formValidation('resetForm');
				$('#typeModal').modal('show');
			});
		},
		'click .delete' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				content: '确认要删除该标本类型?',
				buttons: {
					confirm: {
						text: '<i class="fa fa-remove"></i> 删除',
		                btnClass: 'btn-danger',
		                action: function () {
		                	$.get('/specimen/type/' + row.id + '/remove/', function(data) {
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
		            cancel: { btnClass: 'btn-default' }
		        }
			});
		},
		'click .undelete' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				content: '确认要恢复该标本类型?',
				buttons: {
					confirm: {
						text: '撤消删除',
		                btnClass: 'btn-danger',
		                action: function () {
		                	$.get('/specimen/type/' + row.id + '/undelete/', function(data) {
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
		            cancel: { btnClass: 'btn-default' }
		        }
			});
		}
	};

	$table.bootstrapTable({
		url : '/specimen/type/list',
		search : false,
		showRefresh : true,
		showToggle : true,
		showColumns : true,
		detailView : false,
		striped : true,
		pagination : false,
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
			field : 'typeHead',
			title : '表示符号'
		}, {
			field : 'name',
			title : '类型名称'
		}, {
			field : 'caseTemplate',
			title : '分析模板'
		}, {
			field : 'gmtCreated',
			title : '创建时间'
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