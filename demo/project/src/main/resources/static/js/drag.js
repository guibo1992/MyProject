;(function($, window, document, undefined) {
	
	// 定义的构造函数
	var Drag = function(ele, opt) {
		this.$ele = ele, this.isDown = false,
		this.mX = 0, this.mY = 0,
		this.x = 0, this.y = 0, 
		this.defaults = {
			parent : $('body'),
			randomPosition : false,
			handler : false,
			dragStart : function(x, y) { },
			dragEnd : function(x, y) { },
			dragMove : function(x, y) { }
		};
		this.options = $.extend({}, this.defaults, opt)
	}
	
	Drag.prototype = {
		run : function() {
			var $this = this;
			var $elt = this.$ele;
			var randomPosition = this.options.randomPosition;
			var $box = this.options.parent;
			var fun = this.options;
			var X = 0, Y = 0, moveX, moveY, boxWidth = 0, boxHeight = 0, itemWidth = 0, itemHeight = 0;
			
			var initSize = function($box, $elt) {
				boxWidth = $box.outerWidth();
				boxHeight = $box.outerHeight();
				itemWidth = $elt.outerWidth();
				itemHeight = $elt.outerHeight();
			}
			var randomPlace = function() {
				if (randomPosition) {
					var randX = parseInt(Math.random() * (boxWidth - itemWidth));
					var randY = parseInt(Math.random() * (boxHeight - itemHeight));
					$elt.css({ left : randX, top : randY });
				}
			}
			initSize($box, $elt);
			
			if (randomPosition) {
				randomPlace();
			}
			$(window).resize(function() {
				
			});
			$elt.on('keyup', function(e) {
				e.preventDefault();

				if(e.keyCode==13) {
					$(this).text($(this).html().replace(/<\/?(div|br)>/g, ''));
					return false;
				};
			});
			$elt.resize(function() {
				itemWidth = $(this).outerWidth();
				itemHeight = $(this).outerHeight();
			});
			
			$elt.mousedown(function(e) {
				$this.isDown = true;
				$(this).addClass('drag');
				
				$this.mX = e.pageX, $this.mY = e.pageY;
				$this.x = $(this).position().left, $this.y = $(this).position().top;
				
				fun.dragStart(parseInt($(this).css('left')), parseInt($(this).css('top')));
				return false;
			});
			$(document).mouseup(function(e) {
				$('.drag').removeClass('drag');
				fun.dragEnd(parseInt($elt.css('left')), parseInt($elt.css('top')));
				$this.isDown = false;
			});
			
			$(document).on('mousemove', function(e) {
				if (!$this.isDown) {
					return false;
				}
				moveX = $this.x + e.pageX - $this.mX;
				moveY = $this.y + e.pageY - $this.mY;
				
				var $elt = $('.drag');
				fun.dragMove(parseInt($elt.css('left')), parseInt($elt.css('top')));
				
//				function move() { 
//					if ($this.isDown) {
						$elt.css({ 'left' : moveX, 'top' : moveY });
//					} else {
//						return;
//					}
					if (moveX < 0) {
						$elt.css({ left : 0 });
					}
					if (moveX > (boxWidth - itemWidth)) {
						$elt.css({ left : boxWidth - itemWidth });
					}
					if (moveY < 0) {
						$elt.css({ top : 0 });
					}
					if (moveY > (boxHeight - itemHeight)) {
						$elt.css({ top : boxHeight - itemHeight });
					}
//				}
//				
//				move();
				
			});
		}
	}

	// 插件
	$.fn.boxDrag = function(options) {
		var $this = this;
		// 创建实体
		var drag = new Drag($this, options);
		// 调用方法
		drag.run();
		return this;
	}
})(jQuery, window, document);