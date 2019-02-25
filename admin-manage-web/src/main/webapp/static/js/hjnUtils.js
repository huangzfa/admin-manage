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