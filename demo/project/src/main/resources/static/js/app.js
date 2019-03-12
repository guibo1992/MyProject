/*! BLADE app.js
 * ================
 * Main JS application file for BLADE. This file
 * should be included in all pages. It controls some layout
 * options and implements exclusive BLADE plugins.
 *
 * @version 2.3.0
 * @license MIT <http://opensource.org/licenses/MIT>
 */

//Make sure jQuery has been loaded before app.js
if (typeof jQuery === "undefined") {
	throw new Error("WebApp requires jQuery");
}

/**
 * Store a new settings in the browser
 * 
 * @param String
 *            name Name of the setting
 * @param String
 *            val Value of the setting
 * @returns void
 */
function store(name, val) {
	if (typeof (Storage) !== "undefined") {
		localStorage.setItem(name, val);
	} else {
		window.alert('Please use a modern browser to properly view this template!');
	}
}

/**
 * Get a prestored setting
 * 
 * @param String
 *            name Name of of the setting
 * @returns String The value of the setting | null
 */
function get(name) {
	if (typeof (Storage) !== "undefined") {
		return localStorage.getItem(name);
	} else {
		window.alert('Please use a modern browser to properly view this template!');
	}
}

/*
 * BLADE
 * 
 * @type Object @description $.BLADE is the main object for the template's app. It's used for implementing functions and
 * options related to the template. Keeping everything wrapped in an object prevents conflict with other plugins and is
 * a better way to organize our code.
 */
$.BLADE = {};

/*
 * -------------------- - BLADE Options - -------------------- Modify these options to suit your implementation
 */
$.BLADE.options = {
	// Add slimscroll to navbar menus
	// This requires you to load the slimscroll plugin
	// in every page before app.js
	navbarMenuSlimscroll : true,
	navbarMenuSlimscrollWidth : "3px", // The width of the scroll bar
	navbarMenuHeight : "200px", // The height of the inner menu
	// General animation speed for JS animated elements such as box collapse/expand and
	// 'fast', 'normal', or 'slow'
	animationSpeed : 300,
	// Sidebar push menu toggle button selector
	sidebarToggleSelector : "[data-toggle='offcanvas']",
	// Activate sidebar push menu
	sidebarPushMenu : true,
	// Activate sidebar slimscroll if the fixed layout is set (requires SlimScroll Plugin)
	sidebarSlimScroll : true,
	// Enable sidebar expand on hover effect for sidebar mini
	// This option is forced to true if both the fixed layout and sidebar mini
	// are used together
	sidebarExpandOnHover : false,
	// BoxRefresh Plugin
	enableBoxRefresh : true,
	// Bootstrap.js tooltip
	enableBSToppltip : true,
	BSTooltipSelector : "[data-toggle='tooltip']",
	// Enable Fast Click. Fastclick.js creates a more
	// native touch experience with touch devices. If you
	// choose to enable the plugin, make sure you load the script
	// before BLADE's app.js
	enableFastclick : true,
	// Control Sidebar Options
	enableControlSidebar : false,
	controlSidebarOptions : {
		// Which button should trigger the open/close event
		toggleBtnSelector : "[data-toggle='control-sidebar']",
		// The sidebar selector
		selector : ".control-sidebar",
		// Enable slide over content
		slide : true
	},
	// Box Widget Plugin. Enable this plugin
	// to allow boxes to be collapsed and/or removed
	enableBoxWidget : true,
	// Box Widget plugin options
	boxWidgetOptions : {
		boxWidgetIcons : {
			// Collapse icon
			collapse : 'fa-minus',
			// Open icon
			open : 'fa-plus',
			// Remove icon
			remove : 'fa-times'
		},
		boxWidgetSelectors : {
			// Remove button selector
			remove : '[data-widget="remove"]',
			// Collapse button selector
			collapse : '[data-widget="collapse"]'
		}
	},
	// Direct Chat plugin options
	directChat : {
		// Enable direct chat by default
		enable : true,
		// The button to open and close the chat contacts pane
		contactToggleSelector : '[data-widget="chat-pane-toggle"]'
	},
	// Define the set of colors to use globally around the website
	colors : {
		lightBlue : "#3c8dbc",
		red : "#f56954",
		green : "#00a65a",
		aqua : "#00c0ef",
		yellow : "#f39c12",
		blue : "#0073b7",
		navy : "#001F3F",
		teal : "#39CCCC",
		olive : "#3D9970",
		lime : "#01FF70",
		orange : "#FF851B",
		fuchsia : "#F012BE",
		purple : "#8E24AA",
		maroon : "#D81B60",
		black : "#222222",
		gray : "#d2d6de"
	},
	// The standard screen sizes that bootstrap uses.
	// If you change these in the variables.less file, change
	// them here too.
	screenSizes : {
		xs : 480,
		sm : 768,
		md : 992,
		lg : 1200
	}
};

