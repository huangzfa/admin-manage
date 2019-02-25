; (function($, window, document) {
	(function(){
		var pluginName="shellidea_layout_LR";
		var _config={
			leftWidth:0,
			hideBarWidth:8,
			hasHideBar:true,
			speed:300,
			onHideBarBefer:null,
			onHideBarAfter:null
		}
		
		var layout_LR=function(jq,config){
			this.jq=jq;
			this.leftLayout=jq.children(".si-layout-left");
			this.rightLayout=jq.children(".si-layout-right");
			this.config=$.extend({},_config,config);
			this.init();
		};
		
		layout_LR.fn=layout_LR.prototype={
			init:function(){
				this.initLayout();
				this.initEvent();
			},
			initLayout:function(){
				if(this.config.leftWidth>0){
					this.leftWidth=this.config.leftWidth;
					this.leftLayout.css({"width":this.leftWidth+"px"});
					this.rightLayout_MarginLeft=this.leftWidth+1;
				}else{
					this.leftWidth=parseInt(this.leftLayout.css("width"));
				}
				if(this.config.hasHideBar){
					this.leftLayout.wrap("<div class='si-layout-left-box'></div>");
					this.leftLayoutBox=this.leftLayout.parent();
					this.leftLayoutBoxWidth=this.leftWidth+this.config.hideBarWidth;
					this.leftLayoutBox.css({"width":this.leftLayoutBoxWidth+"px"});
					this.leftLayoutBox.append("<div class='hideBar'><i class='fa fa-caret-left'></i></div>");
					this.hideBar=this.leftLayoutBox.children(".hideBar");
					this.hideBar.css({"width":this.config.hideBarWidth+"px"});
					this.rightLayout_MarginLeft=this.leftWidth+this.config.hideBarWidth+1;
				}
				this.rightLayout.css({"margin-left":this.rightLayout_MarginLeft+"px"});
			},
			initEvent:function(){
				var that=this;
				var speed=this.config.speed;
				if(this.hideBar){
					this.hideBar.click(function(){
						if(that.config.onHideBarBefer&&(typeof that.config.onHideBarBefer=='function')){
							that.config.onHideBarBefer.call(this);
						}
						var fa=$(this).children(".fa");
						if(fa.hasClass("fa-caret-left")){
							that.leftLayoutBox.animate({width: $(this).css("width")}, speed,function(){
								fa.removeClass("fa-caret-left");
								fa.addClass("fa-caret-right");
							});
							that.leftLayoutBox.siblings().animate({marginLeft:$(this).css("width")}, speed,function(){
								if(that.config.onHideBarAfter&&(typeof that.config.onHideBarAfter=='function')){
									that.config.onHideBarAfter.call(this);
								}
							});
						}
					});
					this.hideBar.mouseover(function(){
						if(that.config.onHideBarBefer&&(typeof that.config.onHideBarBefer=='function')){
							that.config.onHideBarBefer.call(this);
						}
						var fa=$(this).children(".fa");
						if(fa.hasClass("fa-caret-right")){
							that.leftLayoutBox.animate({width: that.leftLayoutBoxWidth+"px"}, speed,function(){
								fa.removeClass("fa-caret-right");
								fa.addClass("fa-caret-left");
							});
							that.leftLayoutBox.siblings().animate({marginLeft:that.rightLayout_MarginLeft+"px"}, speed,function(){
								if(that.config.onHideBarAfter&&(typeof that.config.onHideBarAfter=='function')){
									that.config.onHideBarAfter.call(this);
								}
							});
						}
					});
				}
			}
		};
		
		$.fn[pluginName]=function(config){
			if(this.length!=1){
				throw new Error("must one");
			}
			if(!$.data(this[0],pluginName)){
				$.data(this[0],pluginName,new layout_LR(this,config));
			}
			return this;
		};
		$.fn.getShellidea_layout_LR=function(){
			return $.data(this[0],pluginName);
		}
	})();
})(jQuery, window, document);