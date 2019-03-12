
$(function() {
	
	var $_items = $('#_items'), $box = $('.tmpl-box');
	
	$(".chosen-select").chosen({
		width : "100%",
		disable_search_threshold : 10
	});
	
	var dragStart = function($this, x, y) {
		$('#pointX').val(x);
    	$('#pointY').val(y);
    	if ($this.attr('data-type') == 'qr') {
    		$('#text').prop('disabled', true).val('');
    		$('#width').prop('disabled', false).val($this.width());
        	$('#height').prop('disabled', false).val($this.height());
    		$('#fontSize').prop('disabled', true).val('').trigger('chosen:updated');
    	} else {
    		$('#width').prop('disabled', true);
    		$('#height').prop('disabled', true);
    		$('#text').prop('disabled', false).val($this.text());
    		$('#fontSize').prop('disabled', false).val($this.css('font-size')).trigger('chosen:updated');
    	}
	}
	
	$box.find('div.item').each(function() {
		var $this = $(this);
		
		$this.boxDrag({
			parent: $box,
			dragStart: function(x,y){
				$this.trigger('click');
				$('#pointX').val(x);
				$('#pointY').val(y);
			}, 
			dragEnd: function(x,y){
				$('#pointX').val(x);
				$('#pointY').val(y);
			},
			dragMove: function(x,y){
				$('#pointX').val(x);
				$('#pointY').val(y);
			} 
		});
		
		if ($this.attr('data-type') == 'qr') {
			$this.children().remove();
			$this.qrcode({
        		width: $this.innerWidth(), 
        		height: $this.innerHeight(),
        		text : "P201800345B04"
        	});	
		} 
	});
	
	$(document).on('click', 'div.item', function() {
		var $this = $(this);
    	$(this).addClass('active').siblings().removeClass('active');
    	
    	$('#pointX').val($this.position().left);
    	$('#pointY').val($this.position().top);
    	
    	if ($this.attr('data-type') == 'qr') {
    		$('#text').prop('disabled', true).val('');
    		$('#width').prop('disabled', false).val($this.width());
        	$('#height').prop('disabled', false).val($this.height());
    		$('#fontSize').prop('disabled', true).val('').trigger('chosen:updated');
    	} else {
    		$('#width').prop('disabled', true);
    		$('#height').prop('disabled', true);
    		$('#text').prop('disabled', false).val($this.text());
    		$('#fontSize').prop('disabled', false).val($this.css('font-size')).trigger('chosen:updated');
    	}
    });
	
    $('div.checkbox > input[type="checkbox"]').on('change', function() {
    	var $this = $(this)
    	
    	let $i = $box.find('#' + $this.val());
    	if ($i.length > 0) {
			if ($i.hasClass('active')) {
				$('.tmpl-control .form-control').val('');
				$('.tmpl-control select').trigger('chosen:updated');
			}
			$i.remove();
    	}
    
    	if ($this.prop('checked')) {
    		var $selector = $($_items.find('[data-id="' + $this.val() + '"]').get(0)).clone();
    		$selector.attr('id', $this.val());
    		
    		$selector.appendTo($box).boxDrag({
        	    parent: $box,
        	    dragStart: function(x,y){
        	    	$selector.trigger('click');
    				$('#pointX').val(x);
    				$('#pointY').val(y);
    			}, 
    			dragEnd: function(x,y){
    				$('#pointX').val(x);
    				$('#pointY').val(y);
    			},
    			dragMove: function(x,y){
    				$('#pointX').val(x);
    				$('#pointY').val(y);
    			} 
        	});
    		if ($selector.attr('data-type') == 'qr') {
	    		$selector.qrcode({
	        		width: $selector.innerWidth(), 
	        		height: $selector.innerHeight(),
	        		text : "P201800345B04"
	        	});	
    		}
    	}
    });
    
    $('#text').on('keyup paste', function() {
    	$('.tmpl-box').find('div.active').text($(this).val()).trigger('resize');
    });
    $('#fontSize').on('change', function() {
    	var font = $(this).val();
    	$('.tmpl-box').find('div.active').css({'font-size': font}).trigger('resize');
    });
    $('#pointX').on('keyup change', function() {
    	var $this = $(this);
    	$('.tmpl-box').find('div.active').css({ left: parseInt($this.val()) });
    });
    $('#pointY').on('keyup change', function() {
    	var $this = $(this);
    	$('.tmpl-box').find('div.active').css({ top: parseInt($this.val()) });
    });
    $('#width').on('keyup change', function() {
    	var $this = $(this), $item = $('.tmpl-box').find('div.active');
    	var width = parseInt($this.val());
    	
    	$item.width(width).trigger('resize');
    	if ($item.attr('data-type') == 'qr') {
    		$item.find('canvas').width(width);
    	}
    	$('#height').val(width).trigger('change');
    });
    $('#height').on('keyup change', function() {
    	var $this = $(this), $item = $('.tmpl-box').find('div.active');
    	var height = parseInt($this.val());
    	$item.height(height);
    	
    	if ($item.attr('data-type') == 'qr') {
    		$item.find('canvas').height(height);
    	}
    	$('#width').val(height).trigger('change');
    });

    $('.btn.print').on('click', function() {
    	var array = [];
    	$box.find('div.item').each(function() {
    		var $this = $(this);
    		array.push({
        		type: $this.attr('data-type'),
    			pointX: $this.position().left,
        		pointY: $this.position().top,
        		width: $this.width(),
        		height: $this.height(),
        		value: $this.text(),
        		padding: $this.css('padding').replace('px', ''),
        		fontSize: $this.css('font-size').replace('px', ''),
        		itemHtml: $this.prop('outerHTML')
    		});
    	});
    	var json = {
    		id: $('#id').val(),
    		name: $('#tmplName').val(),
    		width: $box.width(),
    		height: $box.height(),
    		htmlContent: $box.html(),
    		itemList: array
    	}
    	
    	$.ajax({
    		url: '/slide/template/print',
    		type: "post", 
            dataType: "json",
            traditional: true,
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(json),
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
				$frame.attr('src', "/slide/template/preview?file=" + data.data)
            }
        });
    	
    });
    
    $('#save').click(function() {
    	var array = [];
    	$box.find('div.item').each(function() {
    		var $this = $(this);
    		array.push({
        		type: $this.attr('data-type'),
    			pointX: $this.position().left,
        		pointY: $this.position().top,
        		width: $this.width(),
        		height: $this.height(),
        		value: $this.text(),
        		padding: $this.css('padding').replace('px', ''),
        		fontSize: $this.css('font-size').replace('px', ''),
        		itemHtml: $this.prop('outerHTML')
    		});
    	});
    	var json = {
    		id: $('#id').val(),
    		idCode: $('#idCode').val(),
    		name: $('#tmplName').val(),
    		width: $box.width(),
    		height: $box.height(),
    		htmlContent: $box.html(),
    		itemList: array
    	}
    	
    	$.ajax({
    		url: '/slide/template/save',
    		type: "post", 
            dataType: "json",
            traditional: true,
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(json),
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
				$.alert({
					title: '提示',
					content: "模板保存成功!",
					buttons: {
						ok: { 
							text: '确定', 
							btnClass: 'btn-primary'
						}
					}
				});
            }
        });
    });
    
    $('.tmpl-box').find('[data-id="_number"]').on('resize', function() {
    	$("#canvascode").JsBarcode($(this).text());
    }).trigger('resize');
    
    if (!$('#id').val()) {
    	$('div.checkbox > input[type="checkbox"]').attr('checked', true).trigger('change');
    }
    
});