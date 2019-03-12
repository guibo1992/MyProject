$(function() {
	'use strict';
	
	var $form = $('#reportForm');
	var $interpretModal = $('#interpretModal'), $conclusionModal = $('#conclusionModal');
	var $interpretTable = $('#interpret-table'), $conclusionTable = $('#conclusion-table');
	var $type = $form.find('[name="typeId"]');
	
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
			name: {
                validators: {
                	notEmpty: { message: 'The value is required.' },
					stringLength: {
			            max: 32,
			            message: 'The value must be less than 32 characters long'
			        }
                }
            },
            detectionMethod: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 200,
                        message: 'The value must be less than 200 characters long'
                    }
                }
			},
			resultInterpretation: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 200,
                        message: 'The value must be less than 200 characters long'
                    }
                }
			},
			reportConclusion: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 200,
                        message: 'The value must be less than 200 characters long'
                    }
                }
			},
			remarks: {
                validators: {
                    // notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        max: 200,
                        message: 'The value must be less than 200 characters long'
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
        	url: '/analysis/report/submit',
        	type: 'post',
        	dataType: 'json',
        	beforeSubmit: function(arr, $form, options) {
        		if ( $form.find('[name="id"]').val() ) {
        			options.url = '/analysis/report/' + $form.find('[name="id"]').val() + '/resubmit';
        		}
        	},
    	    success: function(data) {
    	    	$form.formValidation('resetForm');
    	    	
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
    	       
    	    	$.alert({
					title : '提示',
					content : "报告提交成功,等待审核!",
					buttons : {
						ok : { 
							text : '确定', 
							btnClass : 'btn-success',
							action: function() {
								history.back();
								// location.href = "/analysis/report/list.html";
							}
						}
					}
				});
    	    }
        }); 
	});
	
	$interpretModal.on('show.bs.modal', function() {
		$interpretTable.bootstrapTable('refresh', { 
			query : {
				typeId : $type.val()
			}
		});
	});
	$conclusionModal.on('show.bs.modal', function() {
		$conclusionTable.bootstrapTable('refresh', { 
			query : {
				typeId : $type.val()
			}
		});
	});
	
	$interpretTable.bootstrapTable({
		url : '/report/interpretation/list',
		search : false,
		showRefresh : false,
		showToggle : false,
		showColumns : false,
		detailView : false,
		//striped : true,
		pagination : true,
		paginationLoop : false,
		sidePagination : 'server',
		clickToSelect: true,
		height: 280,
		pageSize : 10,
		columns : [ {
			align: 'center',
            valign: 'middle',
            checkbox: true
        }, {
        	field: 'title',
        	title: '标题',
        }],
		onPostBody: function() {
			$interpretTable.find("input:checkbox").each(
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
	
	$conclusionTable.bootstrapTable({
		url : '/report/conclusion/list',
		search : false,
		showRefresh : false,
		showToggle : false,
		showColumns : false,
		detailView : false,
		//striped : true,
		pagination : true,
		paginationLoop : false,
		sidePagination : 'server',
		clickToSelect: true,
		height: 280,
		pageSize : 10,
		pageList: [10, 20, 50],
		columns : [ {
			align: 'center',
            valign: 'middle',
            checkbox: true
        }, {
        	field: 'title',
        	title: '标题'
        }],
		onPostBody: function() {
			$conclusionTable.find("input:checkbox").each(
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
	
	$interpretModal.find('[type="submit"]').on('click', function() {
		var selects = $interpretTable.bootstrapTable('getSelections');
		
		if (!selects || selects.length < 1) {
			$.alert({
				title : '警告',
				content : "请选择一个或一个以上的分析结果解释!",
				buttons : {
					close : { text : '关闭', btnClass : 'btn-warning' }
				}
			});
			return false;
		}
		$form.find('[name="resultInterpretation"]').val(selects.map(function(row) { return row.interpretation; }).join('\n'));
		$interpretModal.modal('hide');
		$form.data('formValidation').revalidateField('resultInterpretation');
	});
	
	$conclusionModal.find('[type="submit"]').on('click', function() {
		var selects = $conclusionTable.bootstrapTable('getSelections');
		
		if (!selects || selects.length < 1) {
			$.alert({
				title : '警告',
				content : "请选择一个或一个以上的报告结论!",
				buttons : {
					close : { text : '关闭', btnClass : 'btn-warning' }
				}
			});
			return false;
		}
		$form.find('[name="reportConclusion"]').val( selects.map(function(row) { return row.conclusion; }).join('\n'));
		$conclusionModal.modal('hide');
		$form.data('formValidation').revalidateField('reportConclusion');
	});
	
	var setReportDate = function() {
		var date = moment().format('YYYY-MM-DD');
		$('#reportDate').text(date);
		$('[name="reportDate"]').val(date);
	}
	self.setInterval('setReportDate()', 3600000)
	
});