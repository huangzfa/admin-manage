(function($, win) {
	var defaults = {
		id: undefined,
		initStep: 1,
		stepClick: undefined
	}
	var MsStep = function(setting) {
		var _t = this;
		var cf = _t.cf = {};
		$.extend(_t.cf, defaults, setting);
		if(!cf.id) {
			throw 'id is required';
		}
		_t.init();
	}
	win.MsStep = MsStep;
	var proto = MsStep.prototype;
	proto.init = function() {
		var _t = this;
		var cf = _t.cf;
		var $stepBody = $(cf.id);
		var size = $stepBody.find("li").length;
		var barWidth = cf.initStep < size ? 100 / (2 * size) + 100 * (cf.initStep - 1) / size : 100;
		if(size < cf.initStep) {
			cf.initStep = size;
		}
		_t.size = size;
		_t.curPage = cf.initStep;//当前页=初始页
		_t.realPage = cf.initStep;//实际页=初始页
		_t.stepBody = $stepBody;

		$stepBody.prepend("<div class=\"step-bar\"><div class=\"step-bar-active\"></div></div>");

		$stepBody.find("li").each(function(i, li) {
			if(i < cf.initStep) {
				$(li).addClass("step-active");
			}
			$(li).prepend('<span class="step-item"><a class="step-num" href="javascript:void(0);" step="' + (i + 1) + '">' + (i + 1) + '</a></span>');
		});
		$stepBody.find("li").css({
			"width": 100 / size + "%"
		});
		$stepBody.find(".step-bar-active").animate({
				"width": barWidth + "%"
			},
			100);
			
		$stepBody.on("click", ".step-num", function() {
			if(cf.stepClick && typeof cf.stepClick === 'function') {
				var step=parseInt($(this).attr('step'));
				if(step>_t.realPage){
					return false;
				}
				_t.curPage = step;
				cf.stepClick.call(_t,step);
			}
		});
		$stepBody.find('.step-num').eq(cf.initStep==size?0:cf.initStep-1).click();
	}
	proto.nextStep = function() {
		var _t = this;
		if(_t.curPage >= _t.size) {
			return false;
		}
		return this.goStep(_t.curPage + 1);
	}

	proto.preStep = function() {
		var _t = this;
		if(_t.curPage <= 1) {
			return false;
		}
		return this.goStep(_t.curPage - 1);
	}

	proto.goStep = function(page) {
		if(page == undefined || isNaN(page) || page < 0) {
			if(window.console && window.console.error) {
				console.error('the method goStep has a error,page:' + page);
			}
			return false;
		}
		var _t = this;
		_t.curPage=page;
		if(page>_t.realPage){
			_t.realPage = page;
			_t.stepBody.find("li").each(function(i, li) {
				$li = $(li);
				$li.removeClass('step-active');
				if(i < page) {
					$li.addClass('step-active');
				}
			});
			barWidth = page < _t.size ? 100 / (2 * _t.size) + 100 * (page - 1) / _t.size : 100;
			_t.stepBody.find(".step-bar-active").animate({
					"width": barWidth + "%"
				},
				100);
		}
		if(_t.cf.stepClick && typeof _t.cf.stepClick === 'function') {
			_t.cf.stepClick.call(_t,_t.curPage);
		}
		return true;
	}
})(jQuery, window);