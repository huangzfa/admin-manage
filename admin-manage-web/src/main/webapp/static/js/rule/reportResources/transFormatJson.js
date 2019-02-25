/**
 * [千分位方法]
 * @num 数字
 * @step 小数点位数【默认两位】
 */
function commafy(num, step) {
	var microValue;
	var floatSection; // 小数部分
	num = num.toString().replace(/,/g, ''); //避免带逗号的数据传入
	var value = String(parseFloat(num)); // 吧传入数据转换为float后再转换为字符串

	var arr = value.split('.');
	var isFloat = arr.length == 2; //判断是否是浮点数
	if (isFloat) {
		floatSection = arr[1].substr(0, step); // 小数部分
		value = arr[0]; //整数部分
	}

	// 小数点前面部分设置千分位
	var microValue = value.replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function(s) {
		// //console.log(s)
		return s + ',';
	});

	if (isFloat) {
		microValue = microValue + '.' + floatSection
	} else {
		microValue = microValue + '.' + "00"
	}

	if (typeof microValue == 'undefined' || microValue == 'NaN') {
		microValue = ""
	}
	return microValue;
}


//编号递增
Handlebars.registerHelper("increasingIndex", function(value) {
	return value + 1;
})

// 两个参数相乘
Handlebars.registerHelper("multiplicationFn", function(v1, v2, options) {
	// return value + 1;
	var v1 = v1 || 0,
		v2 = v2 || 0;
	Math.formatFloat = function(f, digit) {
		var m = Math.pow(10, digit);
		return parseInt(f * m, 10) / m;
	}
	return commafy(Math.formatFloat(v1 * v2, 2));
})

// 将json对象转换成json对符串 
Handlebars.registerHelper("toJsonString", function(jsonDate) {
	return JSON.stringify(jsonDate);
})


//时间转换  格式:2014-08-15 00:00:00
Handlebars.registerHelper("transFormatDate", function(value) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (second < 10) {
			second = '0' + second;
		}
		if (minute < 10) {
			minute = '0' + minute;
		}
		return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	}
	if (value == null || value == '') {
		return '-';
	}
	var d = new Date(value.replace(new RegExp(/-/gm), "/"));
	return formatDate(d);
});
//时间转换  格式:2014-08-15 00:00
Handlebars.registerHelper("transFormatShortDate", function(value) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (second < 10) {
			second = '0' + second;
		}
		if (minute < 10) {
			minute = '0' + minute;
		}
		return year + "-" + month + "-" + date + " " + hour + ":" + minute;
	}
	if (value == null || value == '') {
		return '';
	}
	var d = new Date(value.replace(new RegExp(/-/gm), "/"));
	return formatDate(d);
});
// 格式:2014/08/15
Handlebars.registerHelper("noticeDateFormat", function(value) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (second < 10) {
			second = '0' + second;
		}
		return year + "/" + month + "/" + date;
	}
	if (value == null || value == '') {
		return '';
	}
	var d = new Date(value.replace(new RegExp(/-/gm), "/"));
	return formatDate(d);
});

// 格式:2014-08-15
Handlebars.registerHelper("dateFormat", function(value) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		return year + "-" + month + "-" + date;
	}
	if (value == null || value == '') {
		return '';
	}
	var d = new Date(value.replace(new RegExp(/-/gm), "/"));
	return formatDate(d);
});

// 格式:2014/08/15 12:00
Handlebars.registerHelper("noticeDateFormatNew", function(value) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (month < 10) {
			month = '0' + month;
		}
		if (date < 10) {
			date = '0' + date;
		}
		if (hour < 10) {
			hour = '0' + hour;
		}
		if (second < 10) {
			second = '0' + second;
		}
		return year + "/" + month + "/" + date + " " + hour + ":" + second;
	}
	if (value == null || value == '') {
		return '';
	}
	var d = new Date(value.replace(new RegExp(/-/gm), "/"));
	return formatDate(d);
});



