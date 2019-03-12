
$(function() {
	'use strict';
	
	var $table = $('#table'), $startDate = $('#startDate'), $endDate = $('#endDate'), $type = $('#type');
	
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
	
	$('#add').on('click', function() {
		location.href = "/specimen/add.html";
	});
	
	$type.on('change', function() {
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
		return value == "M" ? '男' : (value == "F" ? '女' : '其他');
	}
	
	var birthdateFormatter = function(value, row, index) {
		var today = moment(), day = moment(value);
		
		if (day.isValid()) {
			return (today.year() - day.year() - (today.month() - day.month() >= 0 ? 0 : 1)) + " 岁"; 
		}
		return null;
	}
	var statusFormatter = function(value, row, index) {
		if (row.reportStatus) {
			return '<span class="text-muted">已完成</span>';
		} else if (row.analysisStatus) {
			return '<span class="text-success">分析已完成</span>';
		} else if (row.cultureStatus) {
			return '<span class="text-primary">等待分析结果</span>';
		} else if (!row.cultureStatus && row.cultureList.length > 0) {
			return '<span class="text-primary">标本培养中</span>';
		} else {
			return '<span class="text-primary">等待培养</span>';
		}
	}
	
	function operateFormatter(value, row, index) {
		var array = new Array();
		
		// if (row.status) {
			if (!row.analysisStatus) {
				array.push('<a class="edit btn btn-primary" href="#" data-placement="left" title="编辑标本">');
				array.push('<i class="fa fa-edit"></i> 编辑');
				array.push('</a> ');
			
//				array.push('<a class="delete btn btn-danger" href="#" data-placement="left" title="删除标本">');
//				array.push('<i class="fa fa-remove"></i> 删除');
//				array.push('</a> ');
			}
//		} else {
//			array.push('<a class="undelete btn btn-default" href="#" data-placement="left" title="恢复标本">');
//			array.push('<i class="fa fa-undo"></i> 撤消删除');
//			array.push('</a> ');
//		}
		
		return array.join('');
	}
	window.operateEvents = {
		'click .edit' : function(e, value, row, index) {
			location.href = '/specimen/' + row.id + '.html';
		},
		'click .delete' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				content: '确认操作?',
				buttons: {
					confirm: {
						text: '<i class="fa fa-remove"></i> 删除',
		                btnClass: 'btn-danger',
		                action: function () {
		                	$.get('/specimen/' + row.id + '/remove/', function(data) {
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
		            cancel: { btnClass: 'btn-default' }
		        }
			});
		},
		'click .undelete' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				content: '确认恢复?',
				buttons: {
					confirm: {
						text: '<i class="fa fa-undo"></i> 恢复',
		                btnClass: 'btn-danger',
		                action: function () {
		                	$.get('/specimen/' + row.id + '/undo', function(data) {
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
		            cancel: { btnClass: 'btn-default' }
		        }
			});
		}
	};

	$table.bootstrapTable({
		url : '/specimen/list',
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
			title : '标本编号'
		}, {
			field : 'gmtCreate',
			title : '录入日期'
		}, {
			field : 'hisId',
			title : 'HIS ID'
		}, {
			field : 'patient.name',
			title : '姓名',
		}, {
			field : 'patient.sex',
			title : '性别',
			formatter : sexFormatter
		}, {
			field : 'patient.birthdate',
			title : '年龄',
			formatter: birthdateFormatter
		}, {
			// field : 'status',
			title : '状态',
			formatter: statusFormatter
		}, {
			events : operateEvents,
			title : '操作',
			formatter : operateFormatter
		} ]
	});
	
});