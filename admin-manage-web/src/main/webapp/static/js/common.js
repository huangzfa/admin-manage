/**
 * 判断是不是小数
 * @param input
 * @param s  保留几位小数
 * @returns {boolean}
 */
function checkFloat(input,s) {
    var p = input.split(".");
    if( p.length >2 ){
        return false;
    }
    var re = /^[0-9]*]*$/;
    if( !re.test(p[0])){
        return false;
    }
    re = /^[0-9]*]*$/;
    if (p.length>1 && !re.test(p[1])) {
        return false;
    }
    if(p.length>1 && p[1].length>s){
        return false;
    }
    return true;
}

/**
 * 判断是不是正整数
 * @param val
 * @returns {boolean}
 */
function isNumber(val){
    val  = val + "";
    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)){
        if( val.indexOf("0") ==0 ){
            return false;
        }
        return true;
    }else{
        return false;
    }
}
/**
 * 验证是不是012345678开头
 * @param val
 * @returns {boolean}
 */
function is01(val) {
    val  = val + "";

    if( val.indexOf("0") == 0 && val.indexOf("0.")<0 ){
        return false;
    }else{
        return true;
    }
}
(function(window){
	function _package( _sPackage ){
	    var nps = _sPackage.split('.'); 
	    var nowScope = window; 
	    for(var i=0 ; i<nps.length ; i++){
			if(!nowScope[nps[i]]){
			    nowScope[nps[i]] = {}; 
			}
	        nowScope = nowScope[nps[i]];
	    }
	}
	function regCheck(reg,val){
		return reg.test(val);
	}
	//===============================================
	var loadIndex;
	function loading(){
		loadIndex = layer.load(2, {
			time:0,
			shade: [0.1,'#000'] //0.1透明度的白色背景
		});
	}
	function closeLoading(){
		layer.close(loadIndex);
	}
	//===============================================
	function isMobile() {
	    var sUserAgent = navigator.userAgent.toLowerCase();
	    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
	    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
	    var bIsMidp = sUserAgent.match(/midp/i) == "midp";
	    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
	    var bIsAndroid = sUserAgent.match(/android/i) == "android";
	    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
	    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
	    if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
	        return true;
	    } else {
	        return false;
	    }
	}
	function isWeiXinClient(){
	    var ua = window.navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	        return true;
	    }else{
	        return false;
	    }
	}

	//===============================================
	window._utils={
		package:_package,
		regex:{
			isEmpty:function(val){
				return regCheck(/^\s*$/g,val);
			},
			isMobile:function(val){
				return regCheck(/^1[34578]\d{9}$/,val);
			},
			isPhone:function(val){
				return regCheck(/^\d{3,4}-?\d{7,9}$/,val);
			},
			isAlnum:function(val){
				return regCheck(/^[a-zA-Z0-9]+$/,val);
			},
			isAlnumCn:function(val){
				return regCheck(/^[0-9a-zA-Z\u4E00-\u9FA5_]+$/,val);
			},
			isIdcard:function(val){
				return regCheck(/^\d{15}(\d{2}[0-9xX])?$/,val);
			},
			isEmail:function(val){
				return regCheck(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,val);
			}
		},
		cookie:{
			isEnabled:function(){
				return navigator.cookieEnabled;
			},
			set:function(name, value, options){
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
			    }
			},
			get:function(name){
				var cookieValue = null;
		        if (document.cookie && document.cookie != '') {
		            var cookies = document.cookie.split(';');
		            for (var i = 0; i < cookies.length; i++) {
		                var cookie = $.trim(cookies[i]);
		                if (cookie.substring(0, name.length + 1) == (name + '=')) {
		                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
		                    break;
		                }
		            }
		        }
		        return cookieValue;
			},
			del:function(name){
				set(name,'',{expires:-1});
			},
			has:function(name){
				if (document.cookie && document.cookie != '') {
		            var cookies = document.cookie.split(';');
		            for (var i = 0; i < cookies.length; i++) {
		                var cookie = $.trim(cookies[i]);
		                if (cookie.substring(0, name.length + 1) == (name + '=')) {
		                    return true;
		                }
		            }
		        }
				return false;
			}
		},
		loading:loading,
		closeLoading:closeLoading,
		isMobile:isMobile,
		isWeiXinClient:isWeiXinClient
	}
})(window);
