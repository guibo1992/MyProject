$(function() {
	'use strict';
	
	var $table = $('#table');
	
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
	
	var requestParams = function(params) {
		return {
			limit : params.limit,
			offset : params.offset,
			search : params.search,
			startDate : $startDate.val(),
			endDate : $endDate.val()
		}
	}

	$table.bootstrapTable({
		url : '/reagent/stock/in/list',
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
		columns : [{
			field : 'batchNo',
			title : '入库批次'
		}, {
			field : 'entryTime',
			title : '入库时间'
		}, {
			field : 'reagent',
			title : '试剂名称',
			class: 'col-xs-2'
		}, {
			field : 'quantity',
			title : '入库数量'
		}, {
			field : 'surplusQuantity',
			title : '剩余库存量'
		}, {
			field : 'remark',
			title : '备注',
			class: 'col-xs-2'
		}, {
			field : 'operator',
			title : '操作用户'
		}]
	});
	
});