/*
 * ------------------ - Implementation - ------------------ The next block of code implements BLADE's functions and
 * plugins as specified by the options above.
 */
$(function() {
	"use strict";

	// Fix for IE page transitions
	$("body").removeClass("hold-transition");

	if (get('body') && get('body') == 'collapse') {
		$("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');
	}

	// Extend options if external options exist
	if (typeof BLADEOptions !== "undefined") {
		$.extend(true, $.BLADE.options, BLADEOptions);
	}

	// Easy access to options
	var o = $.BLADE.options;

	// Set up the object
	_init();

	// Activate the layout maker
	$.BLADE.layout.activate();

	// Enable sidebar tree view controls
	// $.BLADE.tree('.sidebar');

	// Enable control sidebar
	if (o.enableControlSidebar) {
		$.BLADE.controlSidebar.activate();
	}

	// Add slimscroll to navbar dropdown
	if (o.navbarMenuSlimscroll && typeof $.fn.slimscroll != 'undefined') {
		$(".navbar .menu").slimscroll({
			// height : o.navbarMenuHeight,
			maxHeight: "200px",
			alwaysVisible : false,
			size : o.navbarMenuSlimscrollWidth
		}).css("width", "100%");
	}

	// Activate sidebar push menu
	if (o.sidebarPushMenu) {
		$.BLADE.pushMenu.activate(o.sidebarToggleSelector);
	}

	// Activate Bootstrap tooltip
	if (o.enableBSToppltip) {
		$('body').tooltip({
			selector : o.BSTooltipSelector
		});
	}

	// Activate box widget
	if (o.enableBoxWidget) {
		$.BLADE.boxWidget.activate();
	}

	// Activate fast click
	if (o.enableFastclick && typeof FastClick != 'undefined') {
		FastClick.attach(document.body);
	}

	// Activate direct chat widget
	if (o.directChat.enable) {
		$(document).on('click', o.directChat.contactToggleSelector, function() {
			var box = $(this).parents('.direct-chat').first();
			box.toggleClass('direct-chat-contacts-open');
		});
	}

	/*
	 * INITIALIZE BUTTON TOGGLE ------------------------
	 */
	$('.btn-group[data-toggle="btn-toggle"]').each(function() {
		var group = $(this);
		$(this).find(".btn").on('click', function(e) {
			group.find(".btn.active").removeClass("active");
			$(this).addClass("active");
			e.preventDefault();
		});

	});
});

/*
 * ---------------------------------- - Initialize the BLADE Object - ---------------------------------- All BLADE
 * functions are implemented below.
 */
function _init() {
	'use strict';
	/*
	 * Layout ====== Fixes the layout height in case min-height fails.
	 * 
	 * @type Object @usage $.BLADE.layout.activate() $.BLADE.layout.fix() $.BLADE.layout.fixSidebar()
	 */
	$.BLADE.layout = {
		activate : function() {
			var _this = this;
			_this.fix();
			_this.fixSidebar();
			$(window, ".wrapper", ".sidebar").resize(function() {
				_this.fix();
				_this.fixSidebar();
			});
		},
		fix : function() {
			// Get window height and the wrapper height
			var neg = $('.main-header').outerHeight() + ($('.main-footer').length > 0 ? $('.main-footer').outerHeight() : 0);
			var window_height = $(window).height();

			var sidebar_height = $(".sidebar").height();
			// Set the min-height of the content and sidebar based on the
			// the height of the document.
			if ($("body").hasClass("fixed")) {
				$(".content-wrapper").css('min-height', window_height - $('.main-footer').outerHeight());
			} else {
				var postSetWidth;
				if (window_height >= sidebar_height) {
					$(".content-wrapper").css('min-height', window_height - neg);
					postSetWidth = window_height - neg;
				} else {
					$(".content-wrapper").css('min-height', sidebar_height);
					postSetWidth = sidebar_height;
				}

				// Fix for the control sidebar height
				var controlSidebar = $($.BLADE.options.controlSidebarOptions.selector);
				if (typeof controlSidebar !== "undefined") {
					if (controlSidebar.height() > postSetWidth)
						$(".content-wrapper").css('min-height', controlSidebar.height());
				}

			}
		},
		fixSidebar : function() {
			// Make sure the body tag has the .fixed class
			if (!$("body").hasClass("fixed")) {
				if (typeof $.fn.slimScroll != 'undefined') {
					$(".sidebar").slimScroll({
						destroy : true
					}).height("auto");
				}
				return;
			} else if (typeof $.fn.slimScroll == 'undefined' && window.console) {
				window.console.error("Error: the fixed layout requires the slimscroll plugin!");
			}
			// Enable slimscroll for fixed layout
			if ($.BLADE.options.sidebarSlimScroll) {
				if (typeof $.fn.slimScroll != 'undefined') {
					// Destroy if it exists
					$(".sidebar").slimScroll({
						destroy : true
					}).height("auto");
					// Add slimscroll
					$(".sidebar").slimscroll({
						height : ($(window).height() - $(".main-header").height()) + "px",
						color : "rgba(0,0,0,0.2)",
						size : "3px"
					});
				}
			}
		}
	};

	/*
	 * PushMenu() ========== Adds the push menu functionality to the sidebar.
	 * 
	 * @type Function @usage: $.BLADE.pushMenu("[data-toggle='offcanvas']")
	 */
	$.BLADE.pushMenu = {
		activate : function(toggleBtn) {
			// Get the screen sizes
			var screenSizes = $.BLADE.options.screenSizes;

			// Enable sidebar toggle
			$(toggleBtn).on('click', function(e) {
				e.preventDefault();

				// Enable sidebar push menu
				if ($(window).width() > (screenSizes.sm - 1)) {
					if ($("body").hasClass('sidebar-collapse')) {
						$("body").removeClass('sidebar-collapse').trigger('expanded.pushMenu');
						localStorage.clear();
					} else {
						$("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');
						store('body', 'collapse');
					}
				}
				// Handle sidebar push menu for small screens
				else {
					if ($("body").hasClass('sidebar-open')) {
						$("body").removeClass('sidebar-open').removeClass('sidebar-collapse').trigger('collapsed.pushMenu');
					} else {
						$("body").addClass('sidebar-open').trigger('expanded.pushMenu');
					}
				}
			});

			$(".content-wrapper").click(function() {
				// Enable hide menu when clicking on the content-wrapper on small screens
				if ($(window).width() <= (screenSizes.sm - 1) && $("body").hasClass("sidebar-open")) {
					$("body").removeClass('sidebar-open');
				}
			});

			// Enable expand on hover for sidebar mini
			if ($.BLADE.options.sidebarExpandOnHover || ($('body').hasClass('fixed') && $('body').hasClass('sidebar-mini'))) {
				this.expandOnHover();
			}
		},
		expandOnHover : function() {
			var _this = this;
			var screenWidth = $.BLADE.options.screenSizes.sm - 1;
			// Expand sidebar on hover
			$('.main-sidebar').hover(function() {
				if ($('body').hasClass('sidebar-mini') && $("body").hasClass('sidebar-collapse') && $(window).width() > screenWidth) {
					_this.expand();
				}
			}, function() {
				if ($('body').hasClass('sidebar-mini') && $('body').hasClass('sidebar-expanded-on-hover') && $(window).width() > screenWidth) {
					_this.collapse();
				}
			});
		},
		expand : function() {
			$("body").removeClass('sidebar-collapse').addClass('sidebar-expanded-on-hover');
		},
		collapse : function() {
			if ($('body').hasClass('sidebar-expanded-on-hover')) {
				$('body').removeClass('sidebar-expanded-on-hover').addClass('sidebar-collapse');
			}
		}
	};

	/*
	 * ControlSidebar ============== Adds functionality to the right sidebar
	 * 
	 * @type Object @usage $.BLADE.controlSidebar.activate(options)
	 */
	$.BLADE.controlSidebar = {
		// instantiate the object
		activate : function() {
			// Get the object
			var _this = this;
			// Update options
			var o = $.BLADE.options.controlSidebarOptions;
			// Get the sidebar
			var sidebar = $(o.selector);
			// The toggle button
			var btn = $(o.toggleBtnSelector);

			// Listen to the click event
			btn.on('click', function(e) {
				e.preventDefault();
				// If the sidebar is not open
				if (!sidebar.hasClass('control-sidebar-open') && !$('body').hasClass('control-sidebar-open')) {
					// Open the sidebar
					_this.open(sidebar, o.slide);
				} else {
					_this.close(sidebar, o.slide);
				}
			});

			// If the body has a boxed layout, fix the sidebar bg position
			var bg = $(".control-sidebar-bg");
			_this._fix(bg);

			// If the body has a fixed layout, make the control sidebar fixed
			if ($('body').hasClass('fixed')) {
				_this._fixForFixed(sidebar);
			} else {
				// If the content height is less than the sidebar's height, force max height
				if ($('.content-wrapper, .right-side').height() < sidebar.height()) {
					_this._fixForContent(sidebar);
				}
			}
		},
		// Open the control sidebar
		open : function(sidebar, slide) {
			// Slide over content
			if (slide) {
				sidebar.addClass('control-sidebar-open');
			} else {
				// Push the content by adding the open class to the body instead
				// of the sidebar itself
				$('body').addClass('control-sidebar-open');
			}
		},
		// Close the control sidebar
		close : function(sidebar, slide) {
			if (slide) {
				sidebar.removeClass('control-sidebar-open');
			} else {
				$('body').removeClass('control-sidebar-open');
			}
		},
		_fix : function(sidebar) {
			var _this = this;
			if ($("body").hasClass('layout-boxed')) {
				sidebar.css('position', 'absolute');
				sidebar.height($(".wrapper").height());
				$(window).resize(function() {
					_this._fix(sidebar);
				});
			} else {
				sidebar.css({
					'position' : 'fixed',
					'height' : 'auto'
				});
			}
		},
		_fixForFixed : function(sidebar) {
			sidebar.css({
				'position' : 'fixed',
				'max-height' : '100%',
				'overflow' : 'auto',
				'padding-bottom' : '50px'
			});
		},
		_fixForContent : function(sidebar) {
			$(".content-wrapper, .right-side").css('min-height', sidebar.height());
		}
	};

	/*
	 * BoxWidget ========= BoxWidget is a plugin to handle collapsing and removing boxes from the screen.
	 * 
	 * @type Object @usage $.BLADE.boxWidget.activate() Set all your options in the main $.BLADE.options object
	 */
	$.BLADE.boxWidget = {
		selectors : $.BLADE.options.boxWidgetOptions.boxWidgetSelectors,
		icons : $.BLADE.options.boxWidgetOptions.boxWidgetIcons,
		animationSpeed : $.BLADE.options.animationSpeed,
		activate : function(_box) {
			var _this = this;
			if (!_box) {
				_box = document; // activate all boxes per default
			}
			// Listen for collapse event triggers
			$(_box).on('click', _this.selectors.collapse, function(e) {
				e.preventDefault();
				_this.collapse($(this));
			});

			// Listen for remove event triggers
			$(_box).on('click', _this.selectors.remove, function(e) {
				e.preventDefault();
				_this.remove($(this));
			});
		},
		collapse : function(element) {
			var _this = this;
			// Find the box parent
			var box = element.parents(".box").first();
			// Find the body and the footer
			var box_content = box.find("> .box-body, > .box-footer, > form  >.box-body, > form > .box-footer");
			if (!box.hasClass("collapsed-box")) {
				// Convert minus into plus
				element.children(":first").removeClass(_this.icons.collapse).addClass(_this.icons.open);
				// Hide the content
				box_content.slideUp(_this.animationSpeed, function() {
					box.addClass("collapsed-box");
				});
			} else {
				// Convert plus into minus
				element.children(":first").removeClass(_this.icons.open).addClass(_this.icons.collapse);
				// Show the content
				box_content.slideDown(_this.animationSpeed, function() {
					box.removeClass("collapsed-box");
				});
			}
		},
		remove : function(element) {
			// Find the box parent
			var box = element.parents(".box").first();
			box.slideUp(this.animationSpeed);
		}
	};
}

/*
 * ------------------ - Custom Plugins - ------------------ All custom plugins are defined below.
 */

/*
 * BOX REFRESH BUTTON ------------------ This is a custom plugin to use with the component BOX. It allows you to add a
 * refresh button to the box. It converts the box's state to a loading state.
 * 
 * @type plugin @usage $("#box-widget").boxRefresh( options );
 */
(function($) {

	"use strict";

	$.fn.boxRefresh = function(options) {

		// Render options
		var settings = $.extend({
			// Refresh button selector
			trigger : ".refresh-btn",
			// File source to be loaded (e.g: ajax/src.php)
			source : "",
			// Callbacks
			onLoadStart : function(box) {
				return box;
			}, // Right after the button has been clicked
			onLoadDone : function(box) {
				return box;
			} // When the source has been loaded

		}, options);

		// The overlay
		var overlay = $('<div class="overlay"><div class="fa fa-refresh fa-spin"></div></div>');

		return this.each(function() {
			// if a source is specified
			if (settings.source === "") {
				if (window.console) {
					window.console.log("Please specify a source first - boxRefresh()");
				}
				return;
			}
			// the box
			var box = $(this);
			// the button
			var rBtn = box.find(settings.trigger).first();

			// On trigger click
			rBtn.on('click', function(e) {
				e.preventDefault();
				// Add loading overlay
				start(box);

				// Perform ajax call
				box.find(".box-body").load(settings.source, function() {
					done(box);
				});
			});
		});

		function start(box) {
			// Add overlay and loading img
			box.append(overlay);

			settings.onLoadStart.call(box);
		}

		function done(box) {
			// Remove overlay and loading img
			box.find(overlay).remove();

			settings.onLoadDone.call(box);
		}

	};

})(jQuery);

/*
 * EXPLICIT BOX ACTIVATION ----------------------- This is a custom plugin to use with the component BOX. It allows you
 * to activate a box inserted in the DOM after the app.js was loaded.
 * 
 * @type plugin @usage $("#box-widget").activateBox();
 */
(function($) {

	'use strict';

	$.fn.activateBox = function() {
		$.BLADE.boxWidget.activate(this);
	};
})(jQuery);

/*
 * Init commoms function
 */

$.BLADE.menuOpts = {
	toggle : false
};

$(function() {
//	FastClick.attach(document.body);
	
	jconfirm.defaults = {
    	theme: 'black',	
    	closeIcon: true,
        closeIconClass: 'fa fa-close',
    	title: '提示',
        content: '确定继续执行此操作吗?',
        boxWidth: '450px',
        useBootstrap: false
    };

	$(".sidebar-menu").metisMenu($.BLADE.menuOpts).on('hide.metisMenu', function(e) {
		if ($("body").hasClass('sidebar-collapse')) {
			var $target = $(e.target);
			if (!$target.parents('ul.collapse').length > 0) {
				e.preventDefault();
				return false;
			}
		}
	}).on('show.metisMenu', function(e) {
		if ($("body").hasClass('sidebar-collapse')) {
			e.preventDefault();
			return false;
		}
	});

	$('.sidebar-menu > li').on('mouseenter', function(e) {
		if ($("body").hasClass('sidebar-collapse')) {
			e.preventDefault();
			$(this).find('ul.collapse').first().css('height', 'auto');
		}
	});

	// Monitor menu document reload
	$('#_menu_container').on('DOMNodeInserted', function() {
		$(".sidebar-menu").metisMenu($.BLADE.menuOpts).on('hide.metisMenu', function(e) {
			if ($("body").hasClass('sidebar-collapse')) {
				var $target = $(e.target);
				if (!$target.parents('ul.collapse').length > 0) {
					e.preventDefault();
					return false;
				}
			}
		}).on('show.metisMenu', function(e) {
			if ($("body").hasClass('sidebar-collapse')) {
				e.preventDefault();
				return false;
			}
		});
		$('.sidebar-menu > li').on('mouseenter', function(e) {
			if ($("body").hasClass('sidebar-collapse')) {
				$(this).find('ul.collapse').first().css('height', 'auto');
			}
		});
	});

	$('#logout').on('click', function() {
		$.confirm({
			theme : 'black',
			closeIcon : true,
			closeIconClass : 'fa fa-close',
			icon : 'fa fa-question-circle',
			title : '退出',
			content : 'Are you sure to logout?',
			buttons : {
				confirm : {
					text : '<i class="fa fa-power-off"></i> 退出',
					btnClass : 'btn-warning',
					action : function() {
						location.href = '/logout';
					}
				},
				cancel : {
					text : '<i class="fa fa-times"></i> 取消',
					btnClass : 'btn-default'
				},
			}
		});
	});
	
	$('#message').on('click', function() {
		location.href="/announcement/list.html";
	});

	$('[role="data-remain"]').each(function() {
		var $this = $(this);
		var maxLength = $this.attr('maxlength');
		var $numberWords = $('<p class="number-words">Remaining: <span name="number">' + maxLength + '</span></p>').insertAfter($this);

		$this.on('paste keydown keyup', function() {
			var length = $this.val().length;

			if (length > maxLength) {
				$this.val($this.val().substring(0, maxLength));
				length = maxLength;
			}
			$numberWords.find('span[name="number"]').text(maxLength - length);
		}).trigger('keyup');
	});

//	$.get("/data/count", function(data) {
//		var result = data.data;
//		$('#memberCount').text(result.member);
//		$('#categoryCount').text(result.category);
//		$('#faqCount').text(result.faqCount);
//		$('#commentCount').text(result.comment);
//	});

	$(document).ajaxError(function(event, request, settings, error) {
		if (!error) {
			return false;
		}
		if (request.status != 200) {
			$.alert({
				theme : 'white',
				closeIcon : true,
				closeIconClass : 'fa fa-times',
				icon : 'fa fa-warning text-danger',
				title : '错误',
				content : '页面异步请求出错: ' + request.statusText,
				buttons : {
					close : {
						text : '<i class="fa fa-times"></i> 关闭',
						btnClass : 'btn-danger',
						action : $.afterAjaxError
					}
				}
			});
			return false;
		}
		if (request.responseJSON.code && 4001 == request.responseJSON.code) {
			$.alert({
				theme : 'white',
				closeIcon : true,
				closeIconClass : 'fa fa-warning',
				icon : 'fa fa-warning text-danger',
				title : '未授权的访问!',
				content : request.responseJSON.errMsg,//error,
				buttons : {
					ok: {
						text: '前往登录',
						btnClass: 'btn-success',
						action: function() {
							location.href = "/login.html";
						}
					},
					close : {
						text : '<i class="fa fa-times"></i> 关闭',
						btnClass : 'btn-danger',
						action : $.afterAjaxError
					}
				}
			});
			return false;
		}
	});

	var $pwdForm = $('#resetPasswdForm');
	$pwdForm.formValidation({
		framework : 'bootstrap',
		excluded : ':disabled',
		message : 'This value is not valid',
		icon : {
			valid : 'fa fa-check',
			invalid : 'fa fa-times',
			validating : 'fa fa-refresh'
		},
		fields : {
			origPassword : {
				validators : {
					notEmpty : {
						message : 'The original password is required'
					},
					stringLength : {
						max : 18,
						message : 'The original password must be less than 18 characters long'
					}
				}
			},
			password: {
                validators: {
                    notEmpty: { message: 'The username is required.' },
                    regexp: {
                        regexp: /^[a-zA-Z\d_]{6,18}$/,
                        message: 'The full name can consist of alphabetical characters and spaces only'
                    }
                }
			},
            repassword: {
                validators: { 
                	notEmpty: { message: 'The name is required' },
                	identical: {
                        field: 'password',
                        message: 'The password and its confirm are not the same'
                    }
                }
            }
		}
	}).on('err.field.fv', function(e, data) {
		data.fv.disableSubmitButtons(false);

	}).on('success.field.fv', function(e, data) {
		data.element.parents('.has-success').removeClass('has-success');
		data.element.data('fv.icon').hide();
		data.fv.disableSubmitButtons(false);

	}).on('success.form.fv', function(e) {
		e.preventDefault();

		$(e.target).ajaxSubmit({
			url : '/user/password/update',
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (!data.success) {
					$.alert({
						icon : 'fa fa-warning',
						title : '错误提示',
						content : data.message,
						buttons : {
							close : {
								text : '关闭',
								btnClass : 'btn-danger'
							}
						}
					});
					return false;
				}
			    $.alert({
			        title: 'Success!',
			        content: '密码重置成功!',
			        buttons : {
						close : {
							text : '确定',
							btnClass : 'btn-primary'
						}
					}
			    });
			    $pwdForm.find('[data-dismiss="modal"]').trigger('click');
			}
		});
	});
	$('#resetPasswdModal').on('show.bs.modal', function() {
		$pwdForm.get(0).reset();
		$pwdForm.formValidation('resetForm');
	});
});