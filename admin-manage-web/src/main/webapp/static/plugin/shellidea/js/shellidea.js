$(document).ready(function() {
	try{
		//所有下拉框使用select2
		$(".select2").select2({theme: "classic"});
	}catch(e){
		// blank
	}
	$('.ms-input-number').on('input propertychange keyup', function() {
		$(this).val($(this).val().replace(/[^\d]/ig, ''));
	});
	
	//文本框输入计数
	$('.ms-textarea').on('input propertychange keyup', function() {
		var _this = $(this);
		var maxlength=parseInt(_this.attr('maxlength'));
		var _val = _this.val();
		var length = _val.length;
		_this.siblings('.ms-textarea-counter').children('.count').text(length + "/"+maxlength);
		if(length >= maxlength) {
			_this.val(_val.substring(0, maxlength));
		}
	});
	//select值回显
	$.each($('.backValSelect'),function(i,o){
		var _this=$(o),backVal=_this.attr('data-backVal');
		if(backVal){
			_this.find('option[value="'+backVal+'"]').attr({selected:'selected'});
		}
		
	});	
});

// 获取URL地址参数
function getQueryString(name, url) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    if (!url || url == ""){
	    url = window.location.search;
    }else{	
    	url = url.substring(url.indexOf("?"));
    }
    r = url.substr(1).match(reg)
    if (r != null){ 
    	return unescape(r[2]); 
    }
    return null;
}

//获取字典标签
function getDictLabel(data, value, defaultValue){
	for (var i=0; i<data.length; i++){
		var row = data[i];
		if (row.value == value){
			return row.label;
		}
	}
	return defaultValue;
}

// 打开一个窗体
function windowOpen(url, name, width, height){
	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top=10,left="+left;
	window.open(url ,name , options);
}

// 恢复提示框显示
function resetTip(){
	top.$.jBox.tip.mess = null;
}

// 关闭提示框
function closeTip(){
	top.$.jBox.closeTip();
}

//显示提示框
function showTip(mess, type, timeout, lazytime){
	resetTip();
	setTimeout(function(){
		top.$.jBox.tip(mess, (type == undefined || type == '' ? 'info' : type), {opacity:0, 
			timeout:  timeout == undefined ? 2000 : timeout});
	}, lazytime == undefined ? 500 : lazytime);
}

// 显示加载框
function loading(mess){
	if (mess == undefined || mess == ""){
		mess = "正在提交，请稍等...";
	}
	resetTip();
	top.$.jBox.tip(mess,'loading',{opacity:0});
}

// 警告对话框
function alertx(mess, closed){
	top.$.jBox.info(mess, '提示', {closed:function(){
		if (typeof closed == 'function') {
			closed();
		}
	}});
}

// 确认对话框
function confirmx(mess, href, closed){
	top.$.jBox.confirm(mess,'系统提示',function(v,h,f){
		if(v=='ok'){
			if (typeof href == 'function') {
				href();
			}else{
				resetTip(); //loading();
				location = href;
			}
		}
	},{buttonsFocus:1, closed:function(){
		if (typeof closed == 'function') {
			closed();
		}
	}});
	//top.$('.jbox-body .jbox-icon').css('top','40px');
	return false;
}

// 提示输入对话框
function promptx(title, lable, href, closed){
	top.$.jBox("<div class='form-search' style='padding:20px;text-align:center;'>" + lable + "：<input type='text' id='txt' name='txt'/></div>", {
			title: title, submit: function (v, h, f){
	    if (f.txt == '') {
	        top.$.jBox.tip("请输入" + lable + "。", 'error');
	        return false;
	    }
		if (typeof href == 'function') {
			href();
		}else{
			resetTip(); //loading();
			location = href + encodeURIComponent(f.txt);
		}
	},closed:function(){
		if (typeof closed == 'function') {
			closed();
		}
	}});
	return false;
}

// 添加TAB页面
function addTabPage(title, url, closeable, $this, refresh){
	top.$.fn.jerichoTab.addTab({
        tabFirer: $this,
        title: title,
        closeable: closeable == undefined,
        data: {
            dataType: 'iframe',
            dataLink: url
        }
    }).loadData(refresh != undefined);
}

// cookie操作
function cookie(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
}

// 日期加减
function addDate(date, dadd){  
	date = date.valueOf();
	date = date + dadd * 24 * 60 * 60 * 1000;
	return new Date(date);  
}

//截取字符串，区别汉字和英文
function abbr(name, maxLength){  
 if(!maxLength){  
     maxLength = 20;  
 }  
 if(name==null||name.length<1){  
     return "";  
 }  
 var w = 0;//字符串长度，一个汉字长度为2   
 var s = 0;//汉字个数   
 var p = false;//判断字符串当前循环的前一个字符是否为汉字   
 var b = false;//判断字符串当前循环的字符是否为汉字   
 var nameSub;  
 for (var i=0; i<name.length; i++) {  
    if(i>1 && b==false){  
         p = false;  
    }  
    if(i>1 && b==true){  
         p = true;  
    }  
    var c = name.charCodeAt(i);  
    //单字节加1   
    if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {  
         w++;  
         b = false;  
    }else {  
         w+=2;  
         s++;  
         b = true;  
    }  
    if(w>maxLength && i<=name.length-1){  
         if(b==true && p==true){  
             nameSub = name.substring(0,i-2)+"...";  
         }  
         if(b==false && p==false){  
             nameSub = name.substring(0,i-3)+"...";  
         }  
         if(b==true && p==false){  
             nameSub = name.substring(0,i-2)+"...";  
         }  
         if(p==true){  
             nameSub = name.substring(0,i-2)+"...";  
         }  
         break;  
    }  
 }  
 if(w<=maxLength){  
     return name;  
 }  
 return nameSub;  
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};
// 金额是以分为单位
function JE_format(price) {
	price = price / 100.0;
	return parseFloat(price.toFixed(2));
};