// 保留两位小数
Handlebars.registerHelper("tofixed", function(value) {
	return value.toFixed(2);
});


//时间格式转换 2014-8-18 16:09:27
Handlebars.registerHelper("timeFormat", function(time) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (second < 10) {
			second = '0' + second;
		}
		return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	}
	if (time == null || time == '') {
		return '';
	}
	var d = new Date(time);
	return formatDate(d);
})

//时间格式转换 2014-9-4
Handlebars.registerHelper("timeMonthFormat", function(time) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}
		return year + "-" + month + "-" + date;
	}
	if (time == null || time == '') {
		return '';
	}
	var d = new Date(time);
	return formatDate(d);
})

//时间格式转换 2014年7月10日 12:32:00
Handlebars.registerHelper("timeMsgFormat", function(time) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();

		if (hour < 10) {
			hour = '0' + hour;
		}

		if (minute < 10) {
			minute = '0' + minute;
		}

		if (second < 10) {
			second = '0' + second;
		}
		return year + '年' + month + '月' + date + '日' + '' + hour + ':' + minute + ':' + second;
	}
	if (time == null || time == '') {
		return '';
	}
	return formatDate(new Date(time));
})

/**
 * [金额格式化]
 * 格式: 1,000.00
 */
Handlebars.registerHelper("toThousands", function(num) {
	// var step = step || 2;

	if (typeof num == 'undefined' || num == 'NaN') {
		return "-";
	} else {
		var microValue = commafy(num, 2)
		return microValue;
	}
})


