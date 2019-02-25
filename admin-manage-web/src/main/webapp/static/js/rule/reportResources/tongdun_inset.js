
// 获取链接对应字段参数
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    } else {
        return null;
    }
}
var orderNo = GetQueryString("orderNo")

/**
 * [插入数据方法]
 * @参数
 * getData:传入的参数
 * listTitle:配置的参数对象
 * @返回
 * 返回html字符串
 */
function insertTable(getData, listTitle) {
    var strBox = ''

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

    // 返回状态
    function banKState(state){
        if(state == 10){
            state = "有效"
        }else if(state == 20){
            state = "无效"
        }else{
            state = state
        }
        return state;
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

    for (var i = 0; i < listTitle.length; i++) {
        var strtr = ''
        strBox += '<div class="everyTable"> <h4 class="tableTitle"> ' + listTitle[i].titleText + ' </h4> <div class="tableWrap"> <table class="tableCSS">'
        var everDate = dataObject[listTitle[i].titleKey]; //key值对应表
        var dataLen = dataObject[listTitle[i].titleKey].length; // 字段个数【对应字段】
        var dataList = getData[listTitle[i].titleKey]; //返回的的数据【对应数据】


        if (dataList instanceof Array) {
            //数组类型
            // //console.log(dataList.length)
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

        } else {
            //非数组类型的
            for (var j = 0; j < dataLen; j++) {
                // //console.log(j)
                if (j % 2 == 0) {
                    var name1 = everDate[j].name;
                    var value1 = dataList[everDate[j].key];
                    // 判断是否有值【有值返回“是/否”】；为空/undefined/null 返回空

                    // //console.log(typeof value1)
                    value1 = bankDateFn(value1)
                    if(name1 == "状态" ){
                        value1 = banKState(value1)
                    }

                    var name2 = j + 1 < dataLen ? everDate[j + 1].name : "";
                    var value2 = j + 1 < dataLen ? dataList[everDate[j + 1].key] : "";

                    // 判断是否有值【有值返回“是/否”】；为空/undefined/null 返回空
                    value2 = bankDateFn(value2)
                    if(name2 == "状态" ){
                        value2 = banKState(value2)
                    }


                    strtr += '<tr> <td class="listTitle">' + name1 + '</td> <td class="listText">' + value1 + '</td> <td class="listTitle">' + name2 + '</td> <td class="listText">' + value2 + '</td> </tr>'
                }
            }
            strtr = '<tbody class="moreLine">' + strtr + '</tbody>';
        }

        strBox = strBox + strtr + '</table> </div>  </div>'
            // //console.log(strBox)
    }
    return strBox; //返回html字符串
}


var reviewFlag = $("#reviewFlag").val();
var ruleReportUrl = "";
if (reviewFlag == "1") {
	ruleReportUrl = "/modules/api/report/rule/engine/review.htm";
} else {
	ruleReportUrl = "/modules/api/report/rule/engine.htm";
}

/**
 * [加载模板文件]
 */
$("#tpl_wrap").load("/dev/reportResources/tpl/tpl.html", function() {

    // 规则报告
    $.ajax({
        //url: "/modules/api/report/rule/engine.htm",
    	url: ruleReportUrl,
        method: 'get',
        dataType: "json",
        data: {
            orderNo: orderNo
        },
        success: function(result) {
            if (result.code == 200) {
                var source = $("#rule-template").html(); //获取模板
                var template = Handlebars.compile(source); //预编译模板
                var returnHrml = template(result); //生成html模板
                $("#ruleReport").html(returnHrml)
            } else if (result.code == 204) {
                $("#ruleReport").html("暂无数据")
            } else {
                $(".ruleReport").hide();
            }

        }
    })

    // 同盾报告[数据过于复杂]
    $.ajax({
        url: "/modules/api/report/tongdun.htm",
        method: 'get',
        dataType: "json",
        data: {
            orderNo: orderNo
        },
        success: function(result) {
            if (result.code == 200) {
                var tongdunDate = result.res;
                var newData = {};
                newData.final_score = !!tongdunDate.final_score ? tongdunDate.final_score : "";
                newData.infoLength = !!tongdunDate.risk_items ? tongdunDate.risk_items.length : 0;
                newData.final_decision = !!tongdunDate.final_decision ? tongdunDate.final_decision : "";
                newData.address_detect = !!tongdunDate.address_detect ? tongdunDate.address_detect : {};
                // //console.log(newData)

                //[重新组装数组]
                if (!!tongdunDate && !!tongdunDate.risk_items) {
                    var risk_items = tongdunDate.risk_items;

                    var risk_items_length = risk_items.length; //风险项长度
                    var risk_items_group = [];
                    var risk_items_group_list = [];
                    for (var i = 0; i < risk_items_length; i++) {
                        var risk_items_group_name = risk_items[i].group;
                        var arrIndex = risk_items_group.indexOf(risk_items_group_name);
                        if (arrIndex < 0) {
                            risk_items_group.push(risk_items_group_name);
                            risk_items_group_list.push(new Array(risk_items[i]))
                        } else {
                            risk_items_group_list[arrIndex].push(risk_items[i]);
                        }
                    }

                    newData.risk_items = risk_items_group_list
                        // //console.log(JSON.stringify(newData)) //转化为字符串
                }
                // console.log(newData)
                var source = $("#tongdun-template").html(); //获取模板
                var template = Handlebars.compile(source); //预编译模板
                var returnHrml = template(newData); //生成html模板
                $("#tongdun").html(returnHrml)
            } else if (result.code == 204) {
                $("#tongdun").html("暂无数据")
            } else {
                $(".tongdun").hide();
            }

        }
    })

    //贷后帮报告
    $.ajax({
        url: "/modules/api/report/hulu.htm",
        method: 'get',
        dataType: "json",
        data: {
            orderNo: orderNo
        },
        success: function(result) {
            // //console.log(result)
            if (result.code == 200) {
                // var insertDate = insertTable(result.res.data, listTitle)
                var source = $("#daihouBang-template").html(); //获取模板
                var template = Handlebars.compile(source); //预编译模板
                var returnHrml = template(result.res.data); //生成html模板
                $("#daihouBang").html(returnHrml)
            } else if (result.code == 204) {
                $("#daihouBang").html("暂无数据")
            } else {
                $(".daihouBang").hide();
            }
        }
    })

    // 运营商报告
    $.ajax({
        url: "/modules/api/report/shangshu.htm",
        method: 'get',
        dataType: "json",
        data: {
            orderNo: orderNo
        },
        success: function(result) {
            if (result.code == 200) {
                // var insertDate = insertTable(result.res, shangshuTitle)
                var source = $("#shangshu-template").html(); //获取模板
                var template = Handlebars.compile(source); //预编译模板
                var resJson = "";
                try {
                	resJson = JSON.parse(result.res);
				} catch (e) {
					resJson = result.res;
				}
                var returnHrml = template(resJson); //生成html模板
                $("#shangshu").html(returnHrml)
            } else if (result.code == 204) {
                $("#shangshu").html("暂无数据")
            } else {
                $(".shangshu").hide();
            }
        }
    })
    // 魔蝎报告
    $.ajax({
        url: "/modules/api/report/moxiereport.htm",
        // url: "Api/Tabledata.json",
        method: 'get',
        data: {
            orderNo: orderNo
        },
        success: function(result) {
            console.log(result);
            // if (result.code == 200) {
                // var insertDate = insertTable(result.res, shangshuTitle)
                //var source = $("#moxie-template").html(); //获取模板
               // var template = Handlebars.compile(source); //预编译模板
                //var resJson = "";
               // try {
               //     resJson = JSON.parse(result.res);
              //  } catch (e) {
               //     resJson = result.res;
              //  }
              //  var returnHrml = template(resJson); //生成html模板
                var returnHtml = '<iframe style="width:100%;height:100%;min-height: 600px" src="'+result+'"></iframe>';
                $("#moxie").html(returnHtml)
            // } else if (result.code == 204) {
            //    $("#moxie").html("暂无数据")
           // } else {
           //     $(".moxie").hide();
           // }
        }
    })
    // 运营商统计信息
    $.ajax({
        url: "/modules/api/report/shangshuCount.htm",
        method: 'get',
        dataType: "json",
        data: {
            orderNo: orderNo
        },
        success: function(result) {
            if (result.code == 200) {
                var returnHrml = insertTable(result, shangshuDetailList); //生成html模板
                $("#shangshuDetail").html(returnHrml)
            } else if (result.code == 204) {
                $("#shangshuDetail").html("暂无数据")
            } else {
                $(".shangshuDetail").hide();
            }
        }
    })

    // 法院报告
    $.ajax({
        url: "/modules/api/report/court.htm",
        method: 'get',
        dataType: "json",
        data: {
            orderNo: orderNo
        },
        success: function(result) {
            // //console.log(result)
            if (result.code == 200) {
                // var insertDate = insertTable(result.res, courtTitle)
                // $("#court").html(insertDate)
                var source = $("#court-template").html(); //获取模板
                var template = Handlebars.compile(source); //预编译模板
                var returnHrml = template(result.res); //生成html模板
                $("#court").html(returnHrml)
            } else if (result.code == 204) {
                $("#court").html("暂无数据")
            } else {
                $(".court").hide();
            }
        }
    })

    // 通讯录报告
    $.ajax({
        url: "/modules/api/report/addressList.htm",
        method: 'get',
        dataType: "json",
        data: {
            orderNo: orderNo
        },
        success: function(result) {
            // var source = $("#mailList-template").html(); //获取模板
            // var template = Handlebars.compile(source); //预编译模板
            // var returnHrml = template(phone); //生成html模板
            // $("#mailList").html(returnHrml)
            // //console.log(result)
            if (result.code == 200) {
                // var insertDate = insertTable(result.res, courtTitle)
                // $("#court").html(insertDate)
                var source = $("#mailList-template").html(); //获取模板
                var template = Handlebars.compile(source); //预编译模板
                var returnHrml = template(result); //生成html模板
                $("#mailList").html(returnHrml)
            } else if (result.code == 204) {
                $("#mailList").html("暂无数据")
            } else {
                $(".mailList").hide();
            }
        }
    })

})