(function(win){
	var dict=function(){
		function houseCheckState(key) {
			if(key==1){
				return '未提交';
			}else if(key==2){
				return '审核中';
			}else if(key==3){
				return '审核失败';
			}else if(key==4){
				return '审核成功';
			}else if(key==5){
				return '数据修改审核中';
			}else if(key==6){
				return '数据修改审核失败';
			}
			return '';
	    };
	    function houseState(key) {
	    	if(key==0){
				return '未上架';
			}else if(key==1){
				return '已上架';
			}else if(key==2){
				return '用户下架';
			}else if(key==3){
				return '平台下架';
			}
			return '';
	    };
	    
	    function orderState(key) {
	    	if(key==1){
				return '待确认';
			}else if(key==2){
				return '房客取消';
			}else if(key==3){
				return '房东拒绝';
			}else if(key==4){
				return '超时取消(未确认)';
			}else if(key==5){
				return '待付款';
			}else if(key==6){
				return '超时取消(未付款)';
			}else if(key==7){
				return '待入住(已付款)';
			}else if(key==8){
				return '全额退款';
			}else if(key==9){
				return '入住中';
			}else if(key==10){
				return '入住完成';
			}else if(key==11){
				return '申请退款-房东审核中';
			}else if(key==12){
				return '退款完成';
			}else if(key==13){
				return '申请退款-房东拒绝';
			}
			return '';
	    };
	    return {
	    	houseCheckState:houseCheckState,
	    	houseState:houseState,
	    	orderState:orderState
	    };
	};
	win.dict=dict();
})(window);