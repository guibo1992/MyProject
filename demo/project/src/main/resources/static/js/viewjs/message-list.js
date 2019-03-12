
$(function() {
	'use strict';
	
	var $table = $('#table');
	var $startDate = $('#startDate'), $endDate = $('#endDate');
	
	
	$('#add').on('click', function() {
		location.href = "/message/add.html";
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
	
	var contentFormatter = function(value, row, index) {
		if (value.length > 50) {
			return value.substring(0, 50) + '...';
		}
		return value;
	}

	function operateFormatter(value, row, index) {
		var array = new Array();
		
		array.push('<a class="edit btn btn-primary margin-r-5" href="#" data-placement="left" title="编辑公告">');
		array.push('<i class="fa fa-edit"></i> 编辑');
		array.push('</a> ');
	
		array.push('<a class="delete btn btn-danger" href="#" data-placement="left" title="删除公告">');
		array.push('<i class="fa fa-remove"></i> 删除');
		array.push('</a> ');

		return array.join('');
	}
	
	window.operateEvents = {
		'click .view' : function(e, value, row, index) {
			window.location.href='/message/view/' + row.id;
		},
		'click .edit' : function(e, value, row, index) {
			e.preventDefault();
			location.href = "/message/" + row.id + ".html";
		},
		'click .delete' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				content: '确认要删除?',
				buttons: {
					confirm: {
						text: '<i class="fa fa-remove"></i> 删除',
		                btnClass: 'btn-danger',
		                action: function () {
		                	$.get('/message/' + row.id + '/remove', function(data) {
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
		            cancel: { text: '取消', btnClass: 'btn-default' }
		        }
			});
		}
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
			field: 'releaseDate',
			title : '发布日期'
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