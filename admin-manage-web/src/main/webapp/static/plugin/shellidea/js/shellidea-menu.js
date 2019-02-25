; (function($, window, document) {
	(function(){
		var pluginName = "shellideaMenu";
	    var defaults = {
	        speed: 300,
	        showDelay: 0,
	        hideDelay: 0,
	        singleOpen: true,
	        clickEffect: true,
	        click:null
	    };
	    function Plugin(element, options) {
	        this.element = element;
	        this.settings = $.extend(defaults, options);
	        this._name = pluginName;
	        this.init();
	    };
	    $.extend(Plugin.prototype, {
	        init: function() {
	            this.openSubmenu();
	            this.submenuIndicators();
	            if (this.settings.clickEffect) {
	                this.addClickEffect();
	            }
	        },
	        showSubmenu: function(obj){
	        	var that=this;
	        	if ($(obj).children(".submenu").length > 0) {
                    if ($(obj).children(".submenu").css("display") == "none") {
                        $(obj).children(".submenu").delay(that.settings.showDelay).slideDown(that.settings.speed);
                        $(obj).children(".submenu").siblings("a").addClass("submenu-indicator-minus");
                        if (that.settings.singleOpen) {
                            $(obj).siblings().children(".submenu").slideUp(that.settings.speed);
                            $(obj).siblings().children(".submenu").siblings("a").removeClass("submenu-indicator-minus");
                        }
                        //$(obj).parent().children("li.active").removeClass("active");
						//$(obj).addClass("active");
                        return false;
                    }
                    return false;
                }
	        },
	        openSubmenu: function() {
	        	var that=this;
	        	/*一级菜单li点击事件*/
	            $(this.element).children("ul").find("li").bind("click touchstart",function(e) {
	                e.stopPropagation();
	                e.preventDefault();
	                if ($(this).children(".submenu").length > 0) {
	                    if ($(this).children(".submenu").css("display") == "none") {
	                        $(this).children(".submenu").delay(that.settings.showDelay).slideDown(that.settings.speed);
	                        $(this).children(".submenu").siblings("a").addClass("submenu-indicator-minus");
	                        if (that.settings.singleOpen) {
	                            $(this).siblings().children(".submenu").slideUp(that.settings.speed);
	                            $(this).siblings().children(".submenu").siblings("a").removeClass("submenu-indicator-minus");
	                        }
	                        //$(this).parent().children("li.active").removeClass("active");
							//$(this).addClass("active");
							/*点击二级菜单自动选中子菜单的第一个菜单*/
							//$(this).children(".submenu").children("li:first").click();
	                        return false;
	                    } else {
	                        $(this).children(".submenu").delay(that.settings.hideDelay).slideUp(that.settings.speed);
	                    }
	                    if ($(this).children(".submenu").siblings("a").hasClass("submenu-indicator-minus")) {
	                        $(this).children(".submenu").siblings("a").removeClass("submenu-indicator-minus");
	                    }
	                    return false;
	                }else{
	                	//$(this).parent().children("li.active").removeClass("active");
						//$(this).addClass("active");
						if(that.settings.click&&(typeof that.settings.click=='function')){
							that.settings.click.call(this);
						}
	                }
	            });
	        },
	        submenuIndicators: function() {
	            if ($(this.element).find(".submenu").length > 0) {
	                $(this.element).find(".submenu").siblings("a").append("<span class='submenu-indicator'><i class='fa fa-chevron-up normal'></i></span>");
	            }
	        },
	        addClickEffect: function() {
	            var ink, d, x, y;
	            $(this.element).find("a").bind("click touchstart",function(e) {
	                $(".shellidea-ink").remove();
	                if ($(this).parent().children(".shellidea-ink").length === 0) {
	                    $(this).parent().prepend("<span class='shellidea-ink'></span>");
	                }
	                ink = $(this).parent().find(".shellidea-ink");
	                ink.removeClass("animate-shellidea-ink");
	                if (!ink.height() && !ink.width()) {
	                    d = Math.max($(this).outerWidth(), $(this).outerHeight());
	                    ink.css({
	                        height: d/2,
	                        width: d/2
	                    });
	                }
	                x = e.pageX - $(this).offset().left - ink.width() / 2;
	                y = e.pageY - $(this).offset().top - ink.height() / 2;
	                ink.css({
	                    top: y + 'px',
	                    left: x + 'px'
	                }).addClass("animate-shellidea-ink");
	            })
	        }
	    });
	    $.fn[pluginName] = function(options) {
//	        this.each(function() {
//	            if (!$.data(this, "plugin_" + pluginName)) {
//	            	var menuPlugin=new Plugin(this, options);
//	                $.data(this, "plugin_" + pluginName, menuPlugin);
//	            }
//	        });
	        return new Plugin(this, options);
	    }
	})();
	
    (function(){
    	var defaults = {
	        click:null
	    };
	    function Plugin(element, options) {
	        this.element = element;
	        this.settings = $.extend(defaults, options);
	        this.init();
	    };
	    $.extend(Plugin.prototype, {
	        init: function() {
	        	this.initEvents();
	        },
	        initEvents: function(){
	        	var that=this;
	        	$(this.element).children(".nav-bar").find(".menuA").bind("click touchstart",function(e) {
	                e.stopPropagation();
	                e.preventDefault();
	                $(this).siblings().removeClass("active");
					$(this).addClass("active");
					if(that.settings.click&&(typeof that.settings.click=='function')){
						that.settings.click.call(this);
					}
	            });
	        }
	    });
	    $.fn.shellideaHeader = function(options) {
	        return new Plugin(this, options);
	    }
    })();

})(jQuery, window, document);