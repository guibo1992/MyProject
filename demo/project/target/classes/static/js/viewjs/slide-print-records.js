
$(function() {
	'use strict';
	
	var $table = $('#table');
	var $startDate = $('#startDate'), $endDate = $('#endDate'), $type = $('#type');
	
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
	
	$type.change(function() {
		$table.bootstrapTable('refresh');
	});
		
	var requestParams = function(params) {
		return {
			limit: params.limit,
			offset: params.offset,
			search: params.search,
			typeId: $type.val(),
			endDate: $endDate.find('input').val(),
			startDate: $startDate.find('input').val()
		}
	}
	var sexFormatter = function(value, row, index) {
		return value == "M" ? '男' : (value == 'F' ? '女' : '其他');
	}
	
	var BirthdateFormatter = function(value, row, index) {
		var today = moment(), day = moment(value);
		
		if (day.isValid()) {
			return (today.year() - day.year() - (today.month() - day.month() >= 0 ? 0 : 1)) + ""; 
		}
		return null;
	}
	var printCountFormatter = function(value, row, index) {
		return row.printLine == 'B' ? value + ' x 2' : value;
	}

	function operateFormatter(value, row, index) {
		var array = new Array();
		array.push('<a class="print btn btn-sm btn-success margin-r-5" href="#" data-placement="left" title="重新打印玻载片">');
		array.push('<i class="fa fa-print"></i> 重新打印');
		array.push('</a> ');
		
		return array.join('');
	}

	window.operateEvents = {
		'click .print' : function(e, value, row, index) {
			e.preventDefault();
			
			$.get('/slide/print/' + row.specimenId + '/print', function(data) {
				if (data.success) {
					$.alert({ 
						title: '提示',
						content : "共打印标本玻片数: " + data.data + '片',
						buttons: { 
							ok: { text: '确认', btnClass: 'btn-success' } 
						}
					});
					return false;
				}
				$.alert({ 
					title: '<i class="fa fa-warning"></i> 错误',
					content : data.message,
					buttons: { 
						close: { text: '关闭', btnClass: 'btn-danger' } 
					}
				});
			});
			
		}
	};

	$table.bootstrapTable({
		url : '/slide/print/records/list',
		search : true,
		showRefresh : true,
		showToggle : true,
		showColumns : true,
		detailView : false,
		striped : true,
		pagination : true,
		paginationLoop : false,
		sidePagination : 'server',
		toolbar: '#toolbar',
		queryParams : requestParams,
		columns : [ {
			field : 'printTime',
			title : '打印时间'
		}, {
			field : 'hisId',
			title : '门诊号'
		}, {
			field : 'type.name',
			title : '标本类型'
		}, {
			field : 'specimenNo',
			title : '标本号'
		}, {
			field : 'patient.name',
			title : '姓名',
		}, {
			field : 'printCount',
			title : '打印数量'
		}, {
			title: '操作',
			events : operateEvents,
			formatter : operateFormatter
		} ]
	});
	
});