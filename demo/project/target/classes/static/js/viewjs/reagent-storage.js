$(function() {
	'use strict';
	
	var $table = $('#table'), $inForm = $('#reagentInForm'), $outForm = $('#reagentOutForm');
	
	var $startDate = $('#startDate'), $endDate = $('#endDate');
	$startDate.datepicker({
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		weekStart : 0,
		showOnFocus : true,
		keyboardNavigation : false,
		todayHighlight : true,
		autoclose : true,
		todayBtn : false,
		clearBtn : true
	}).on('changeDate', function(e) {
		$endDate.datepicker('setStartDate', e.date);
		$table.bootstrapTable('refresh');
	});
	$endDate.datepicker({
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		weekStart : 0,
		showOnFocus : true,
		keyboardNavigation : false,
		todayHighlight : true,
		autoclose : true,
		todayBtn : false,
		clearBtn : true
	}).on('changeDate', function(e) {
		$startDate.datepicker('setEndDate', e.date);
		$table.bootstrapTable('refresh');
	});
	
	$inForm.formValidation({
		framework : 'bootstrap',
		excluded : ':disabled',
		message : 'This value is not valid',
		icon : {
			valid : 'fa fa-check',
			invalid : 'fa fa-times',
			validating : 'fa fa-refresh'
		},
		fields : {
            quantity: {
                validators: { 
                	notEmpty: { message: 'The value is required' },
                	integer: {
                        message: 'The value is not an integer',
                        thousandsSeparator: ''
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
		var reagentId = $(e.target).find('[name="reagentId"]').val();
		
		$(e.target).ajaxSubmit({
        	url: '/reagent/' + reagentId + '/put-in',
        	type: 'post',
        	dataType: 'json',
    	    success: function(data) {
    	    	$inForm.formValidation('resetForm');
    	    	
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
	
	$outForm.formValidation({
		framework : 'bootstrap',
		excluded : ':disabled',
		message : 'This value is not valid',
		icon : {
			valid : 'fa fa-check',
			invalid : 'fa fa-times',
			validating : 'fa fa-refresh'
		},
		fields : {
            quantity: {
                validators: { 
                	notEmpty: { message: 'The value is required' },
                	integer: {
                        message: 'The value is not an integer',
                        thousandsSeparator: ''
                    },
                    callback: {
                    	callback: function(value, validator, $field) {
                    		var floor = $outForm.find('[name="stocks"]').val();
                    		
                    	    if (parseInt(value) > parseInt(floor)) {
                    	        return {
                    	            valid: false,
                    	            message: '领用数量不能大于库存数量'
                    	        }
                    	    }

                    	   return true;
                    	}
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
		var reagentId = $(e.target).find('[name="reagentId"]').val();
		
		$(e.target).ajaxSubmit({
        	url: '/reagent/' + reagentId + '/put-out',
        	type: 'post',
        	dataType: 'json',
    	    success: function(data) {
    	    	$outForm.formValidation('resetForm');
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
    	    	
    	    	var warnStock = $outForm.find('[name="warnStock"]').val();
    	    	if (data.datas.surplus <= warnStock) {
    	    		$.alert({
						title : '提示',
						content : '该试剂库存量已到达预警值,请尽快采购!',
						buttons : {
							close : { text : '关闭', btnClass : 'btn-warning' }
						}
					});
    	    	}
    	       
    	    	$table.bootstrapTable('refresh');
    	    	$('[data-dismiss="modal"]').trigger('click');
    	    }
        }); 
	});
	
		
	var requestParams = function(params) {
		return {
			limit : params.limit,
			offset : params.offset,
			search : params.search,
			startDate : $startDate.val(),
			endDate : $endDate.val(),
		}
	}
	var stocksFormatter = function(value, row, index) {
		return (value <= row.warnStock ? '<span class="text-danger"><i class="fa fa-warning"></i> ' : '<span>') + value + '</span>'
	}
	function operateFormatter(value, row, index) {
		var array = new Array();
		
		array.push('<a class="put-in btn btn-primary margin-r-5" href="#" data-placement="left" title="试剂入库"> 入库 </a>');
		array.push('<a class="put-out btn btn-warning" href="#" data-placement="left" title="试剂出库"> 领用 </a>');
		
		return array.join('');
	}

	window.operateEvents = {
		'click .put-in' : function(e, value, row, index) {
			e.preventDefault();
			
			$inForm.find('.form-control').val('');
			$inForm.find('[name="reagentId"]').val(row.id);
			$inForm.find('[name="name"]').val(row.name);
			$inForm.formValidation('resetForm');
			$('#reagentInModal').modal('show');
		},
		'click .put-out' : function(e, value, row, index) {
			e.preventDefault();
			
			$outForm.find('.form-control').val('');
			$outForm.find('[name="reagentId"]').val(row.id);
			$outForm.find('[name="name"]').val(row.name);
			$outForm.find('[name="stocks"]').val(row.stocks);
			$outForm.find('[name="warnStock"]').val(row.warnStock);
			$outForm.formValidation('resetForm');
			$('#reagentOutModal').modal('show');
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
			title : '数量单位'
		}, {
			field : 'stocks',
			title : '库存量',
			formatter: stocksFormatter
		}, {
			field : 'warnStock',
			title : '预警库存'
		}, {
			field : 'storeCondition',
			title : '存储方式',
			class: 'col-xs-2'
		}, {
			field : 'lastPutInTime',
			title : '最后入库时间'
		},  {
			field : 'lastPutOutTime',
			title : '最后出库时间'
		}, {
			events : operateEvents,
			formatter : operateFormatter
		} ]
	});
	
});