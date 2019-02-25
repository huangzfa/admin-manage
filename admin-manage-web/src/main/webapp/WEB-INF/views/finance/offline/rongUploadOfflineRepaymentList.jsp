<%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>线下平账</title>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,ajaxfileupload,97Date"/>
</head>
<body>

<div class="breadcrumb form-search" style="height: 10%">
    <form id="searchOfflineForm">
        <ul class="ul-form">
            <li>
                <label>流水单号：</label>
                <input id="tradNo" name="tradNo"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="32"  class="input-large" type="text" value="" maxlength="50"/>
            </li>
            <li>
                <label>实际还款时间：</label>
                <input id="realRepayTimeMin" name="realRepayTimeMin" type="text"  onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  readonly="readonly" />
                -<input id="realRepayTimeMax" name="realRepayTimeMax" type="text"  onclick="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  readonly="readonly" />
            </li>
            <li>
                <label>支付宝账号：</label>
                <select id="zfbAccountId" name="zfbAccountId">
                    <option value="" >---请选择---</option>
                    <c:forEach var="item" items="${alipayNos}">
                        <option value="${item.id}" >${item.account}</option>
                    </c:forEach>
                </select>
            </li>
            <li>
                &nbsp;&nbsp;&nbsp;&nbsp; <input id="search" class="btn btn-primary" type="button" value="查询"/>
            </li>
        </ul>
    </form>
</div>
<div class="si-warp" style="position: inherit;height: 85%">
    <sys:message content="${message}" isShowBox="false"/>
    <table id="offlineRepaymentRongUpload" title="融360线下还款" class="easyui-datagrid"
           data-options="singleSelect:true,rownumbers:true,toolbar:'toolbar',singleSelect:true,striped:true,fit:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'tradNo',width:200,align:'center',halign:'center'">支付宝流水单号</th>
            <th data-options="field:'realRepayDate',width:150,align:'center',halign:'center',fixed:true">转账时间</th>
            <th data-options="field:'repayAmount',width:100,align:'center',halign:'center'">金额</th>
            <th data-options="field:'userAlipayNo',width:100,align:'center',halign:'center'">对方账号</th>
            <th data-options="field:'userAlipayName',width:100,align:'center',halign:'center'">对方姓名</th>
            <th data-options="field:'receiptAlipayNo',width:100,align:'center',halign:'center'">到账支付宝</th>
            <th data-options="field:'createDate',width:150,align:'center',halign:'center',fixed:true">上传时间</th>
            <th data-options="field:'creatorName',width:100,align:'center',halign:'center'">上传人</th>
            <th data-options="field:'isFlatSuccess',width:100,align:'left',halign:'center',formatter:uploadStatusformater">状态</th>
            <th data-options="field:'flatUserName',width:100,align:'center',halign:'center'">归档人</th>
            <th data-options="field:'option',width:150,align:'center',halign:'center',fixed:true,formatter:optionformater">
                操作
            </th>
        </tr>
        </thead>
    </table>
</div>

<div id="flatWin" class="easyui-window" title="归档" closed="true" style="width:75%;height:80%;padding:5px;">
    <div class="easyui-layout" fit="true">
        <div region="north" style="width: 98%;height: 25%">
            <table cellpadding="10px">
                <input type="hidden" id="flatUploadId" name="flatUploadId" value="" />
                <tr>
                    <th>请输入平账手机号:</th>
                    <td><input type="text" id="flatUserPhone" name="flatUserPhone" value=""  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="16" ></td>
                </tr>
                <tr>
                    <th>请输入借款流水号:</th>
                    <td>
                        <input type="text" id="flatBorrowNo" name="flatBorrowNo" value="" maxlength="30" >
                    </td>
                </tr>
            </table>
        </div>
        <div region="center" style="width: 98%;height: 55%">
            <table cellpadding="10px" border="1px">
                <thead style="background: #E8E2D9">
                    <th width="20%">支付宝流水单号:</th>
                    <th width="15%">转账时间:</th>
                    <th width="8%">金额(元):</th>
                    <th width="8%">用户姓名:</th>
                    <th width="10%">导入的支付宝账号:</th>
                    <th width="15%">上传时间:</th>
                    <th width="8%">上传人:</th>
                    <th width="8%">状态</th>
                </thead>
                <tbody>
                    <tr>
                        <td><span id="flatTradNo"></span></td>
                        <td><span id="flatRealRepayTime"></span></td>
                        <td><span id="flatAmount"></span></td>
                        <td><span id="flatUserName"></span></td>
                        <td><span id="flatUserAlipayNo"></span></td>
                        <td><span id="flatUploadTime"></span></td>
                        <td><span id="flatUploadOperator"></span></td>
                        <td><span id="flatUploadStatus"></span></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div region="south" style="height: 15%;text-align: center;align: center;" class="p-right">
            <div id="flatButtonDiv" style="position: absolute;left:40%;bottom: 30%;" >

            </div>
        </div>
    </div>
