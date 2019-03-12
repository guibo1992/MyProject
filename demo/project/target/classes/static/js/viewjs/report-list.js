
$(function() {
	'use strict';
	
	var $table = $('#table'), $startDate = $('#startDate'), $endDate = $('#endDate'), $type = $('#type');
	var $status = $('#reportStatus');
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	$status.find('label.btn').on('change', function() {
		var reportStatus = $(this).find('[name="reportStatus"]').val();
		$table.bootstrapTable('refresh', { query : { auditStatus : reportStatus } });
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
		$table.bootstrapTable('refresh', {
			query : {
				endDate : $endDate.val(),
				startDate : $startDate.val()
			}
		});
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
		$table.bootstrapTable('refresh', {
			query : {
				endDate : $endDate.val(),
				startDate : $startDate.val()
			}
		});
	});
	
	$('#add').on('click', function() {
		location.href = "/specimen/add.html";
	});
	
	$type.on('change', function() {
		$table.bootstrapTable('refresh', { query : { typeId: $type.val() } });
	});
		
	var requestParams = function(params) {
		return {
			limit: params.limit,
			offset: params.offset,
			search: params.search,
			typeId: $type.val(),
			endDate: $endDate.val(),
			startDate: $startDate.val(),
			auditStatus: $status.find('label.active input').val()
		}
	}
	var sexFormatter = function(value, row, index) {
		return value == "1" ? '男' : (value == 2 ? '女' : '其他');
	}
	
	var reportStatusFormatter = function(value, row, index) {
		return value == "0" ? '待审核' : (value == 1 ? '已通过' : '待修改');
	}
	
	var BirthdateFormatter = function(value, row, index) {
		var today = moment(), day = moment(value);
		
		if (day.isValid()) {
			return (today.year() - day.year() - (today.month() - day.month() >= 0 ? 0 : 1)) + " 岁"; 
		}
		return null;
	}
	var statusFormatter = function(value, row, index) {
		if (row.auditStatus == 'WAIT_AUDIT') {
			return '<span class="text-primary">等待审核</span>';
		}  else if (row.auditStatus == 'AUDIT_PASSED') {
			return '<span class="text-success">审核完成</span>';
		} else {
			return '<span class="text-danger">审核驳回</span>';
		}
	}

	function operateFormatter(value, row, index) {
		var array = new Array();
		
		if (row.auditStatus == 'AUDIT_PASSED') {
			array.push('<a class="fastPrint btn btn-success margin-r-5" href="#" title="快速打印报告"> <i class="fa fa-print"></i> 打印报告 </a>');
			array.push('<a class="view btn btn-default" href="#" target="_blank" title="报告详情"><i class="fa fa-file-pdf-o"></i> 报告详情</a>');
		} else if (row.auditStatus == 'AUDIT_REJECT') {
			array.push('<a class="resubmit btn btn-warning" href="#" title="重新提交审核"><i class="fa fa-repeat"></i> 重新提交</a>')
		}
			
		return array.join('');
	}
	window.operateEvents = {
		'click .fastPrint' : function(e, value, row, index) {
			e.preventDefault();
			
			$.ajax({
	    		url: '/analysis/report/' + row.id + '/print',
	    		type: "post", 
	            dataType: "json",
	            traditional: true,
	            contentType: 'application/json;charset=UTF-8',
	            success : function (data) {
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
	            	$('iframe').remove();
	            	var $frame = $('<iframe style="display:none" ></iframe>').appendTo($('body'));
					$frame.on("load", function() { 
						$(this)[0].contentWindow.print(); 
					});
					$frame.attr('src', "/transfer/pdf/print-stream?file=" + data.data)
	            }
	        });
		},
		'click .view' : function(e, value, row, index) {
			window.open('/analysis/report/info/' + row.id + '.html', 'report');
		},
		'click .resubmit' : function(e, value, row, index) {
			location.href = '/analysis/report/' + row.id + '/edit.html';
		}
	};
	
	$table.bootstrapTable({
		url : '/analysis/report/list',
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
			field : 'specimenNo',
			title : '标本号',
		}, {
			field : 'name',
			title : '报告名称'
		}, {
			field : 'hisId',
			title : '门诊号'
		}, {
			field : 'inspectionType',
			title : '样本类型',
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