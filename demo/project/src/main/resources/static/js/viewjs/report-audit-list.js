
$(function() {
	'use strict';
	
	var $table = $('#table'), $startDate = $('#startDate'), $endDate = $('#endDate'), $type = $('#type');
	var $status = $('#auditStatus');
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	$type.on('change', function() {
		$table.bootstrapTable('refresh');
	});
	$status.find('[name="auditStatus"]').on('change', function() {
		$table.bootstrapTable('refresh');
	});
		
	var requestParams = function(params) {
		return {
			limit: params.limit,
			offset: params.offset,
			search: params.search,
			typeId: $type.val(),
			auditStatus: $status.find('label.active [name="auditStatus"]').val()
		}
	}
	var statusFormatter = function(value, row, index) {
		if (row.auditStatus == 'WAIT_AUDIT') {
			return '<span class="text-primary">等待审核</span>';
		} else if ( row.auditStatus == 'WAIT_REAUDIT') {
			return '<span class="text-primary">等待重审</span>';
		} else if (row.auditStatus == 'AUDIT_PASSED') {
			return '<span class="text-success">审核完成</span>';
		} else {
			return '<span class="text-danger">审核驳回</span>';
		}
	}

	function operateFormatter(value, row, index) {
		if (row.auditStatus == 'AUDIT_PASSED') {
			return '<a class="info btn btn-success" href="#" title="报告详情"><i class="fa fa-file-pdf-o"></i> 报告详情</a>';
		} else if (row.auditStatus == 'WAIT_AUDIT') {
			return '<a class="audit btn btn-primary" href="#" title="报告审核"><i class="fa fa-edit"></i> 审核</a>';
		}
	}
	window.operateEvents = {
		'click .info' : function(e, value, row, index) {
			window.open('/analysis/report/info/' + row.id + '.html', 'report');
		},
		'click .audit' : function(e, value, row, index) {
			location.href = '/report/audit/' + row.id + '.html';
		}
	};
	
	$table.bootstrapTable({
		url : '/report/audit/list',
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
			field : 'name',
			title : '分析报告'
		}, {
			field : 'reportDate',
			title : '提交时间'
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
			field : 'specimenDate',
			title : '采样日期'
		}, {
			field : 'reportDate',
			title : '提交时间'
		}, {
			field : 'auditStatus',
			title : '状态',
			formatter: statusFormatter
		}, {
			events : operateEvents,
			title : '操作',
			formatter : operateFormatter
		} ]
	});
	
});