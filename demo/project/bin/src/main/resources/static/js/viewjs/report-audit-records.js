
$(function() {
	'use strict';
	
	var $table = $('#table'), $startDate = $('#startDate'), $endDate = $('#endDate'), $type = $('#type');
	var $status = $('#auditStatus');
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	$startDate.datepicker({
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		weekStart : 0,
		showOnFocus : true,
		keyboardNavigation : false,
		todayHighlight : true,
		autoclose : true,
		todayBtn : false,
		clearBtn : true,
		endDate: new Date()
	}).on('changeDate', function(e) {
		$('#endDate').datepicker('setStartDate', e.date);
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
		clearBtn : true,
		endDate: new Date()
	}).on('changeDate', function(e) {
		$('#startDate').datepicker('setEndDate', e.date);
		$table.bootstrapTable('refresh');
	});
	
//	$type.on('change', function() {
//		$table.bootstrapTable('refresh', { query : { typeId: $type.val() } });
//	});
	$status.find('[name="status"]').on('change', function() {
		var auditStatus = $(this).val();
		$table.bootstrapTable('refresh', { query : { auditStatus : auditStatus } });
	});
		
	var requestParams = function(params) {
		return {
			limit: params.limit,
			offset: params.offset,
			search: params.search,
			typeId: $type.val(),
			endDate : $endDate.find('input').val(),
			startDate : $startDate.find('input').val(),
			auditStatus: $status.find('.active > [name="auditStatus"]').val()
		}
	}
	var statusFormatter = function(value, row, index) {
		if (value) {
			return '<span class="text-success">审核通过</span>';
		} 
		return '<span class="text-danger">审核驳回</span>';
	}

	window.operateEvents = {
		'click .view' : function(e, value, row, index) {
			location.href = '/report/audit/' + row.id + '.html';
		},
	};
	
	$table.bootstrapTable({
		url : '/report/audit/records/list',
		search : true,
		showRefresh : true,
		showToggle : false,
		showColumns : false,
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
			field : 'auditTime',
			title : '审核时间'
		}, {
			field : 'reportName',
			title : '分析报告'
		}, {
			field : 'inspectionType',
			title : '类型',
		}, {
			field : 'specimenNo',
			title : '标本号',
		}, {
			field : 'hisId',
			title : '门诊号'
		}, {
			field : 'auditStatus',
			title : '审核结果',
			formatter: statusFormatter
		}, {
			field : 'rejectReason',
			title : '失败原因'
		} ]
	});
	
});