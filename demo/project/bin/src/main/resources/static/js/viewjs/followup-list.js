$(function() {
	'use strict';
	
	var $table = $('#table'), $form = $('#followupForm');
	var $startDate = $('#startDate'), $endDate = $('#endDate'), $status = $('#status');
	
	$('#specimenSN').typeahead({
        source: function(query, process) { 
        	if (!$.trim(query)) {
        		return false;
        	}
        	var type = query.replace(/[^A-Za-z]/ig, ""); 
        	var number = query.replace(/[^0-9]/ig, ""); 
        	
            $.post("/specimen/sn/list", {type: type, sn: number}, function(data) {
                var results = [];
                
                $.each(data, function(i, s) {
                	results.push(s.specimenType + s.specimenSN);
                });
                process(results);
            });
        },
        delay: 500,
        minLength: 1,
        afterSelect: function (item) {       
        	var type = item.replace(/[^A-Za-z]/ig, ""); 
        	$('[specimenType]').val(type);
        },
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
		$endDate.datepicker('setStartDate', e.date);
		$table.bootstrapTable('refresh', {
			query : {
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
		$startDate.datepicker('setEndDate', e.date);
		$table.bootstrapTable('refresh', {
			query : {
				endDate : $endDate.val()
			}
		});
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
			name: {
                validators: {
                    notEmpty: { message: 'The value is required.' },
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: 'The username must be more than 2 and less than 30 characters long'
                    }
                }
			},
            targetName: {
                validators: { notEmpty: { message: 'The value is required' } }
            },
            followupDate: {
            	validators: { notEmpty: { message: 'The value is required' } }
            },
            followupContent: {
            	validators: { 
            		notEmpty: { message: 'The value is required' },
            		stringLength: {
                        min: 6,
                        max: 200,
                        message: 'The username must be more than 6 and less than 200 characters long'
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
        	url: '/user/add',
        	type: 'post',
        	dataType: 'json',
        	beforeSubmit: function(arr, $form, options) {
        		if ( $form.find('[name="id"]').val() ) {
        			options.url = '/user/' + $form.find('[name="id"]').val() + '/update';
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
    	       
    	    	$table.bootstrapTable('refresh');
    	    	$('[data-dismiss="modal"]').trigger('click');
    	    }
        }); 
	}).find('[name="followupDate"]').datepicker({
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
		$form.formValidation('revalidateField', 'followupDate');
	});
	
	
	$('#addFollowup').on('click', function() {
		location.href = "/followup/record/add.html";
	});
	
	$status.find('label.btn').on('click', function() {
		var status = $(this).find('[name="status"]').val();
		$table.bootstrapTable('refresh', { query : { followupStatus : status } });
	});
		
	var requestParams = function(params) {
		return {
			limit : params.limit,
			offset : params.offset,
			search : params.search,
			followupStatus : $status.find('label.active > input').val()
		}
	}
	var birthdayFormatter = function(value, row, index) {
		var array = new Array();
		
		if (value > 0) {
			array.push('<span class="label label-success">', '正常');
		} else if (value < 0) {
			array.push('<span class="label label-danger">', '拒访');
		} else {
			array.push('<span class="label label-warning">', '失访');
		}
		array.push('</span>');
		
		return array.join('');
	}
	function operateFormatter(value, row, index) {
		var array = new Array();
		var href = '/followup/' + row.id + '/records.html';
		
		array.push('<a class="record-info btn btn-primary btn-sm margin-r-5" href="', href);
		array.push('" data-placement="left" title="随访记录详情">');
		array.push('<i class="fa fa-edit"></i> 详情');
		array.push('</a> ');
		
		return array.join('');
	}

	$table.bootstrapTable({
		url : '/followup/list',
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
			field : 'specimenNo',
			title : '标本号'
		}, {
			field : 'patientName',
			title : '姓名'
		}, {
			field : 'patientSex',
			title : '性别'
		}, {
			field : 'patientBirthdate',
			title : '出生日期'
		}, {
			field : 'followupTotalCount',
			title : '随访数'
		}, {
			field : 'lostFollowupCount',
			title : '失访数'
		}, {
			field : 'rejectFollowupCount',
			title : '拒访数'
		}, {
			field : 'firstFollowupDate',
			title : '首次随访日期'
		},  {
			field : 'lastFollowupDate',
			title : '最后随访日期'
		}, {
			class: 'col-xs-1',
			formatter : operateFormatter
		} ]
	});
	
});