(function(){
	/*
	   //属性  
	   var appCodeName= window.navigator.appCodeName;//返回与浏览器相关的内部代码名  都为Mozilla  
	   var appName=navigator.appName;//返回浏览器正式名称  均为Netscape  
	   var appVersion=navigator.appVersion;//返回浏览器版本号  
	   var cookieEnabled=navigator.cookieEnabled;//返回浏览器是否启用cookie，true和false  
	   var geolocation=navigator.geolocation;//返回地理定位信息(h5)  
	   var javaEnabled=navigator.javaEnabled();//检测当前浏览器是否支持 Java，从而知道浏览器是否能显示 Java 小程序(IE,chrome返回true，firefox返回false)  
	   var language=navigator.language;//返回浏览器的首选语言  
	   var mimeTypes= navigator.mimeTypes;//返回浏览器支持的Mime类型  
	   var msManipulationViewsEnabled= navigator.msManipulationViewsEnabled;//仅支持IE，true  
	   var msMaxTouchPoints=navigator.msMaxTouchPoints;//字面意思是最大的触摸点，IE为0，其他不支持  
	   var msPointerEnabled=navigator.msPointerEnabled;//IE为true，其他不支持  
	   var onLine=navigator.onLine;//是否连接互联网，均返回true(未断网)  
	   var platform=navigator.platform;//所在平台，返回win32  
	   var plugins=navigator.plugins;//返回浏览器插件集合  
	   var preference=navigator.preference;//允许一个已标识的脚本获取并设置特定的 Navigator 参数  
	   var product= navigator.product;//浏览器产品名，返回gecko  
	   var systemLanguage=navigator.systemLanguage;//获取系统语言，IE支持，返回zh-cn  
	   var userAgent=navigator.userAgent;//判断浏览器类型  
	   var userLanguage=navigator.userLanguage;//返回操作系统的自然语言设置,IE支持，返回zh-cn  
	   //方法  
	   var msLaunchUri=navigator.msLaunchUri;//回调函数，未研究  
	   var taintEnabled=navigator.taintEnabled;//回调函数  
	   var hasOwnProperty=navigator.hasOwnProperty;//意思是是否支持属性，用法如下  
	   var s=document.hasOwnProperty("ontouchstart");//电脑返回false，手机为true  
	 * */
	var _IE = (navigator.appName.toUpperCase().indexOf('MICROSOFT')!=-1);
	var _IE6 = (navigator.userAgent.toUpperCase().indexOf('MSIE 6.0')!=-1);
	var _IE7 = (navigator.userAgent.toUpperCase().indexOf('MSIE 7.0')!=-1);
	var _OPERA = (window.opera!=null);
	var _MOZILLA = !_IE&&!_OPERA&&(navigator.userAgent.indexOf("Mozilla")>=0);
	var _GECKO = (navigator.product == "Gecko")?true:false;
	var _SAFARI = navigator.userAgent.toLowerCase().indexOf("safari")>=0;
	var _GECKOLIKE = _GECKO||_SAFARI||_OPERA;
	var _BrowserVersion = parseFloat(window.navigator.appVersion);
	var _LoadedClass = {};
	var _basePath=$basePath();
	var _jscss={};
	_jscss['css']=['/css/common.css'];
	_jscss['jquery']=['/js/jquery-1.8.3.min.js'];
	_jscss['jquery1.11.3']=['/js/jquery-1.11.3.min.js'];
	_jscss['json']=['/js/json2.min.js'];
	_jscss['map']=['/js/Map.js'];
	_jscss['pageList']=['/js/pageList.js'];
	_jscss['dict']=['/js/dict.js'];
	_jscss['xmlENDEC']=['/js/xmlENDEC.js'];
	_jscss['webfont']=['/webfont/font-awesome/css/font-awesome.min.css'];
	_jscss['bootstrap']=['/plugin/bootstrap/2.3.1/css_default/bootstrap.min.css',
	                     '/plugin/bootstrap/2.3.1/css_default/bootstrap-responsive.min.css',
	                     '/plugin/bootstrap/2.3.1/js/bootstrap.min.js'];
	/**
	_jscss['bootstrap']=['/plugin/bootstrap/3.3.5/css/bootstrap.min.css',
	                     '/plugin/bootstrap/3.3.5/js/bootstrap.min.js'];
	 **/
	_jscss['si']=['/plugin/shellidea/css/shellidea.css',
	              '/plugin/shellidea/js/shellidea.js'];
	_jscss['siMenu']=['/plugin/shellidea/css/shellidea-menu.css',
	                  '/plugin/shellidea/js/shellidea-menu.js'];
	_jscss['siLayout']=['/plugin/shellidea/css/shellidea-layout.css',
	                    '/plugin/shellidea/js/shellidea-layout.js'];
	_jscss['easyui']=['/plugin/easyui/themes/bootstrap/easyui.css',
                        '/plugin/easyui/themes/icon.css',
                        '/plugin/easyui/jquery.easyui.min.js'];
	_jscss['select2']=['/plugin/jquery-select2/3.4/select2.min.css',
	                   '/plugin/jquery-select2/3.4/select2.min.js'];
	_jscss['layer']=['/plugin/layer/layer.js'];
	_jscss['jbox']=['/plugin/jbox-v2.3/jBox/Skins/Gray/jbox.css',
                    '/plugin/jbox-v2.3/jBox/jquery.jBox-2.3.min.js',
                    '/plugin/jbox-v2.3/jBox/i18n/jquery.jBox-zh-CN.js'];
	_jscss['zTree']=['/plugin/zTree_v3/zTreeStyle/zTreeStyle.css',
	                 '/plugin/zTree_v3/jquery.ztree.all-3.5.min.js',
	                 '/plugin/zTree_v3/jquery.ztree.exhide-3.5.min.js'];
	_jscss['validation']=['/plugin/jquery-validation/1.14.0/jquery.validate.css',
                          '/plugin/jquery-validation/1.14.0/jquery.validate.min.js',
                          '/plugin/jquery-validation/1.14.0/localization/messages_zh.js',
                          '/plugin/jquery-validation/custom.validator.method.js'];
	_jscss['hjn']=['/js/hjnUtils.js'];
	_jscss['ajaxfileupload']=['/plugin/uploadFile/ajaxfileupload.js'];
    _jscss['ocupload']=['/plugin/ocupload/ocupload.js'];
	_jscss['pano']=['/plugin/pano/api-1.19-pr6.js'];
	_jscss['ueditor']=['/plugin/ueditor143/ueditor.config.js',
	                   '/plugin/ueditor143/ueditor.all.min.js',
	                   '/plugin/ueditor143/lang/zh-cn/zh-cn.js'];
	_jscss['webuploader']=['/plugin/webuploader/webuploader.css',
	                       '/plugin/webuploader/webuploader.min.js'];
	_jscss['97Date']=['/plugin/My97DatePicker/WdatePicker.js'];
	_jscss['wheelmenu']=['/plugin/wheelmenu/jquery.wheelmenu.js'];
	_jscss['highcharts']=['/plugin/highcharts/highcharts.js'];
	_jscss['rangeSlider']=['/plugin/rangeSlider/css/ion.rangeSlider.css',
	                       '/plugin/rangeSlider/css/ion.rangeSlider.skinModern.css',
	                       '/plugin/rangeSlider/js/ion.rangeSlider.min.js'];
	_jscss['sortable']=['/plugin/sortable/Sortable.min.js'];
	_jscss['base64']=['/js/jbase64.js'];
	_jscss['resStore']=['/plugin/resStore/resStore.js?x=1x',
	                    '/plugin/resStore/resStore.css'];
	_jscss['photoswipe']=['/plugin/PhotoSwipe/photoswipe.css',
                      '/plugin/PhotoSwipe/default-skin/default-skin.css',
                      '/plugin/PhotoSwipe/photoswipe.min.js',
                      '/plugin/PhotoSwipe/photoswipe-ui-default.min.js'];
	_jscss['step']=['/plugin/step/ms-step.js',
	                    '/plugin/step/ms-step.css'];
	function $basePath(){
		return '/static';
	}
	
	$include();
	function $include(){
		var basejs='base.js';
		var scripts= document.getElementsByTagName('script');
		var base= false;
		for(var i=0 ; i<scripts.length ; i++){
			var src = scripts[i].getAttribute('src',2);
			if (src) {
				//src=src.replace(/(^\s+)|(\s+$)/g,"");//lr trim
				src=src.replace(/\s/g,'');
				var paramIndex=src.indexOf('?');
				if(paramIndex!=-1){
					var osrc=src.substring(0,paramIndex);
				}
				if(osrc.length==(osrc.indexOf(basejs)+basejs.length)){
					if (src.lastIndexOf('mod=')!=-1) {
						var modStr=src.substring(src.lastIndexOf('mod=')+4);
						if (modStr) {
							var modArr=modStr.split(',');
							for ( var i in modArr) {
								$import(modArr[i]);
							}
						}
					}
					break;
				}
			}
		}
		return base;
	}
	
	function $package( _sPackage ){
	    var nps = _sPackage.split('.'); 
	    var nowScope = window; 
	    for(var i=0 ; i<nps.length ; i++){
			if(!nowScope[nps[i]]){
			    nowScope[nps[i]] = {}; 
			}
	        nowScope = nowScope[nps[i]];
	    }
	}

	function $define( _sClaz ){
		if (typeof eval(_sClaz) == "undefined") {
			eval(_sClaz+"=new Object();"); 
		}
	}

	function $import(_sClaz){
		//表明已加载
		if(_LoadedClass[_sClaz]){
			return false;
		}else{
			_LoadedClass[_sClaz] = true;
		}
		var file=_jscss[_sClaz];
		if (file) {
			var files = typeof file == 'string' ? [file]:file; 
			for (var i = 0; i < files.length; i++) { 
				var name = files[i].replace(/(^\s+)|(\s+$)/g,'');
				var scripts = document.getElementsByTagName('SCRIPT');
				for( var j=0 ; j<scripts.length ; j++){
					if(scripts[j].getAttribute("src",2) == name){
						return false;
					}
				}
				var att = name.split('.'); 
				var ext = att[att.length - 1].toLowerCase(); 
				var isCSS = ext == 'css'; 
				var tag = isCSS ? 'link' : 'script'; 
				var attr = isCSS ? ' type="text/css" rel="stylesheet" ' : ' type="text/javascript" '; 
				var link = (isCSS ? ' href' : ' src') + '="' + _basePath + name + '"'; 
				document.write("<" + tag + link + attr + "></" + tag + ">"); 
			} 
		}
	}
})();


