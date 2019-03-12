

$(function() {
	'use strict';
	
	var $table = $('#table'), $modal = $('#templateModal'), $resultModal = $('#resultModal');
	var $status = $('#status'), $type = $('#type');
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	$status.find('label.btn').on('click', function() {
		var status = $(this).find('[name="status"]').val();
		$table.bootstrapTable('refresh', { query : { analysisStatus : status } });
	});
	$type.on('change', function() {
		$table.bootstrapTable('refresh', { query : { type: $type.val() } });
	});
	
		
	var requestParams = function(params) {
		return {
			limit: params.limit,
			offset: params.offset,
			search: params.search,
			typeId: $type.val(),
			analysisStatus: $status.find('label.active > input').val()
		}
	}
	
	var statusFormatter = function(value, row, index) {
		if (value && row.reportStatus) {
			return '<span class="text-muted">已出报告</span>';
		} else if (value) {
			return '<span class="text-success">分析已完成</span>';
		} else  {
			return '<span class="text-muted">等待分析结果</span>';
		} 
	}
	
	var resultFormatter = function(value, row, index) {
		if (row.analysisStatus) {
			return '<a class="result text-success" href="#" title="查看分析结果"> <i class="fa fa-search"></i> 查看结果</a>';
		}
	}
	var operateFormatter = function(value, row, index) {
		if (!row.analysisStatus || row.reportStatus) {
			return '';
		}
		return '<a class="genReport btn btn-primary margin-l-5" href="#" title="生成分析报告"><i class="fa fa-file-pdf-o"></i>  生成报告</a>';
	}
	window.operateEvents = {
		'click .result' : function(e, value, row, index) {
			e.preventDefault();
			
			$.get('/karyotype/analysis/' + row.id + '/result', function(data) {
				if (!data.success) {
					$.alert({
						title : '错误',
						content : '分析结果查询失败!',
						buttons : {
							close : { text : '关闭', btnClass : 'btn-danger' }
						}
					});
					return false;
				}
				
				$resultModal.find('[name="analysisResult"]').text(data.data.analysisResult);
				$resultModal.find('[name="analysisMetImg"]').attr('src', _static + data.data.analysisMetImg)
				if (data.data.analysisKarImg) {
					$resultModal.find('[name="analysisKarImg"]').attr('src', _static + data.data.analysisKarImg)
				}
				$resultModal.modal('show');
			});
		},
		'click .genReport' : function(e, value, row, index) {
			e.preventDefault();
			
			$.get('/report/template/' + row.typeId + '/list', function(data) {
				if (!data.success) {
					$.alert({
						title : '提示',
						content : data.message,
						buttons : {
							close : { text : '关闭', btnClass : 'btn-danger' }
						}
					});
					return false;
				}
				
				var $templateId = $('#templateId').attr('data-id', row.id).empty();
				$.each(data.data, function(i, t) {
					$templateId.append('<option value="' + t.id + '">' + t.name + '</option>');
				});
				$templateId.trigger('chosen:updated');
				$modal.modal('show');
			});
		}
	};

	$table.bootstrapTable({
		url : '/karyotype/analysis/list',
		search : true,
		showRefresh : true,
		showToggle : true,
		showColumns : true,
		detailView : false,
		striped : true,
		pagination : true,
		paginationLoop : false,
		sidePagination : 'server',
		pageSize : 10,
		pageList: [10, 20, 50],
		toolbar : '#toolbar',
		queryParams : requestParams,
		columns : [ {
			field : 'specimenNo',
			title : '标本号',
		}, {
			field : 'hisId',
			title : '门诊号'
		}, {
			field : 'gmtCreate',
			title : '录入时间'
		}, {
			field : 'cultureCompletedTime',
			title : '培养完成时间',
		}, {
			field : 'analysisCompletedTime',
			title : '分析完成时间'
		}, {
			field : 'analysisStatus',
			title : '状态',
			formatter: statusFormatter
		}, {
			title: '分析结果',
			events: operateEvents,
			formatter: resultFormatter
		}, {
			events : operateEvents,
			title : '操作',
			formatter : operateFormatter
		} ]
	});	
	
	$modal.find('[type="submit"]').on('click', function() {
		var $templateId = $('#templateId');
		
		if (!$templateId.val()) {
			$.alert({
				title : '警告',
				content : "请选择一个有效的报告模板!",
				buttons : {
					close : { text : '确定', btnClass : 'btn-warning' }
				}
			});
			return false;
		}
		location.href = '/karyotype/analysis/' + $templateId.attr('data-id') + '/tmpl-' + $templateId.val() + '/report.html';
	});
	
});