//判断不为空或者null或者undefined
Handlebars.registerHelper("isNotBlank", function(v1, options) {
	if (v1 != null && v1 != undefined && v1.toString() != "") {
		//满足添加继续执行
		return options.fn(this);
	} else {
		//不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});


// 判断相等
Handlebars.registerHelper("equal", function(v1, v2, options) {
	if (v1 == v2) {
		//满足添加继续执行
		return options.fn(this);
	} else {
		//不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});

// 不相等
Handlebars.registerHelper("notEqual", function(v1, v2, options) {
	if (v1 != v2) {
		//满足添加继续执行
		return options.fn(this);
	} else {
		//不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});

// 判断是否是正数
Handlebars.registerHelper("isPositive", function(value, options) {
	var isPositive = /^[1-9]\d*$/.test(value);
	if (isPositive) {
		//满足添加继续执行
		return options.fn(this);
	} else {
		//不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});


//剩余天数
Handlebars.registerHelper("stayDateFormat", function(dueDate) {
	if (new Date(dueDate) > 0) {
		var nowDate = new Date();
		var dueTime = new Date(dueDate);
		var s1 = dueTime.getTime(),
			s2 = nowDate.getTime();
		var total = (s1 - s2) / 1000;
		var day = parseInt(total / (24 * 60 * 60)); //计算整数天数
		day = day > 0 ? day : 0;
		return day;
	} else {
		return "-";
	}

});

//倒计时功能
Handlebars.registerHelper("showListCountDownTimeFun", function(idName, idIndex, putStartTime, nowTime) {
	// //console.log(idName)
	// var sys_second1 = (putStartTime - nowTime) / 1000;
	var putStartTime = putStartTime || 0;
	var sys_second1 = putStartTime; //剩余时间

	var timer1 = setInterval(function() {
		if (sys_second1 >= 1) {
			sys_second1 -= 1;
			var day1 = Math.floor((sys_second1 / 3600) / 24);
			var hour1 = Math.floor((sys_second1 / 3600) % 24);
			var minute1 = Math.floor((sys_second1 / 60) % 60);
			var second1 = Math.floor(sys_second1 % 60);
			var showDay1 = day1;
			var showHour1 = hour1 < 10 ? "0" + hour1 : hour1; //计算小时
			var showMinute1 = minute1 < 10 ? "0" + minute1 : minute1; //计算分钟
			var showSecond1 = second1 < 10 ? "0" + second1 : second1; //计算秒杀
			// $("#showTime" + id).html('<i>' + showDay1 + '</i>天<i>' + showHour1 + '</i>时<i>' + showMinute1 + '</i>分<i>' + showSecond1 + '</i>秒');
			$("#" + idName + idIndex).html(showMinute1 + ":" + showSecond1);
		} else {
			clearInterval(timer1);
			// window.location.reload();
		}
	}, 1000);
});

/**
 * [第一条数据显示标题]
 */
Handlebars.registerHelper("hideTitle", function(thisIndex, options) {
	if (thisIndex == 0) {
		//满足添加继续执行
		return options.fn(this);
	} else {
		//不满足条件执行{{else}}部分
		return options.inverse(this);
	}
})

/**
 * 客户行为检测
 */
Handlebars.registerHelper("frequencyDetail", function(frequencyDetail, total) {
	var frequencyDetailLen = frequencyDetail.length;
	// //console.log(frequencyDetail)

	var tableString = ""
	var tableDataTr = "";

	// //console.log(frequencyDetailLen)
	for (var i = 0; i < frequencyDetailLen; i++) {

		var frequencyDetailDate = frequencyDetail[i]; //当前索引数据

		var frequencyDetailDateLen = !!frequencyDetailDate.data ? frequencyDetailDate.data : [] // 内部data
		var tableTitle = frequencyDetailDate.detail.split("：")[0] //标题
		var tableText = frequencyDetailDateLen.length //长度[部data长度]

		// //console.log(frequencyDetailDateLen.length, tableText)

		// //console.log(i)
		for (var j = 0; j < tableText; j++) {
			// //console.log(j==0)
			if (j == 0) {
				// //console.log("j==0")
				tableDataTr += '<tr> <td class="listTitle" rowspan="' + tableText + '">' + tableTitle + '</td> <td class="listText"rowspan="' + tableText + '">' + tableText + '</td> <td>' + frequencyDetailDateLen[0] + '</td> </tr>'
			} else {
				tableDataTr += '<tr> <td>' + frequencyDetailDateLen[j] + '</td> </tr>'
			}
		}
		// tableString += tableDataTr;
	}


	return tableDataTr;
})


/*
 * 多平台借贷申请检测[上部表格]
 */
Handlebars.registerHelper("ListTable", function(platforDetail, total) {
	var tableTd = '<td class="listTitle">总个数</td> <td class="listText">' + total + '</td>'
	var tableArr = [];
	var tableString = "";
	tableArr.push(tableTd);

	for (var value in platforDetail) {
		// //console.log(platforDetail[value])
		var tableTitle = platforDetail[value].split(":")[0]
		var tableText = platforDetail[value].split(":")[1]
		var tableTdAdd = '<td class="listTitle">' + tableTitle + '</td> <td class="listText">' + tableText + '</td>'
		tableArr.push(tableTdAdd);
	}

	/*
	 * 返回表格数据
	 */
	// //console.log(tableArr)
	var tableLen = (tableArr.length % 2 == 0) ? tableArr.length : tableArr.length + 1;
	for (var i = 0; i < tableLen; i++) {
		if (i % 2 == 0) {
			if (!!tableArr[i + 1]) {
				tableString += '<tr>' + tableArr[i] + tableArr[i + 1] + '</tr>'
			} else {
				tableString += '<tr>' + tableArr[i] + '<td class="listTitle"></td> <td class="listText"></td></tr>'
			}

		}
	}

	return tableString;

	// //console.log(platforDetail, total)
})

/**
 * 展示不同等级
 */
Handlebars.registerHelper("showGrade", function(grade) {
	switch (grade) {
		case "high":
			return '<em class="' + grade + '">高</em>';
			break;
		case "medium":
			;
			return '<em class="' + grade + '">中</em>';
			break;
		case "low":
			;
			return '<em class="' + grade + '">低</em>';
			break;
		default:
			return '<em</em>';
			break;
	}
})

/**
 * 各维度多头详情
 */
Handlebars.registerHelper("ListTableTwoLine", function(platforDetailDimension) {
	// var tableStr1 = "";

	function toTableStr(tableDate) {
		var tableStr = "";
		var tableTop = '<tr> <td style="text-align: center;" colspan="2">' + tableDate.dimension + '</td> </tr>'

		tableStr = tableStr + tableTop;

		for (var value in tableDate.detail) {
			var tableTitle = tableDate.detail[value].split(":")[0]
			var tableText = tableDate.detail[value].split(":")[1]
			var tableTdAdd = '<tr> <td class="listTitle">' + tableTitle + '</td> <td class="listText">' + tableText + '</td></tr>'
			tableStr += tableTdAdd;
		}

		return '<div class="detailItem"> <table class="tableCSS"> <tbody class="moreLine">' + tableStr + '</tbody> </table> </div>';
	}
	// //console.log()
	return toTableStr(platforDetailDimension[0]) + toTableStr(platforDetailDimension[1])
})

/**
 * [返回对应的key值对应对象]
 */
function buildKeyTodata(listTitle) {
	var dataObject = {} //生成key值对应对象

	// 判断是否有值【有值返回“是/否”】；为空/undefined/null 返回空
	function bankDateFn(bankDate) {
		if (bankDate === true) {
			bankDate = "是"
		} else if (bankDate === false) {
			bankDate = "否"
		} else if (bankDate === "" || bankDate === null || bankDate === undefined) {
			bankDate = "空值"
		} else {
			bankDate = bankDate
		}
		return bankDate;
	}

	var listTitleLen = listTitle.length; //数据长度
	for (var i = 0; i < listTitleLen; i++) {
		var titleKey = listTitle[i].titleKey
		var dataList = listTitle[i].dataList;
		dataObject[titleKey] = []; //新建数组
		// //console.log(dataList)
		for (var value in dataList) {
			var newObj = {
				key: value,
				name: dataList[value]
			}
			dataObject[titleKey].push(newObj)
		}
	}
	return dataObject;
}


/**
 * [根据key和数据返回表格]
 */
function tableHtmlStr(dataLen, everDate, dataList) {
	function bankDateFn(bankDate) {
		if (bankDate === true) {
			bankDate = "是"
		} else if (bankDate === false) {
			bankDate = "否"
		} else if (bankDate === "" || bankDate === null || bankDate === undefined) {
			bankDate = "空值"
		} else {
			bankDate = bankDate
		}
		return bankDate;
	}

	var strtr = ''
	var arrayTr = "";
	var arrayTh = "";
	var arrayTd = "";

	//标题头
	for (var j = 0; j < dataLen; j++) {
		var name1 = everDate[j].name;
		arrayTd += "<th>" + name1 + "</th>"
	}
	arrayTr = "<thead> <tr> " + arrayTd + "</tr> </thead>"


	//具体列表内容
	for (var ar = 0; ar < dataList.length; ar++) {
		var arrayTd = "";
		arrayTr += "<tr>"
		for (var j = 0; j < dataLen; j++) {
			var value1 = dataList[ar][everDate[j].key];
			// 判断是否有值【有值返回“是/否”】；为空/undefined/null 返回空
			value1 = bankDateFn(value1)

			arrayTd += "<td>" + value1 + "</td>"
		}
		arrayTr = arrayTr + arrayTd + "</tr>"
	}
	// strtr = arrayTh + '<tbody class="oneLine">' + arrayTr + '</tbody>';
	strtr = arrayTh + arrayTr;

	return strtr;
}

/**
 * 返回多行表格
 */
Handlebars.registerHelper("bacKTables", function(tableDate, tableTitle, tableIndex) {
	// //console.log(tableDate)
	var titleKey = buildKeyTodata(eval(tableTitle))[tableIndex]; //key值对应
	var dataLen = titleKey.length; //表格字段个数
	var dataList = tableDate; //数据

	// //console.log(tableHtmlStr(dataLen, titleKey, dataList))
	return tableHtmlStr(dataLen, titleKey, dataList);


})