</div>


</body>
<script type="text/javascript">
    var pageSize = 10;


    $(function () {
        pageSize = ${cfg:getPageSize()};
        var pageList = [pageSize, 30, 50];
        var pager = $('#offlineRepaymentRongUpload').datagrid('getPager');
        pager.pagination({
            pageSize: pageSize,//每页显示的记录条数，默认为10
            pageList: pageList,//可以设置每页记录条数的列表
            layout: ['list', 'sep', 'first', 'prev', 'links', 'next', 'last', 'sep', 'manual'],
            beforePageText: '',
            afterPageText: ' 共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
            onSelectPage: function (pageNumber, pageSize) {
                pageSize = pageSize;
                getData(pageNumber, pageSize);
            }
        });
        $('.datagrid-pager.pagination-num').hide();

        //加载第一页数据
        getData(1, pageSize);

        $('#flatWin').window({
            onBeforeClose:function(){
                $("#flatUserPhone").attr("disabled",false);
                $("#flatBorrowNo").attr("disabled",false);
                // 清空内容
                $("#flatUserPhone").val("");
                $("#flatBorrowNo").val("");
            }
        });
    });

    function getData(pageNum, pageSize) {
        $('#offlineRepaymentRongUpload').datagrid('loading');
        hjnUtils.ajax({
            type: 'POST',
            url: '${ctxA}/finance/offline/repayment/getUploadRongListData',
            data: $("#searchOfflineForm").serialize() + '&page=' + pageNum + '&pagesize=' + pageSize,
            dataType: 'json',
            success: function (data) {
                $('#offlineRepaymentRongUpload').datagrid('loaded');
                if (data.code == 1) {

                    $('#offlineRepaymentRongUpload').datagrid('loadData', data.list);
                } else {
                    top.$.jBox.tip(data.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('#offlineRepaymentRongUpload').datagrid('loaded');
            }
        });
    }

    $('#search').click(function () {
        getData(1, pageSize);
    });

    function uploadStatusformater(value, row, index) {
        if (value == '0' || value == '-1' ) {
            return "可用作平账";
        } else if (value == '1' || value == '2') {
            return "已用作平账";
        }
        return "";
    }

    var flatObjects = new Map;
    function optionformater(value, row, index) {
        var opStr = '';
        flatObjects.set("flatObject"+row.id,JSON.stringify(row));
        <shiro:hasPermission name="finance:offline:repayment:rongwaitlist:edit">
        if(isNotEmpty(row.isFlatSuccess + '') && (parseInt(row.isFlatSuccess) == 0 || parseInt(row.isFlatSuccess) == -1) ){
            opStr += '<a class="si-option-a" href="javascript:repaymentApprove(1,\'flatObject'+row.id+'\')">归档</a>';
        }
        </shiro:hasPermission>

        opStr += '<a class="si-option-a" href="javascript:repaymentApprove(2,\'flatObject'+row.id+'\')" >查看详情</a>';

        return opStr;
    }

    function closeWindow(winName){

        $('#'+winName).window('close');
    }

    function convertNull(val){

        if(val == undefined || val == null || (val+"") == "" ){
            return "";
        }else{
            return val;
        }

    }

    function repaymentApprove(type,flatObjectKey){
        var flatObject =  flatObjects.get(flatObjectKey);
        if(flatObject != undefined){
            flatObject = JSON.parse(flatObject);
        }else{
            $.messager.alert("error","数据获取错误,请刷新界面重试!");
            return;
        }

        var flatButtonDiv = $("#flatButtonDiv");
        var flatButtonDivHtml = '<input id="closeWindow" class="btn btn-primary" onclick="closeWindow(\'flatWin\')" type="button" value="关闭窗口"/>&nbsp;&nbsp;&nbsp;&nbsp;';
        if (type == 1){
            flatButtonDivHtml += '<input id="flatAccount" class="btn btn-primary" onclick="flatAccountRepayment()" type="button" value="归档"/>';
        }else{
            jQuery.ajax({
                url: "${ctxA}/finance/offline/repayment/getUploadUserAndBorrow",
                type:"POST",
                data:{
                    "flatUploadId":flatObject.id,
                    "flatUserId":flatObject.userId
                },
                success:  function(data) {

                    var result = JSON.parse(data);
                    if (""+result.code == "1") {
                        $("#flatUserPhone").attr("disabled","disabled");
                        $("#flatBorrowNo").attr("disabled","disabled");
                        $("#flatUserPhone").val(result.userPhone);
                        $("#flatBorrowNo").val(flatObject.borrowNo);
                    } else {
                        $("#flatUserPhone").attr("disabled","disabled");
                        $("#flatBorrowNo").attr("disabled","disabled");
                        $("#flatUserPhone").val("");
                        $("#flatBorrowNo").val(flatObject.borrowNo);
                    }
                },
                error:function(data,status,e){
                    $.messager.alert("异常","error!---"+ e);
                }
            });


        }
        flatButtonDiv.html(flatButtonDivHtml);

        $("#flatUploadId").val(flatObject.id);
        $("#flatTradNo").html(flatObject.tradNo);
        $("#flatRealRepayTime").html(flatObject.realRepayDate);
        $("#flatAmount").html(flatObject.repayAmount);
        $("#flatUserName").html(flatObject.userAlipayName);
        $("#flatUserAlipayNo").html(flatObject.userAlipayNo);
        $("#flatUploadTime").html(flatObject.createDate);
        $("#flatUploadOperator").html(flatObject.creatorName);

        if (parseInt(flatObject.isFlatSuccess) == 0 || parseInt(flatObject.isFlatSuccess) == -1 ) {
            $("#flatUploadStatus").html("可用作平账");
        } else{
            $("#flatUploadStatus").html("已用作平账");
        }

        $('#flatWin').window('open');

    }

    //线下平账
    function flatAccountRepayment() {
        var flatUploadId = $('#flatUploadId').val();
        var flatUserPhone = $('#flatUserPhone').val();
        var flatBorrowNo = $("#flatBorrowNo").val();
        if(isEmpty(flatUploadId)){
            $.messager.alert("error","系统参数异常,请刷新界面重试!");
            return;
        }
        if(isEmpty(flatUserPhone) || isEmpty(flatBorrowNo)){
            $.messager.alert("error","请填写用户手机号和借款编号!");
            return;
        }

        $.messager.confirm('Confirm','请确认是否归档给手机号为'+flatUserPhone+'的用户?',function(result){
            if(result) {
                jQuery.ajax({
                    url: "${ctxA}/finance/offline/repayment/flatUploadRongOffline",
                    type:"POST",
                    data:{
                        "flatUploadId":flatUploadId,
                        "flatUserPhone":flatUserPhone,
                        "flatBorrowNo":flatBorrowNo
                    },
                    success:  function(data) {

                        var result = JSON.parse(data);
                        if (""+result.code == "1") {
                            $.messager.alert("提示","归档成功",null,function () {
                                window.location.href =  location.href;
                            });
                        } else {
                            $.messager.alert("提示",result.msg);
                        }
                    },
                    error:function(data,status,e){
                        $.messager.alert("异常","error!---"+ e);
                    }
                });
            }
        });
    }

    function isNotEmpty(val){
        if(val != undefined && val != null && val.length >= 0){
            return true;
        }else{
            return false;
        }
    }

    function isEmpty(val){

        if(val == undefined || val == null || val.length <= 0){
            return true;
        }else{
            return false;
        }
    }

</script>


</html>
