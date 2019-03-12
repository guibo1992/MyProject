
$(function() {
	'use strict';
	
	var $table = $('#table'), $modal = $('#documentModal');
	
	$modal.on('show.bs.modal', function (e) {
		 $(this).find('.form-control').val('');
		 $(this).find('[type="checkbox"]').prop('checked', false);
	});
	
	var filesizeFormatter = function(value, row, index) {
		return (value / 1024).toFixed(2) + 'KB';
	}
	var statusFormatter = function(value, row, index) {
		if (row.status) {
			return '<span class="text-muted">永久文件</span>';
		}
		return '<span class="text-info">临时文件</span>';
	}
	function operateFormatter(value, row, index) {
		var array = new Array();
		array.push('<a download class="btn btn-success" href="/transfer/file/download?file=', row.file);
		array.push('&filename=', row.name);
		array.push('"><i class="fa fa-download"></i> 下载</a>');
		
		if(!row.status){
			array.push('<a class="delete btn btn-danger margin-l-5" href="#"><i class="fa fa-remove"></i> 删除</a>');
		}
		return array.join('');
	}
	window.operateEvents = {
		'click .delete' : function(e, value, row, index) {
			e.preventDefault();
			
			$.confirm({
				icon: 'fa fa-question-circle',
				content: '确认删除?',
				buttons: {
					confirm: {
						text: '<i class="fa fa-remove"></i> 删除',
		                btnClass: 'btn-danger',
		                action: function () {
		                	$.get('/technical/document/' + row.id + '/remove', function(data) {
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
		url : '/technical/document/list',
		search : false,
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
		columns : [ {
			field : 'id',
			title : '#'
		}, {
			field : 'name',
			title : '文件名'
		}, {
			field : 'type',
			title : '文件类型'
		}, {
			field : 'filesize',
			title : '文件大小',
			formatter: filesizeFormatter
		}, {
			field : 'gmtCreate',
			title : '上传时间'
		}, {
			field: 'status',
			title : '状态',
			formatter: statusFormatter
		}, {
			events : operateEvents,
			title : '操作',
			class: 'col-xs-2',
			formatter : operateFormatter
		} ]
	});
	
	
	
	$('input[type="file"]').fileupload({
		url: "/transfer/document/upload",
		dataType: 'json',
		paramName: 'file',
		maxFileSize: 52428800,
		// acceptFileTypes: /(\.|\/)(jpe?g|png|pdf|docx?|xlsx?)$/i,
		formData: { param: Math.random() * 1000},
		messages: {
        	acceptFileTypes: 'File type not allowed',
            maxFileSize: 'File exceeds maximum allowed size of 50MB'
        },
        send: function(e, data) {
    		$(this).closest('.form-group').find(".progress").addClass('show');
    	},
    	progressall: function(e, data) {
    		let progress = parseInt(data.loaded / data.total * 100, 10);  
    		$(this).closest('.form-group').find(".progress-bar").css('width', progress + '%'); 
    	},
    	fail: function(e, data) {
    		$(this).closest('.form-group').find(".progress").removeClass('show');
    	},
    	always: function (e, data) {
    		var $progress = $(this).closest('.form-group').find(".progress");
    		setTimeout(function() { 
    			$progress.removeClass('show').find(".progress-bar").css('width', '0%'); 
    		}, 300);
    	},
    	processfail: function (e, data) {
    		var file = data.files[0];
			$.alert({
				icon: 'fa fa-warning',
				title: '错误',
				content: file.error + ': ' + file.name,
				buttons: { close: { text: '关闭', btnClass: 'btn-danger' } }
			});
        },
        done: function(e, data) {
    		var result = data.result;
    		if (!result.success) {
    			$.alert({
    				icon: 'fa fa-warning',
    				title: '错误',
    				content: result.message,
    				buttons: {
    					close: { text: '关闭', btnClass: 'btn-danger' }
    				}
    			});
    			return false;
    		}
    		
    		$('#name').val(result.data.filename);
    		$('#file').val(result.data.file);
    		$('#type').val(result.data.type);
    		$('#filesize').val(result.data.length);
    	}
	}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
	
	
	$('[type="submit"]').on('click', function() {
		if (!$('#file').val()) {
			$.alert({
				icon: 'fa fa-warning',
				title: '错误',
				content: "请上传文档!",
				buttons: {
					close: { text: '关闭', btnClass: 'btn-danger' }
				}
			});
			return false;
		}
		
		var params = {
			name: $('#name').val(),
			file: $('#file').val(),
			type: $('#type').val(),
			filesize: $('#filesize').val(),
			status: $('#status').prop('checked')
		}
		$.post('/technical/document/add', params, function(data) {
			if (!data.success) {
    			$.alert({
    				icon: 'fa fa-warning',
    				title: '错误',
    				content: result.message,
    				buttons: {
    					close: { text: '关闭', btnClass: 'btn-danger' }
    				}
    			});
    			return false;
    		}
			$modal.modal('hide');
			$table.bootstrapTable('refresh');
		});
	});
	
	
	
});