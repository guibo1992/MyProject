
$(function() {
	'use strict';
	
	var $form = $('#templateForm');
	
	$form.formValidation({
		framework : 'bootstrap',
		excluded : ':disabled',
		message : 'This value is not valid',
		icon : {
			valid : 'fa fa-check',
			invalid : 'fa fa-times',
			validating : 'fa fa-refresh'
		}
	}).on('err.field.fv', function(e, data) {
    	data.fv.disableSubmitButtons(false);
    	
    }).on('success.field.fv', function(e, data) {
        data.element.parents('.has-success').removeClass('has-success');
        data.element.data('fv.icon').hide();
        
    }).on('success.form.fv', function(e) {
		e.preventDefault();
		
		$(e.target).ajaxSubmit({
        	url: '/report/template/add',
        	type: 'post',
        	dataType: 'json',
        	beforeSubmit: function(arr, $form, options) {
        		if ( $form.find('[name="id"]').val() ) {
        			options.url = '/report/template/' + $form.find('[name="id"]').val() + '/update';
        		}
        	},
    	    success: function(data) {
    	    	$form.formValidation('resetForm');
    	    	if (!data.success) {
    	    		$.alert({
						title : '错误',
						content : data.message,
						buttons : {
							ok : { text : '关闭', btnClass : 'btn-danger' }
						}
					});
    	    	   return false;
    	    	}
    	    	$.alert({
					title : '提示',
					content : "模板创建成功!",
					buttons : {
						ok : { text : '确定', btnClass : 'btn-success', action: function() { location.reload(); } }
					}
				});
    	    }
        }); 
	}).find('[name="hideResultSex"]').on('change', function() {
		$($(this).attr('data-target')).toggle();
	}).end().find('[rule="content"]').on('paste keydown keyup', function() {
		$($(this).attr('data-text')).html($(this).val().replace(/(\n\r)|\n|\r/g, '<br />'));
	}).on('blur', function() {
		if (!$(this).val()) {
			var $content = $($(this).attr('data-text'));
			$content.text($content.attr('data-default'));
		}
	});
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	$('#auditors select.chosen-select').on('change', function() {
		var $this = $(this), val = $this.val(), $target = $('#' + $this.attr('name'));
		var $other = $('#auditors').find('select').not($this);
		
		$other.find('option:disabled').prop('disabled', false);
		$other.find('option[value="' + val + '"]').prop('disabled', true);
		$other.trigger('chosen:updated');
		$target.text($this.find('option:selected').text());
	}).trigger('change');
	
	$('input[type="checked"]').trigger('change');
	$('#date').text(moment().format("YYYY-MM-DD"));
	
	$('#print').on('click', function() {
		var $report = $('#report-page');
		
    	var params = {
			name: $report.find('[name="name"]').text(),
			hisId: $report.find('[name="hisId"]').text(),
			specimenNo: $report.find('[name="specimenNo"]').text(),
			inspectionType: $report.find('[name="inspectionType"]').text(),
			specimenDate: $report.find('[name="specimenDate"]').text(),
			patientName: $report.find('[name="patientName"]').text(),
			patientSex: $report.find('[name="patientSex"]').text(),
			patientAge: $report.find('[name="patientAge"]').text(),
			inspectionPhysician: $report.find('[name="inspectionPhysician"]').text(),
			inspectionDept: $report.find('[name="inspectionDept"]').text(),
			clinicalInfo: $report.find('[name="clinicalInfo"]').text(),
			detectionMethod: $report.find('[name="detectionMethod"]:visible').text(),
			analysisResult: $report.find('[name="analysisResult"]:visible').text(),
			analysisKarImg: $report.find('[name="analysedKarImg"]:visible').attr('data-value'),
			analysisMetImg: $report.find('[name="analysedMetImg"]:visible').attr('data-value'),
			resultInterpretation: $report.find('[name="resultInterpretation"]').text(),
			reportConclusion: $report.find('[name="reportConclusion"]').text(),
			remarks: $report.find('[name="remarks"]:visible').text(),
			laboratorian: $report.find('[name="laboratorian"]').text(),
			masterAuditor: $report.find('[name="masterAuditor"]').text(),
			deputyAuditor: $report.find('[name="deputyAuditor"]').text(),
			reportDate: $report.find('[name="reportDate"]').text()
    	}
    	params['template'] = {
    			hideDetectionMethod: $form.find('input[name="hideDetectionMethod"]:checked').val(),
    			hideResultMetImg: $form.find('input[name="hideResultMetImg"]:checked').val(),
    			hideResultSex: $form.find('input[name="hideResultSex"]:checked').val(),
    			hideRemarks: $form.find('input[name="hideRemarks"]:checked').val()
    	};
    	
    	$.ajax({
    		url: '/report/template/test/print',
    		type: "post", 
            dataType: "json",
            traditional: true,
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(params),
            success : function (data) {
            	if (!data.success) {
					$.alert({
						title: '错误',
						content: data.message,
						buttons: {
							close: { text: '关闭', btnClass: 'btn-danger' }
						}
					});
					return false;
				}
            	$('iframe').remove();
            	var $frame = $('<iframe style="display:none" ></iframe>').appendTo($('body'));
				$frame.on("load", function() { 
					$(this)[0].contentWindow.print(); 
				});
				$frame.attr('src', "/transfer/pdf/print-stream?file=" + data.data)
            }
        });
    	
    });
	
	
	
});