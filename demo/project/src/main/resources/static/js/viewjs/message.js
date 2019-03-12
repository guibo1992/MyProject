$(function() {
	'use strict';

	var $form = $('#messageForm'), $summernote = $('#summernote');
	
	$form.formValidation({
		framework : 'bootstrap',
		excluded : [':disabled'],
		message : 'This value is not valid',
		icon : {
			valid : 'fa fa-check',
			invalid : 'fa fa-times',
			validating : 'fa fa-refresh'
		},
		fields : {
			title: {
				validators : { notEmpty : { message : 'The value is required' } }
			},
			content : {
				validators : {
					callback: {
						message: '公告内容不能为空',
						callback: function(value, validator, $field) {
							var code = $summernote.summernote('code');
							return (code !== '' && code !== '<p><br></p>');
						}
					}
				}
			}
		}
	}).on('err.field.fv', function(e, data) {
		data.element.data('fv.icon').hide();
    	data.fv.disableSubmitButtons(false);
    	
    }).on('success.field.fv', function(e, data) {
		data.element.parents('.has-success').removeClass('has-success');
		data.element.data('fv.icon').hide();

	}).on('success.form.fv', function(e) {
		e.preventDefault();

		var $group = $('#attachContainer').closest('.form-group');
		$group.find('input[type="hidden"]').remove();
		
		$('#attachContainer > tr').each(function(i) {
			var $this = $(this);
			$group.append('<input type="hidden" name="attachList[' + i + '].file" value="' + $this.find('td[name="name"]').attr('data-file') + '"/>');
			$group.append('<input type="hidden" name="attachList[' + i + '].name" value="' + $this.find('td[name="name"]').text() + '"/>')
			$group.append('<input type="hidden" name="attachList[' + i + '].type" value="' + $this.find('td[name="type"]').text() + '"/>')
			$group.append('<input type="hidden" name="attachList[' + i + '].filesize" value="' + $this.find('td[name="filesize"]').attr('data-val') + '"/>')
		});
		
		$(e.target).ajaxSubmit({
			url : '/message/release',
			type: 'post',
			dataType : 'json',
			beforeSubmit: function(arr, $form, options) {
        		if ( $form.find('[name="id"]').val() ) {
        			options.url = '/message/' + $form.find('[name="id"]').val() + '/update';
        		}
        	},
			success : function(data) {
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
				$.alert({
					title: '提示',
					content: "公告发布成功!",
					buttons: {
						ok: { 
							text: '确定', 
							btnClass: 'btn-primary',
							action: function() {
								location.href = "/message/list.html";
							}
						}
					}
				});
			}
		});
	})
	
	
	$summernote.summernote({
		toolbar: [
			['style', ['style', 'bold', 'italic', 'underline', 'clear']],
	        ['font', ['strikethrough', 'superscript', 'subscript']],
	        ['fontname', ['fontname']],
	        ['fontsize', ['fontsize']],
	        ['color', ['color']],
	        ['para', ['ul', 'ol', 'paragraph']],
	        ['height', ['height']],
	        ['insert', ['table', 'hr']],
	        ['misc', ['undo', 'redo']],
	        ['view', ['fullscreen', 'codeview' ]]
	    ],
		lang : 'zh-CN',
		fontNames: ['宋体', '微软雅黑', 'Arial', 'Comic Sans MS', 'Courier New', 'Helvetica', 'Impact', 'Tahoma', 'Times New Roman', 'Verdana'],
		placeholder: '公告内容',
		minHeight : 500,
		dialogsFade : true,
		dialogsInBody : true
	}).on('summernote.change', function(customEvent, contents, $editable) {
        $form.formValidation('revalidateField', 'content');
    });
//	$summernote.summernote('fontName', '微软雅黑');
//	$summernote.summernote('fontSize', 16);
	
	$('input[type="file"]').fileupload({
		url: "/transfer/file/upload",
		dataType: 'json',
		paramName: 'file',
		maxFileSize: 52428800,
		acceptFileTypes: /(\.|\/)(jpe?g|png|pdf|docx?|xlsx?)$/i,
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
    		var $container = $('#attachContainer');
    		
    		var array = [];
    		array.push('<tr>')
    		array.push('<td name="name" data-file="', result.data.file, '">', result.data.filename, '</td>');
    		array.push('<td name="type">', result.data.type, '</td>');
    		array.push('<td name="filesize" data-val="', result.data.length, '">', result.data.length, 'KB</td>');
    		array.push('<td><a href="#" name="delFile" class="btn btn-sm btn-danger"><i class="fa fa-remove"></i> 删除</a></td>');
    		array.push('</tr>')
    		$container.append(array.join(''));
    		
    	}
	}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
	
	
	$(document).on('click', 'a[name="delFile"]', function(e) {
		e.preventDefault();
		$(this).closest('tr').remove();
	});
	
});