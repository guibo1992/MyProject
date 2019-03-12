
$(function() {
	'use strict';
	
	var $table = $('#table');
	var $type = $('#type'), $status = $('#status');
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	$('#addTemplate').on('click', function() {
		location.href = "/report/template/add.html";
	});
	
	$type.on('change', function() {
		$table.bootstrapTable('refresh', { query : { typeId: $type.val() } });
	});
	
	$status.find('label.btn').on('click', function() {
		var status = $(this).find('[name="status"]').val();
		$table.bootstrapTable('refresh', { query : { status : status } });
	});
	
	var requestParams = function(params) {
		return {
			limit: params.limit,
			offset: params.offset,
			search: params.search,
			typeId: $type.val(),
			status: $status.find('label.active > input').val()
		}
	}

	var statusFormatter = function(value, row, index) {
		return row.status > 0 ? '<span class="label label-success">正常</span>' : '<span class="text-muted">失效</span>';
	}
	
	function operateFormatter(value, row, index) {
		var array = new Array();
		
		if (row.status) {
			array.push('<a class="edit btn btn-primary margin-r-5" href="#" data-placement="left" title="编辑模板">');
			array.push('<i class="fa fa-edit"></i> 编辑');
			array.push('</a> ');
			
			array.push('<a class="remove btn btn-danger margin-r-5" href="#" data-placement="left" title="删除模板">');
			array.push('<i class="fa fa-remove"></i> 删除');
			array.push('</a> ');
		} else {
			array.push('<a class="undelete btn btn-warning" href="#" data-placement="left" title="撤消删除">');
			array.push('<i class="fa fa-undo"></i> 恢复');
			array.push('</a> ');
		}

		return array.join('');
	}
	window.operateEvents = {
		'click .edit' : function(e, value, row, index) {
			e.preventDefault();
			location.href = '/report/template/' + row.id + '.html';
		},
		'click .remove' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				content: '确认删除模板?',
				buttons: {
					confirm: {
						text: '<i class="fa fa-remove"></i> 删除',
		                btnClass: 'btn-danger',
		                action: function () {
		                	$.get('/report/template/' + row.id + '/remove/', function(data) {
								if (!data.success) {
									$.alert({ 
										title: '错误',
										content : data.message,
										buttons: { 
											close: { text: '关闭', close: { btnClass: 'btn-danger' } }
										}
									});
									return false;
								}
								$table.bootstrapTable('refresh');
							}, 'json');
	                    }
		            },
		            cancel: { text: "取消", btnClass: 'btn-default' }
		        }
			});
		},
		'click .undelete' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				content: '确认恢复模板?',
				buttons: {
					confirm: {
						text: '<i class="fa fa-undo"></i> 恢复',
		                btnClass: 'btn-warning',
		                action: function () {
		                	$.get('/report/template/' + row.id + '/undelete/', function(data) {
								if (!data.success) {
									$.alert({ 
										title: '错误',
										content : data.message,
										buttons: { 
											close: { text: '关闭', close: { btnClass: 'btn-danger' } }
										}
									});
									return false;
								}
								$table.bootstrapTable('refresh');
							}, 'json');
	                    }
		            },
		            cancel: { text: "取消", btnClass: 'btn-default' }
		        }
			});
		}
	};

	$table.bootstrapTable({
		url : '/report/template/list',
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
			title : '#',
		}, {
			field : 'type.name',
			title : '类型'
		}, {
			field : 'name',
			title : '模板名称'
		}, {
			field : 'status',
			title : '状态',
			formatter: statusFormatter
		}, {
			field : 'gmtModified',
			title : '更新时间'
		}, {
			events : operateEvents,
			formatter : operateFormatter
		} ]
	});
	
});