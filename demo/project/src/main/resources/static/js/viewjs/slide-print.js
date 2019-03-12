
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
	//$startDate.datepicker('setDate', new Date());	
	
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
	
	$type.change(function() {
		$table.bootstrapTable('refresh');
	});
	
	var lineTypeFormatter = function(value, row, index) {
		return value == 'B' ? 'AB' : value;
	}
	var sexFormatter = function(value, row, index) {
		return value == "M" ? '男' : (value == 'F' ? '女' : '其他');
	}
	
	var BirthdateFormatter = function(value, row, index) {
		var today = moment(), day = moment(value);
		
		if (day.isValid()) {
			return (today.year() - day.year() - (today.month() - day.month() >= 0 ? 0 : 1)) + " 岁"; 
		}
		return null;
	}
	var printCountFormatter = function(value, row, index) {
		return row.printLine == 'B' ? value + ' x 2' : value;
	}

	function operateFormatter(value, row, index) {
		return '<a class="print btn btn-primary" href="javascript:;" title="打印玻载片"><i class="fa fa-print"></i> 打印</a>';
	}

	window.operateEvents = {
		'click .print' : function(e, value, row, index) {
			e.preventDefault();
			
			$.get('/slide/print/' + row.id + '/print', function(data) {
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
		url : '/specimen/list',
		search : false,
		showRefresh : false,
		showToggle : false,
		showColumns : false,
		detailView : false,
		striped : true,
		pagination : true,
		paginationLoop : false,
		sidePagination : 'server',
		toolbar: '#toolbar',
		singleSelect: false,
		queryParams : requestParams,
		clickToSelect: true,
		pageSize : 20,
		pageList: [20, 30, 50],
		columns : [ {
			align: 'center',
            valign: 'middle',
            checkbox: true
		}, {
			field : 'hisId',
			title : '门诊号'
		}, {
			field : 'specimenNo',
			title : '标本号'
		},{
			field : 'patient.name',
			title : '姓名',
		}, {
			field : 'patient.sex',
			title : '性别',
			formatter : sexFormatter
		}, {
			field : 'patient.birthdate',
			title : '年龄',
			formatter: BirthdateFormatter 
		}, {
			field : 'lineType',
			title : 'A/B线',
			formatter : lineTypeFormatter
		}, {
			field : 'linePrintCount',
			title : '打印数量',
			formatter: printCountFormatter
		}, {
			field : 'gmtCreate',
			title : '创建日期'
		}, {
			title: '操作',
			clickToSelect: false,
			events: operateEvents,
			formatter: operateFormatter
		} ],
		onPostBody: function() {
			$(table).find("input:checkbox").each(
				function(i) {
					var $check = $(this), $td = $check.parent();
					if ($check.attr("id") && $check.next("label")) {
						return;
					}
					var name = $check.attr("name"), id = name + "-" + i;
					$td.append($('<div class="checkbox"></div>').append($check.attr("id", id)).append($('<label for="' + id + '"></label>')));
				}
			);
		}
	});
	
	$('#print').on('click', function() {
		var selectArray = $table.bootstrapTable('getSelections');
		if (selectArray.length < 1) {
			$.alert({ 
				title: '<i class="fa fa-warning"></i> 警告',
				content : '请选择需要打印的标本!',
				buttons: { close: { text: '关闭', btnClass: 'btn-danger' } }
			});
			return false;
		}

		var ids = [];
		$.each(selectArray, function(i, item) {
			ids.push(item.id);
		}); 

		var param = $.param({ids: ids});
		$.post('/slide/print/batch', param, function(data) {
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

	});
	
});