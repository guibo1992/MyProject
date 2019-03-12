$(function() {
	'use strict';
	
	var $table = $('#table'), $form = $('#reagentForm');
	
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
			name: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 50,
                        message: 'The value must be less than 50 characters long'
                    }
                }
			},
            warnStock: {
                validators: { 
                	notEmpty: { message: 'The value is required' },
                	integer: {
                        message: 'The value is not an integer',
                        thousandsSeparator: ''
                    },
                    between: {
                        min: 1,
                        max: 100000,
                        message: 'The value must be between 1 and 100000'
                    }
                }
            },
            qtyUnit: {
            	validators: { 
            		notEmpty: { message: 'The value is required' },
            		stringLength: {
            			min: 1,
                        max: 6,
                        message: 'The value must be more than 1 and less than 6 characters long'
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
        	url: '/reagent/add',
        	type: 'post',
        	dataType: 'json',
        	beforeSubmit: function(arr, $form, options) {
        		if ( $form.find('[name="id"]').val() ) {
        			options.url = '/reagent/' + $form.find('[name="id"]').val() + '/update';
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
	
	
	$('#addReagent').on('click', function() {
		$form.find('.form-control').val('');
		$form.formValidation('resetForm');
		$('#reagentModal').modal('show');
	});
	
		
	var requestParams = function(params) {
		return {
			limit : params.limit,
			offset : params.offset,
			search : params.search,
		}
	}
	function operateFormatter(value, row, index) {
		var array = new Array();
		array.push('<a class="edit btn btn-primary btn-sm" href="#" data-placement="left" title="编辑">');
		array.push('<i class="fa fa-edit"></i> 编辑');
		array.push('</a> ');
		
		return array.join('');
	}

	window.operateEvents = {
		'click .edit' : function(e, value, row, index) {
			e.preventDefault();
			
			$.get('/reagent/' + row.id, function(data) {
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
				var reagent = data.data;
				$form.find('[name="id"]').val(reagent.id);
				$form.find('[name="name"]').val(reagent.name);
				$form.find('[name="qtyUnit"]').val(reagent.qtyUnit);
				$form.find('[name="type"]').val(reagent.type);
				$form.find('[name="spec"]').val(reagent.spec);
				$form.find('[name="warnStock"]').val(reagent.warnStock);
				$form.find('[name="storeCondition"]').val(reagent.storeCondition);
				$form.find('[name="remark"]').val(reagent.remark);
				
				$form.formValidation('resetForm');
				$('#reagentModal').modal('show');
			});
		}
	}

	$table.bootstrapTable({
		url : '/reagent/list',
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
			field : 'name',
			title : '名称',
			class: 'col-xs-2'
		}, {
			field : 'qtyUnit',
			title : '单位'
		}, {
			field : 'type',
			title : '类型'
		}, {
			field : 'spec',
			title : '规格'
		}, {
			field : 'warnStock',
			title : '预警库存'
		}, {
			field : 'storeCondition',
			title : '存储方式',
			class: 'col-xs-2'
		}, {
			field : 'remark',
			title : '备注',
			class: 'col-xs-2'
		}, {
			field : 'gmtCreated',
			title : '创建时间'
		}, {
			events : operateEvents,
			formatter : operateFormatter
		} ]
	});
	
});