$(function() {
	'use strict';
	
	var $table = $('#table'), $status = $('#status'), $type = $('#type');
	var $form = $('#processForm'), $batchForm = $('#batchForm');

	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	$form.formValidation({
		framework : 'bootstrap',
		excluded : ':disabled',
		message : 'This value is not valid',
		icon : {
			valid : 'fa fa-check',
			invalid : 'fa fa-times',
			validating : 'fa fa-refresh'
		},
		fields : {
			processTime: {
                validators: {
                	notEmpty: { message: 'The value is required.' },
                	date: {
                        format: 'YYYY-MM-DD',
                        message: 'The date is not valid'
                    }
                }
            }
		}
	}).on('err.field.fv', function(e, data) {
    	data.fv.disableSubmitButtons(false);
    	
    }).on('success.field.fv', function(e, data) {
        data.element.parents('.has-success').removeClass('has-success');
        data.element.data('fv.icon').hide();
        
    }).on('success.form.fv', function(e) {
		e.preventDefault();
		
		$(e.target).ajaxSubmit({
        	url: '/specimen/culture/add',
        	type: 'post',
        	dataType: 'json',
    	    success: function(data) {
    	    	if (!data.success) {
    	    		$.alert({
						title : '错误',
						content : data.message,
						buttons : {
							close : { text : '关闭', btnClass : 'btn-warning' }
						}
					});
    	    	   return false;
    	    	}
    	       
    	    	$.get('/specimen/culture/' + $form.find('[name="specimenId"]').val(), function(data) {
    	    		if (!data.success) {
    	    			$table.bootstrapTable('refresh');
    	    			return false;
    	    		}
    	    		
    	    		var index = $form.find('[type="submit"]').attr('data-index');
    	    		$table.bootstrapTable('updateRow', {index: index, row: data.data});
    	    	});
    	    	$('[data-dismiss="modal"]').trigger('click');
    	    }
        }); 
	}).find('.date').datepicker({
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		weekStart : 0,
		showOnFocus : true,
		keyboardNavigation : false,
		todayHighlight : true,
		autoclose : true,
		todayBtn : 'linked',
		endDate: new Date()
	}).on('changeDate', function(e) {
		//$(this).datepicker('setEndDate', new Date());
		$form.data('formValidation').revalidateField('processTime');
	});
	
	$batchForm.formValidation({
		framework : 'bootstrap',
		excluded : ':disabled',
		message : 'This value is not valid',
		icon : {
			valid : 'fa fa-check',
			invalid : 'fa fa-times',
			validating : 'fa fa-refresh'
		},
		fields : {
			typeId: {
				validators: {
                	notEmpty: { message: 'The value is required.' }
				}
			},
			processStep: {
				validators: {
                	notEmpty: { message: 'The value is required.' }
				}
			},
			specimenNos: {
				validators: {
                	notEmpty: { message: 'The value is required.' }
				}
			},
			processTime: {
                validators: {
                	notEmpty: { message: 'The value is required.' },
                	date: {
                        format: 'YYYY-MM-DD',
                        message: 'The date is not valid'
                    }
                }
            }
		}
	}).on('err.field.fv', function(e, data) {
    	data.fv.disableSubmitButtons(false);
    	
    }).on('success.field.fv', function(e, data) {
        data.element.parents('.has-success').removeClass('has-success');
        data.element.data('fv.icon').hide();
        
    }).on('success.form.fv', function(e) {
		e.preventDefault();
		
		$(e.target).ajaxSubmit({
        	url: '/specimen/culture/batch/process',
        	type: 'post',
        	dataType: 'json',
    	    success: function(data) {
    	    	if (!data.success) {
    	    		$.alert({
						title : '错误',
						content : data.message,
						buttons : {
							close : { text : '关闭', btnClass : 'btn-warning' }
						}
					});
    	    	   return false;
    	    	}
    	    	$table.bootstrapTable('refresh');
    	    	$('[data-dismiss="modal"]').trigger('click');
    	    }
        }); 
	}).find('.date').datepicker({
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		weekStart : 0,
		showOnFocus : true,
		keyboardNavigation : false,
		todayHighlight : true,
		autoclose : true,
		todayBtn : 'linked',
		endDate: new Date()
	}).on('changeDate', function(e) {
		// $(this).datepicker('setEndDate', new Date());
		$batchForm.data('formValidation').revalidateField('processTime');
	});
	
	$batchForm.find('[name="typeId"]').on('change', function() {
		var $this = $(this), index = $batchForm.find('[name="processOrder"]').val();
		if (!index) {
			return false;
		}
		
		// 同步刷新列表
		// $type.val($this.val()).trigger('change');
		// $type.trigger('chosen:updated');
		
		var $startIds = $batchForm.find('[name="startId"]').empty(), $endIds = $batchForm.find('[name="endId"]').empty();
		$batchForm.find('.chosen-select').trigger('chosen:updated');
		
		$.get('/specimen/culture/process/list', {typeId: $this.val(), index: index}, function(data) {
			$.each(data, function(i, s) {
				$startIds.append('<option value="' + s + '">' + s + '</option>');
			});
			$.each(data, function(i, s) {
				$endIds.append('<option value="' + s + '">' + s + '</option>');
			});
			$batchForm.find('.chosen-select').trigger('chosen:updated');
		});
	}).trigger('change');
	
	$batchForm.find('[name="processOrder"]').on('change', function() {
		var $this = $(this), typeId = $batchForm.find('[name="typeId"]').val();
		$batchForm.find('[name="processStep"]').val($this.find('option:selected').text());
		$batchForm.data('formValidation').revalidateField('processStep');
		
		var lastVal = $this.find('option:last').val();
		$batchForm.find('[name="completedStatus"]').val($this.val() == lastVal ? '1' : '0');
		
		var $startIds = $batchForm.find('[name="startId"]').empty(), $endIds = $batchForm.find('[name="endId"]').empty();
		$batchForm.find('.chosen-select').trigger('chosen:updated');
		
		$.get('/specimen/culture/process/list', {typeId: typeId, index: $this.val()}, function(data) {
			if (!data || data.length < 1) {
				return false;
			}
			$.each(data, function(i, s) {
				$startIds.append('<option value="' + s + '">' + s + '</option>');
			});
			$.each(data, function(i, s) {
				$endIds.append('<option value="' + s + '">' + s + '</option>');
			});
			$batchForm.find('[name="startId"],[name="endId"]').trigger('change');
			$batchForm.find('.chosen-select').trigger('chosen:updated');
		});
	});
	
	$batchForm.find('[name="startId"]').on('change', function() {
		var $this = $(this), $endSelect = $batchForm.find('[name="endId"]');
		$endSelect.find('option').prop('disabled', false);
		
		var startIndex = $this.find('option:selected').index(), endIndex = $endSelect.find('option:selected').index();
		$endSelect.find('option:lt(' + startIndex + ')').prop('disabled', true);
		
		if (endIndex < startIndex) {
			$endSelect[0].selectedIndex = startIndex;
		}
		$endSelect.trigger('chosen:updated');
		
		var nums = '';
		$this.find('option').each(function(i) {
			if (i >= startIndex && i <= endIndex) {
				if (nums.length > 0) {
					nums += ',';
				}
				nums += $(this).attr('value');
			}
		});
		
		$batchForm.find('[name="specimenNos"]').val(nums);
		$batchForm.data('formValidation').revalidateField('specimenNos');
	});
	
	$batchForm.find('[name="endId"]').on('change', function() {
		var $this = $(this), $startSelect = $batchForm.find('[name="startId"]');
		$startSelect.find('option').prop('disabled', false);
		
		var endIndex = $this.find('option:selected').index(), startIndex = $startSelect.find('option:selected').index();
		$startSelect.find('option:gt(' + endIndex + ')').prop('disabled', true);
		
		if (startIndex > endIndex) {
			$startSelect[0].selectedIndex = endIndex;
		}
		$startSelect.trigger('chosen:updated');
		
		var nums = '';
		$this.find('option').each(function(i) {
			if (i >= startIndex && i <= endIndex) {
				if (nums.length > 0) {
					nums += ',';
				}
				nums += $(this).attr('value');
			}
		});
		
		$batchForm.find('[name="specimenNos"]').val(nums);
		$batchForm.data('formValidation').revalidateField('specimenNos');
	});
	
	var processes = $batchForm.find('[name="processOrder"] > option').map(function() {
		var $this = $(this);
		if ($this.val()) {
			return $this.text().trim();
		}
	}).get(); //['培养', '收获', '滴片', '显带', '扫片'];
	
	$status.find('label.btn').on('click', function() {
		var status = $(this).find('[name="status"]').val();
		$table.bootstrapTable('refresh', { query : { cultureStatus : status } });
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
			cultureStatus: $status.find('label.active > input').val()
		}
	}
	
	var popover = function(content) {
		if (content) {
			return 'rule="popover" data-placement="bottom" data-trigger="hover" data-width="238" rule="popover" data-content="' + content + '"';
		}
		return '';
	}
	
	var cultureFormatter = function(value, row, index) {
		var array = [];
		array.push('<div class="smartwizard sw-main sw-theme-dots">');
		array.push('<ul class="nav nav-tabs step-anchor">');
		
		var map = new Map();
		$.each(row.cultureList, function(i, v) {
			map.set(v.processStep, '<a ' + popover(v.remark) + '" href="#">' + v.processStep + '<br /><small>' + v.processTime + '</small></a>');
		});
		
		var next = true;
		$.each(processes, function(i, p) {
			array.push('<li id="' + row.id + '" data-order="', (i + 1), '" data-name="', p);
			
			if (map.get(p)) {
				array.push('" class="done">', map.get(p), '</li>');
			} else {
				if (next) {
					if (i < 1) {
						array.push('" class="' + (next ? 'active' : '') + '"><a href="#" title="' + p + '标本">' + p + '<br /><small>等待' + p + '</small></a></li>');
					} else {
						array.push('" class="' + (next ? 'active' : '') + '"><a href="#" title="' + p + '标本">' + p + '<br /><small>待完成' + p + '</small></a></li>');
					}
				} else {
					array.push('"><a href="#">' + p + '<br /><small>待完成</small></a></li>');
				}
				next = false;
			}
		});
		array.push('</ul>');
		
		return array.join('');
	}
	
	var statusFormatter = function(value, row, index) {
		if (value === true) {
			return '<span class="text-muted">已完成</span>';
		}
		if (row.cultureList && row.cultureList.length > 0) {
			return '<span class="label label-success">进行中</span>';
		} else {
			return '<span class="label label-info">等待培养</span>';
		}
	}
	
	window.cultureEvents = {
		'click .active > a': function(e, value, row, index) {
			e.preventDefault();
			
			var $this = $(e.target);
			$form.find('.form-control').val('');
			$form.formValidation('resetForm');
			
			$('#cultureModalLabel').text($this.parent().attr('data-name'));
			$form.find('[name="specimenId"]').val($this.parent().attr('id'));
			$form.find('[name="processOrder"]').val($this.parent().attr('data-order'));
			
			var step = $this.parent().attr('data-name');
			$form.find('[name="processStep"]').val(step);
			$form.find('[name="completedStatus"]').val(step == processes[processes.length - 1] ? 1 : 0);
			$form.find('[type="submit"]').attr('data-index', index);
			
			$('#cultureModal').modal('show');
		}
	}
	
	$table.bootstrapTable({
		url : '/specimen/culture/list',
		search : true,
		showRefresh : true,
		showToggle : false,
		showColumns : false,
		detailView : false,
		// striped : true,
		pagination : true,
		paginationLoop : false,
		sidePagination : 'server',
		pageSize : 10,
		pageList: [10, 20],
		toolbar : '#toolbar',
		queryParams : requestParams,
		columns : [ {
			field : 'specimenNo',
			title : '标本编号',
			class: 'col-xs-1',
		}, {
			field : 'type.name',
			title : '类型'
		}, {
			field : 'cultureList',
			title : '培养进度',
			formatter: cultureFormatter,
			events : cultureEvents
		}, {
			field: 'cultureStatus',
			title : '状态',
			formatter: statusFormatter
		} ]
	});	
	
	$table.on('load-success.bs.table', function() {
		$('a[rule="popover"]').webuiPopover();
	});
	
});