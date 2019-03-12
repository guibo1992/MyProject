
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
		clearBtn : true
	}).on('changeDate', function(e) {
		$('#startDate').datepicker('setEndDate', e.date);
		$table.bootstrapTable('refresh', {
			query : {
				endDate : $endDate.val(),
				startDate : $startDate.val()
			}
		});
	});
	
	var requestParams = function(params) {
		return {
			limit: params.limit,
			offset: params.offset,
			search: params.search,
			endDate : $endDate.val(),
			startDate : $startDate.val()
		}
	}

	function operateFormatter(value, row, index) {
		return '<a class="btn btn-success view" href="#"> 查看 </a>';
	}
	
	window.operateEvents = {
		'click .view' : function(e, value, row, index) {
			window.location.href = '/message/' +  row.id + '/view.html';
		},
	};

	$table.bootstrapTable({
		url : '/message/list',
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
			field : 'title',
			title : '标题'
		}, {
			field : 'brief',
			title : '内容'
		}, {
			field: 'releaseDate',
			title : '发布日期'
		}, {
			field : 'gmtModified',
			title : '更新时间'
		}, {
			events : operateEvents,
			formatter : operateFormatter
		} ]
	});
	
});