$.fn.pageList = function(cf, methodName) {
	if(typeof methodName === "string" && $.trim(methodName) != "") {
		switch(methodName) {
			case 'refresh': //刷新当前页,一般用于删除或者更新后
				$(this).each(function() {
					var cf = $(this).data('config_666');
					if(cf != null && typeof cf.clickCallback === "function") {
						cf.clickCallback(cf.currentPage);
					}
				});
				break;
		}
	} else {
		var defaultConfig = {
			prevText: '上一页',
			nextText: '下一页',
			recordText: '', //显示记录数，为空时不显示，否则按照占位符显示文本，{0}表示总页数，{1}表示总记录数
			clickCallback: function(currentPage) {}, //链接被点击时触发的事件，currentPage表示当前点击的是第几页，索引从1开始
			pageSize: 10, //每页条数
			currentPage: 1, //当前第几页，索引从1开始
			totalCount: 0, //总记录数
			showPageRange: 6 //最小值3，除上一页、下一页、第一页、最后一页和...外需要显示的数量
		};
		cf = $.extend({}, defaultConfig, cf);
		//总页数
		var totalPages = parseInt((cf.totalCount / cf.pageSize)) + ((cf.totalCount % cf.pageSize) == 0 ? 0 : 1); 
		if(cf.currentPage < 1) {
			cf.currentPage = 1;
		}
		if(cf.currentPage > totalPages) {
			cf.currentPage = totalPages;
		}
		if(cf.showPageRange < 3) {
			cf.showPageRange = 3;
		}
		$(this).data('config_666', cf);
		$(this).each(function() {
			$(this).empty(); //清空
			if(totalPages > 0) {
				var pageBox = $('.pagelist');
				var startPageNum = 2,
					endPageNum = totalPages-1;
				//是否显示...
				var prevDDD = false,nextDDD = false;
				if(cf.showPageRange + 2 < totalPages) {//算上第一页和最后一页2页
					var flagNum = parseInt(cf.showPageRange / 2);
					if((cf.showPageRange % 2) == 1) {
						flagNum++;
					}
					startPageNum = cf.currentPage - flagNum + 1;
					if(startPageNum < 2) {
						startPageNum = 2;
					}
					endPageNum = cf.currentPage + cf.showPageRange - flagNum;
					
					if(endPageNum >= totalPages){
						endPageNum = totalPages-1;
					}
					var addNum=cf.showPageRange-(endPageNum - startPageNum + 1);
					//alert(startPageNum+"<>"+endPageNum +"<>"+ addNum);
					if(addNum > 0){
						if((cf.currentPage - startPageNum + 1) < flagNum){
							endPageNum+=addNum;
						}else{
							startPageNum-=addNum;
						}
					}
					prevDDD = startPageNum - 1 > 1;
					nextDDD = endPageNum + 1 < totalPages;
				}
				//前一页
				if(cf.currentPage != 1){
					pageBox.append(createUpDownPage('上一页','up',cf));
				}
				//第一页
				pageBox.append(createPageItem(1,cf.currentPage == 1,cf)); 
				//…页
				if(prevDDD) {
					pageBox.append('<span>…</span>'); 
				}
				//第i页
				for(var i = startPageNum; i <= endPageNum; i++) {
					pageBox.append(createPageItem(i, cf.currentPage == i, cf));
				}
				//…页
				if(nextDDD) {
					pageBox.append('<span>…</span>');
				}
				//最后一页
				if(totalPages > 1) {
					pageBox.append(createPageItem(totalPages, cf.currentPage == totalPages, cf));
				}
				//下一页
				if(cf.currentPage != totalPages){
					pageBox.append(createUpDownPage('下一页','down',cf));
				}

				if(typeof cf.recordText === "string" && $.trim(cf.recordText) != "") {
					pageBox.append('<span>' + cf.recordText.replace(/\{0\}/g, totalPages).replace(/\{1\}/g, cf.totalCount) + '</span>');
				}
				$(this).append(pageBox);
			}
		});
	}
	//创建页码对象绑定事件
	function createPageItem(pageNumber, selected, cf) {
		if(selected) {
			return '<span class="selected">'+pageNumber+'</span>';
		} else {
			var pi=$('<a href="javascript:void(0);">'+pageNumber+'</a>');
			pi.click(function() {
				pageClickEvent(this, pageNumber, cf);
			});
		}
		return pi;
	}

	function pageClickEvent(pageItem, pageNumber, cf) {
		if(typeof cf.clickCallback === "function") {
			cf.clickCallback(pageNumber);
		}
		cf.currentPage = pageNumber;
		$(pageItem).parent().pageList(cf);
	}
	//创建上页下页对象绑定事件，flag:up,down
	function createUpDownPage(text,flag,cf) {
		var cls=('up'==flag)?'upPage':'downPage';
		var pi=$('<a href="javascript:void(0);" class="'+cls+'">'+text+'</a>');
		pi.click(function() {
			pageClickEvent(this,('up'==flag)?cf.currentPage-1:cf.currentPage+1, cf);
		});
		return pi;
	}
};