/**
 * 加法运算，避免数据相加小数点后产生多位数和计算精度损失。
 * 
 * @param num1加数1 |
 *            num2加数2
 */
function numAdd(num1, num2) {
	var baseNum, baseNum1, baseNum2;
	try {
		baseNum1 = num1.toString().split(".")[1].length;
	} catch (e) {
		baseNum1 = 0;
	}
	try {
		baseNum2 = num2.toString().split(".")[1].length;
	} catch (e) {
		baseNum2 = 0;
	}
	baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));
	return (num1 * baseNum + num2 * baseNum) / baseNum;
};
/**
 * 减法运算，避免数据相减小数点后产生多位数和计算精度损失。
 * 
 * @param num1被减数 |
 *            num2减数
 */
function numSub(num1, num2) {
	var baseNum, baseNum1, baseNum2;
	var precision;// 精度
	try {
		baseNum1 = num1.toString().split(".")[1].length;
	} catch (e) {
		baseNum1 = 0;
	}
	try {
		baseNum2 = num2.toString().split(".")[1].length;
	} catch (e) {
		baseNum2 = 0;
	}
	baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));
	precision = (baseNum1 >= baseNum2) ? baseNum1 : baseNum2;
	return ((num1 * baseNum - num2 * baseNum) / baseNum).toFixed(precision);
};
/**
 * 乘法运算，避免数据相乘小数点后产生多位数和计算精度损失。
 * 
 * @param num1被乘数 |
 *            num2乘数
 */
function numMulti(num1, num2) {
	var baseNum = 0;
	try {
		baseNum += num1.toString().split(".")[1].length;
	} catch (e) {
	}
	try {
		baseNum += num2.toString().split(".")[1].length;
	} catch (e) {
	}
	return Number(num1.toString().replace(".", ""))
			* Number(num2.toString().replace(".", "")) / Math.pow(10, baseNum);
};
/**
 * 除法运算，避免数据相除小数点后产生多位数和计算精度损失。
 * 
 * @param num1被除数 |
 *            num2除数
 */
function numDiv(num1, num2) {
	var baseNum1 = 0, baseNum2 = 0;
	var baseNum3, baseNum4;
	try {
		baseNum1 = num1.toString().split(".")[1].length;
	} catch (e) {
		baseNum1 = 0;
	}
	try {
		baseNum2 = num2.toString().split(".")[1].length;
	} catch (e) {
		baseNum2 = 0;
	}
	with (Math) {
		baseNum3 = Number(num1.toString().replace(".", ""));
		baseNum4 = Number(num2.toString().replace(".", ""));
		return (baseNum3 / baseNum4) * pow(10, baseNum2 - baseNum1);
	}
};

(function(window,$){
	window.hjnUtils={
		ajax:function(settings){
			var layerIndex=top.layer.msg('处理中...', {
				  icon: 16,
				  time: 0,
				  shade: 0.1
				});
			var _success=settings.success;
			settings.success=function(data, textStatus, jqXHR){
				top.layer.close(layerIndex);
				if(401==data.code){
					top.layer.alert(data.msg, {
						icon: 5,
						end: function(){ 
						    location.href=data.url;
					    }
					});
					/**
					layer.msg(data.msg, {
					  icon: 5,
					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
					}, function(){
						location.href=data.url;
					});  
					 */
					return;
				}
				_success(data, textStatus, jqXHR);
			}
			var _error=settings.error;
			settings.error=function(XMLHttpRequest, textStatus, errorThrown) {
				top.layer.close(layerIndex);
				if (_error) {
					_error(XMLHttpRequest, textStatus, errorThrown);
				}
	        	top.layer.alert(textStatus+"<>"+errorThrown, {icon: 5});
	        } 
			$.ajax(settings);
		},
		/**
		 * 去掉字符串前后的空格
		 * @param str 入参:要去掉空格的字符串
		 */
		trim:function(str){
		     return $.trim(str); 
		},
		isEmpty:function(str){
		     return $.trim(str)==""; 
		},
		isNotEmpty:function(str){
		     return $.trim(str)!=""; 
		},
		nullToStr:function(str){
			return str==null?"":str;
		},
		nullToZero:function(str){
			return str==null?0:str;
		},
		/**
		 * 判断是否是数字
		 * @param value
		 * @returns {Boolean}
		 */
		isNum:function(value){
		    if(value!=null&&$.trim(value)!=""&&isNaN(value)==false){
		        return true;
		    }else{
		        return false;
		    }
		},
		/**
		 * 判断是否是中文
		 * @param str
		 * @returns {Boolean}
		 */
		isChine:function(str){
		    var reg = /^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/;
		    if(reg.test(str)){
		        return false;
		    }
		    return true;
		},
		writeObj:function(obj){ 
			 var description = ""; 
			 for(var i in obj){ 
			  var property=obj[i]; 
			  description+=i+" = "+property+"\n"; 
			 } 
			 return description; 
		}
	};
})(window,jQuery);