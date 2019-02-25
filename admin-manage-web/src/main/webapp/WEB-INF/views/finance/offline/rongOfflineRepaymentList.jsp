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
        <input type="hidden" value="2" name="productType" id="productType"/>
        <ul class="ul-form">
            <li>
                <label>用户账号：</label>
                <input id="userName" name="userName" class="input-large" type="text" value="" maxlength="50"/>
            </li>
            <li>
                <label>还款状态：</label>
                <select name="repaymentStatus" id="repaymentStatus">
                    <option value="0">待还款</option>
                    <option value="1">已还款</option>
                    <option value="-1">还款失败</option>

                </select>
            </li>
            <li>
                <label>来源：</label>
                <select id="submitterType" name="submitterType">
                    <option value="">---全部---</option>
                    <option value="2">融360</option>
                    <option value="0">多贝业务</option>
                    <option value="1">催收平台</option>
                </select>
            </li>
            <li>
                &nbsp;&nbsp;&nbsp;&nbsp; <input id="search" class="btn btn-primary" type="button" value="查询"/>
            </li>
        </ul>
        <br>
        <ul>
            <div id="toolbar">
                <a href="javascript:openUploadWindow('uploadExcelWindow')" class="easyui-linkbutton" iconCls="icon-add" plain="true">上传平账文件</a>
            </div>
        </ul>
    </form>
</div>
<div class="si-warp" style="position: inherit;height: 85%">
    <sys:message content="${message}" isShowBox="false"/>
    <table id="offlineRepaymentRong" title="融360线下还款" class="easyui-datagrid"
           data-options="singleSelect:true,rownumbers:true,toolbar:'toolbar',singleSelect:true,striped:true,fit:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'borrowNo',width:150,align:'center',halign:'center'">借款流水号</th>
            <th data-options="field:'submitterType',width:100,align:'center',halign:'center',formatter:submitTypeformater">来源</th>
            <th data-options="field:'userName',width:100,align:'center',halign:'center'">用户账号</th>
            <th data-options="field:'userRealName',width:100,align:'center',halign:'center'">用户姓名</th>
            <th data-options="field:'borrowArrearsAmount',width:100,align:'center',halign:'center'">借款待还</th>
            <th data-options="field:'arrearsAmount',width:100,align:'center',halign:'center'">所选账单待还金额</th>
            <th data-options="field:'nper',width:100,align:'center',halign:'center'">分期期数</th>
            <th data-options="field:'submitterTime',width:100,align:'center',halign:'center',fixed:true">提交时间</th>
            <th data-options="field:'orderNo',width:150,align:'center',halign:'center'">还款流水号</th>
            <th data-options="field:'settleTime',width:100,align:'center',halign:'center'">平账时间</th>
            <th data-options="field:'settleType',width:100,align:'center',halign:'center',formatter:settleTypeformater">
                平账方式
            </th>
            <th data-options="field:'settleOperatorName',width:100,align:'center',halign:'center'">平账人员</th>
            <th data-options="field:'repayState',width:100,align:'left',halign:'center',formatter:repayStatusformater">
                平账状态
            </th>
            <th data-options="field:'option',width:150,align:'center',halign:'center',fixed:true,formatter:optionformater">
                操作
            </th>
        </tr>
        </thead>
    </table>
</div>

<div id="win" class="easyui-window" title="线下平账" closed="true" style="width:75%;height:80%;padding:5px;">
    <div class="easyui-layout" fit="true">
        <div region="west" border="false" id="imageDiv" class="p-search" style="width:31%;height:100%;padding: 5px;" align="center" >
            <div class="easyui-layout" fit="true">
                <div region="north"  style="width:100%;height:89%;" fit="true">
                    <img id="repaymentImg" name="repaymentImg" src=""
                         style="border: 1px;width: 100%"/>
                </div>

                <div region="south" style="width:100%;height:10%;" class="p-right" >
                    <input id="uploadImageInput" value="" name="uploadImage" type="hidden" />
                    <button style="position: absolute;left:40%;bottom: 30%;" id="uploadImageButton" onclick="openUploadWindow('uploadImageWindow')" class="btn btn-primary" style="text-align: center" >上传图片</button>
                </div>
            </div>
        </div>

        <div region="east" border="false"
             style="width:67%;height:100%;margin-left: 10px;">
            <div class="easyui-layout" fit="true">
                <div region="north" style="width: 98%;height: 80%">
                    <table cellpadding="10px">
                        <input name="repayOfflineId" id="repayOfflineId" value="" type="hidden" />
                        <input name="repayBorrowId" id="repayBorrowId" value="" type="hidden" />
                        <input name="repayBillId" id="repayBillId" value="" type="hidden" />
                        <input name="repayIsBorrowFlat" id="repayIsBorrowFlat" value="0" type="hidden" />
                        <tr>
                            <th>平账方式:</th>
                            <td>常规平账</td>
                            <th>用户账号:</th>
                            <td><input type="text" id="repayUserName" name="repayUserName" value="" disabled="disabled"></td>
                        </tr>
                        <tr>
                            <th>还款方式:</th>
                            <td>
                                <input type="radio" class="easyui-validatebox" name="repaymentType" value="1" checked="checked" ><label>支付宝</label></input>
                                <input type="radio" class="easyui-validatebox" name="repaymentType" value="2" ><label>银行卡</label></input>
                            </td>
                            <th>用户姓名:</th>
                            <td><input type="text" id="repayUserRealName" name="repayUserRealName" value="" disabled="disabled"></td>
                        </tr>
                        <tr>
                            <th>还款卡号:</th>
                            <td><input type="text" name="repaymentCardNo" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="20"  id="repaymentCardNo" value="" ></td>
                            <th>借款待还金额:</th>
                            <td><input type="number" value="" id="borrowwaitRepayment" max="100000" step="0.01" min="0" name="borrowwaitRepayment" disabled="disabled" /></td>
                        </tr>
                        <tr>
                            <th>实还金额:</th>
                            <td><input type="number" step="0.01" value="" id="realRepayment"  onchange="getShouldDerateAmount()" name="realRepayment" max="100000.00" min="0.00" /></td>
                            <td>实际还款时间</td>
                            <td><input id="realRepayTime"  name="realRepayTime" autocomplete="off" type="text" onchange="getShouldDerateAmount()"  onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
                        </tr>
                        <tr>
                            <th>还款账号:</th>
                            <td><select id="alipayAccount" name="alipayAccount">
                                </select>
                            </td>
                            <%--<td>减免逾期费:</td>
                            <td><input type="number" value="" id="derateOverdue" name="derateOverdue" max="10000" min="0" /></td>--%>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <th>还款流水号:</th>
                            <td><input type="text" id="repaymentTradeNo" value="" name="repaymentTradeNo" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="32"></input></td>
                            <td>本次还款应减逾期费:</td>
                            <td><span id="shouldDerateOverdue" style="color: red">填写完实际还款金额和还款时间后计算应减逾期费</span></td>
                        </tr>
                        <tr>
                            <th>备注:</th>
                            <td>
                                <textarea name="operationRemark" id="operationRemark" maxlength="200"></textarea>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                </div>
                <div region="south" style="height: 20%;text-align: center;align: center;" class="p-right">
                    <div style="position: absolute;left:30%;bottom: 30%;" >
                        <input id="closeWindow" class="btn btn-primary" onclick="closeWindow('win')" type="button" value="关闭窗口"/>&nbsp;&nbsp;
                        <input id="flatAccount" class="btn btn-primary" onclick="flatAccountRepayment(this)" type="button" value="线下平账"/>&nbsp;&nbsp;
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>


<div class="easyui-window" id="uploadExcelWindow" title="文件上传"  style="width:60%;height:45%;padding:2px;" closed="true">
    <form id="importFileForm" method="post" action="${ctxA}/finance/offline/repayment/uploadRongExcel" enctype="multipart/form-data">
        <table style="margin:5px;height:70px;">
            <tr>
                <td>请选择文件</td>
                <td width="5px;"></td>
                <td><input type="file" id="uploadExcel" name="uploadExcel" style="width:260px;"></td>
                <td></td>
            </tr>
        </table>

        <div style="text-align:center;clear:both;margin:5px;">
            <button type="button" id="uploadFile" class="easyui-linkbutton" onclick="uploadFileCheck(this)"  data-options="iconCls:'icon-ok'" >上传</button>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-bind="click:closeImportClick" href="javascript:closeWindow('uploadExcelWindow')">关闭</a>
        </div>
    </form>
</div>


<div class="easyui-window" id="uploadImageWindow" title="图片上传"  style="width:60%;height:45%;padding:2px;" closed="true">
    <form id="uploadImageForm" method="post" action="${ctxA}/finance/offline/repayment/uploadOfflineExcel" enctype="multipart/form-data">
        <table style="margin:5px;height:70px;">
            <tr>
                <td>请选择文件</td>
                <td width="5px;"></td>
                <td><input type="file" id="uploadImageFile" name="uploadImageFile" style="width:260px;"></td>
                <td></td>
            </tr>
        </table>

        <div style="text-align:center;clear:both;margin:5px;">
            <button type="button" id="uploadImage" class="easyui-linkbutton" onclick="uploadImageFunction(this)"  data-options="iconCls:'icon-ok'" >上传</button>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-bind="click:closeImportClick" href="javascript:closeWindow('uploadImageWindow')">关闭</a>
        </div>
    </form>
</div>

</body>
<script type="text/javascript">
    var pageSize = 10;
    $(function () {
        pageSize = ${cfg:getPageSize()};
        var pageList = [pageSize, 30, 50];
        var pager = $('#offlineRepaymentRong').datagrid('getPager');
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

        $("#realRepayment").tooltip({
            position: 'right',
            content: '<span style="color:#fff">金额上限为100000.00,并且不能为负数!</span>',
            onShow: function(){
                $(this).tooltip('tip').css({
                    backgroundColor: '#666',
                    borderColor: '#666'
                });
            }
        });
        $("#repaymentCardNo").tooltip({
            position: 'right',
            content: '<span style="color:#fff">银行卡号由16-19位数字组成!</span>',
            onShow: function(){
                $(this).tooltip('tip').css({
                    backgroundColor: '#666',
                    borderColor: '#666'
                });
            }
        });

        $("#derateOverdue").tooltip({
            position: 'right',
            content: '<span style="color:#fff">减免的逾期费用不能超过当前账单的总逾期费!</span>',
            onShow: function(){
                $(this).tooltip('tip').css({
                    backgroundColor: '#666',
                    borderColor: '#666'
                });
            }
        });

        $("#repaymentTradeNo").tooltip({
            position: 'right',
            content: '<span style="color:#fff">支付宝流水号由16-32位数字组成!</span>',
            onShow: function(){
                $(this).tooltip('tip').css({
                    backgroundColor: '#666',
                    borderColor: '#666'
                });
            }
        });

        $('#win').window({
            onBeforeClose:function(){
                // 清空内容
                $("#borrowwaitRepayment").val("");
                $("#repayUserName").val("");
                $("#repayUserRealName").val("");
                $("#repaymentTradeNo").val("");
                $("#repayOfflineId").val("");
                $("#repayBorrowId").val("");
                $("#repayBillId").val("");
                $("#realRepayment").val("");
                $("#derateOverdue").val("");
                $("#realRepayTime").val("");
                $("#shouldDerateOverdue").html("填写完实际还款金额和还款时间后计算应减逾期费");
            }
        });


    });

    function getData(pageNum, pageSize) {
        $('#offlineRepaymentRong').datagrid('loading');
        hjnUtils.ajax({
            type: 'post',
            url: '${ctxA}/finance/offline/repayment/getData',
            data: $("#searchOfflineForm").serialize() + '&page=' + pageNum + '&pagesize=' + pageSize + "&pageSource=" + "2" ,
            dataType: 'json',
            success: function (data) {
                $('#offlineRepaymentRong').datagrid('loaded');
                if (data.code == 1) {
                    var option;
                    for (var i = 0 ;i <data.alipayNos.length ; i++){
                        option += "<option value= '"+data.alipayNos[i].id +"'>"+ data.alipayNos[i].name + "&nbsp;"+ data.alipayNos[i].account + "</option>"
                    }
                    $("#alipayAccount").html(option);

                    $('#offlineRepaymentRong').datagrid('loadData', data.list);
                } else {
                    top.$.jBox.tip(data.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('#offlineRepaymentRong').datagrid('loaded');
            }
        });
    }

    $('#search').click(function () {
        getData(1, pageSize);
    });

    function settleTypeformater(value, row, index) {
        if (value == '1') {
            return "批量平账";
        } else if (value == '2') {
            return "人工平账";
        }
        return "";
    }

    function repayStatusformater(value, row, index) {
        if (value == '0') {
            return "待平账";
        } else if (value == '1') {
            return "已平账";
        } else if (value == '-1') {
            return "平账失败";
        }
        return "";
    }

    function submitTypeformater(value, row, index) {
        if (value == '0') {
            return "多贝";
        } else if (value == '1') {
            return "催收平台";
        } else if (value == '2') {
            return "融360";
        } else if (value == '3'){
            return "业务人员";
        }
        return "";
    }


    function optionformater(value, row, index) {
        var opStr = '';
        <shiro:hasPermission name="finance:offline:repayment:ronglist:edit">
        if(isNotEmpty(row.repayState + '') && parseInt(row.repayState) == 0){
            opStr += '<a class="si-option-a" href="javascript:repaymentApprove('+row.id+','+row.borrowId+','+row.isBorrowFlat+',\''+row.userName+'\', \''+row.userRealName+' \')">还款审核</a>';
        }
        </shiro:hasPermission>

        opStr += '<a class="si-option-a" href="${ctxA}/finance/offline/repayment/rongdetail?offlineId='+convertNull(row.id)+'&borrowId='+row.borrowId+'" >还款详情</a>';

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

    function repaymentApprove(offlineId,borrowId,isBorrowFlat,userName,userRealName){

        hjnUtils.ajax({
            type: 'post',
            url: '${ctxA}/finance/offline/repayment/getOfflineDetail',
            data: {
                "offlineId":offlineId,
                "borrowId":borrowId,
                "isBorrowFlat":isBorrowFlat
            },
            dataType: 'json',
            success: function (data) {
                $('#offlineRepayment').datagrid('loaded');
                if (data.code == 1) {
                    if(parseInt(isBorrowFlat) == 2){
                        $("#borrowwaitRepayment").val(data.borrowWaitAmount);
                        $("#repayUserName").val(userName);
                        $("#repayUserRealName").val(userRealName);
                        $("#repaymentTradeNo").val(data.offline.orderNo);
                        $("#repayOfflineId").val(data.offline.id);
                        $("#repayBorrowId").val(data.repayBorrowId);
                        $("#repayBillId").val(data.repayBillId);
                        $("#repaymentImg").attr("src",data.offline.imgUrl);
                    }else if(parseInt(isBorrowFlat) == 1){
                        $("#borrowwaitRepayment").val(data.borrowWaitAmount);
                        $("#repayUserName").val(userName);
                        $("#repayUserRealName").val(userRealName);
                        $("#repayBillId").val(data.repayBillId);
                        $("#repayBorrowId").val(data.repayBorrowId);
                        $("#repayIsBorrowFlat").val(isBorrowFlat);
                    }else{
                        $.messager.alert("提示","系统参数有误,请刷新界面后重试!");
                    }

                    $('#win').window('open');
                } else {

                    top.$.jBox.tip(data.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('#offlineRepayment').datagrid('loaded');
            }
        });
    }


    //线下平账
    function flatAccountRepayment(obj) {

        obj.disabled = true;
        //5 秒后设为有效
        setInterval(function() {obj.disabled = false;}, 4000);

        var repaymentTradeNo = $('#repaymentTradeNo').val();
        var realRepayment = $('#realRepayment').val();
        var repaymentType =  $('input[name="repaymentType"]:checked').val();
        var remark = $("#operationRemark").val()
        var alipayAccount = $("#alipayAccount").val();
        var repaymentCardNo = $("#repaymentCardNo").val();
        var offlineId = $("#repayOfflineId").val();
        var borrowId = $("#repayBorrowId").val();
        var billId = $("#repayBillId").val();
        var derateOverdue = $("#derateOverdue").val();
        var repayIsBorrowFlat = $("#repayIsBorrowFlat").val();
        var realRepayTime = $("#realRepayTime").val();

        if((isEmpty(repayIsBorrowFlat) && isEmpty(offlineId)) || isEmpty(borrowId) || isEmpty(billId)){
            $.messager.alert("error","系统参数异常,请刷新界面重试!");
            return;
        }

        if (isEmpty(repaymentType)) {
            $.messager.alert("提示","请选择还款方式");
            return;
        }

        // 对于 银行卡还款 必须填写 银行卡账户
        if(parseInt(repaymentType) == 2 ){
            if(isEmpty(repaymentCardNo)){
                $.messager.alert("提示","银行卡还款时银行卡账户不能为空!");
                return;
            }
        }

        if (isEmpty(realRepayment)) {
            $.messager.alert("提示","请填写实际还款金额!");
            return;
        }
        if(!/^(([1-9]\d*)|\d)(\.\d{1,2})?$/.test(realRepayment)){
            $.messager.alert("提示","实际还款金额格式有误，必须为正数，小数点后只能保留两位");
        }
        if (isEmpty(repaymentTradeNo)) {
            $.messager.alert("提示","请填写支付流水号!");
            return;
        }

        if (repaymentTradeNo.length != 28 && repaymentTradeNo.length!= 32) {
            $.messager.alert("提示","支付宝流水号为28或者32位的数字!");
            return;
        }

        if (isEmpty(alipayAccount)) {
            $.messager.alert("提示","请填写还款支付宝账号!");
            return;
        }
        if (isEmpty(realRepayTime)) {
            $.messager.alert("提示","请选取实际还款时间!");
            return;
        }

        if(isNotEmpty(remark) && remark.length > 200){
            $.messager.alert("提示","备注信息最大长度为120!");
            return;
        }

        $.messager.confirm('Confirm','请确认该流水号是否需要平账?',function(result){
            if(result) {
                jQuery.ajax({
                    url: "${ctxA}/finance/offline/repayment/repayRongOffline",
                    type:"POST",
                    data:{
                        "borrowId":borrowId,
                        "billId":billId,
                        "borrowCashOfflineId":offlineId,
                        "repaymentType":repaymentType,
                        "repaymentTradeNo":repaymentTradeNo,
                        "realRepayment":realRepayment,
                        "repaymentCardNo":repaymentCardNo,
                        "alipayAccount":alipayAccount,
                        "derateOverdue":derateOverdue,
                        "repayIsBorrowFlat":repayIsBorrowFlat,
                        "realRepayTime":realRepayTime,
                        "remark":remark
                    },
                    success:  function(data) {
                        var result = JSON.parse(data);
                        if (""+result.code == "1") {
                            $.messager.alert("提示","平账成功",null,function () {
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


    function getShouldDerateAmount(){

        var realRepayment = $("#realRepayment").val();
        var realRepayTime = $("#realRepayTime").val();

        if(isEmpty(realRepayment) || isEmpty(realRepayTime)){

            return;
        }
        var repayOfflineId = $("#repayOfflineId").val();
        var repayBorrowId = $("#repayBorrowId").val();
        var repayBillId = $("#repayBillId").val();

        jQuery.ajax({
            url: "${ctxA}/finance/offline/repayment/getShouldDerateAmount",
            type:"POST",
            data:{
                "borrowId":repayBorrowId,
                "billId":repayBillId,
                "borrowCashOfflineId":repayOfflineId,
                "realRepayment":realRepayment,
                "realRepayTime":realRepayTime
            },
            success:  function(data) {

                var result = JSON.parse(data);
                if (""+result.code == "1") {
                        $("#shouldDerateOverdue").html(result.derateOverdue);
                } else {
                    $.messager.alert("提示",result.msg);
                }
            },
            error:function(data,status,e){
                $.messager.alert("异常","error!---"+ e);
            }
        });

    }

    function openUploadWindow(obj){

        $('#'+obj).window('open');
    }

    //导入文件
    function uploadFileCheck(obj) {
        obj.disabled = true;
        //5 秒后设为有效
        setInterval(function() {obj.disabled = false;}, 5000);

        //获取上传文件控件内容
        var file = $("#uploadExcel").val();
        if (isEmpty(file)) {
            $.messager.alert('错误','请选择文件');
            return;
        }

        if(!/\.(xlsx|xls)$/.test(file)){
            $.messager.alert("错误","文件类型必须为.xlsx或者xls");
            $("#uploadExcel").val("");
            return;
        }
        ajaxFileUpload();
    }

    function ajaxFileUpload() {
        $.ajaxFileUpload({
            url: "${ctxA}/finance/offline/repayment/uploadRongExcel",
            type: 'post',
            secureuri: false,
            fileElementId: "uploadExcel",
            dataType: 'json',
            success: function (data, status){
                var result = JSON.parse(data);
                if (""+result.code == "1") {
                    var signKey = result.signKey;
                    var msg = "";
                    if(parseInt(result.isFlat) > 0){
                        $.messager.confirm("上传成功!","目前预计可以平账的条数为："+result.isFlat+"条，"+"点击[OK]按钮开始对上传的文件进行平账,点击[Cancel]删除本次上传数据!",function (confirm) {
                            if(confirm){
                                jQuery.ajax({
                                    url: "${ctxA}/finance/offline/repayment/repayRongOfflineBatchFlat",
                                    type:"POST",
                                    data:{
                                        "signKey":signKey
                                    },
                                    success:  function(data) {
                                        var result = JSON.parse(data);
                                        if (""+result.code == "1") {
                                            var prompt = "<span style='color:red;'>本次上传可平账总数为:"+result.total +",&nbsp;平账成功个数:"+result.success+",&nbsp;平账失败个数为:"+result.failedNum+"</span></br>";
                                            prompt += "失败原因如下:"+result.result;

                                            $.messager.alert({
                                                width: '60%',
                                                height: '30%',
                                                title: "提示",
                                                msg:prompt
                                            });

                                            closeWindow("uploadExcelWindow");
                                            getData(1, pageSize);
                                        } else {
                                            $.messager.alert("提示",result.msg);
                                            closeWindow("uploadExcelWindow");
                                            getData(1, pageSize);
                                        }
                                    },
                                    error:function(data,status,e){
                                        $.messager.alert("异常","error!---"+ e);
                                    }
                                });
                            }else{

                                jQuery.ajax({
                                    url: "${ctxA}/finance/offline/repayment/cancelUpload",
                                    type:"POST",
                                    data:{
                                        "signKey":signKey
                                    },
                                    success:  function(data) {
                                        var result = JSON.parse(data);
                                        $.messager.alert("提示",result.msg);
                                        closeWindow("uploadExcelWindow");
                                        getData(1, pageSize);
                                    },
                                    error:function(data,status,e){
                                        $.messager.alert("异常","error!---"+ e);
                                    }
                                });
                            }
                        });
                    }else{
                        $.messager.confirm("上传成功!","没有可以直接平账的记录，如果需要取消本次上传记录请点击[Cancel],如果需要手动平账点击[OK]!",function (confirm) {
                            if(!confirm){
                                jQuery.ajax({
                                    url: "${ctxA}/finance/offline/repayment/cancelUpload",
                                    type:"POST",
                                    data:{
                                        "signKey":signKey
                                    },
                                    success:  function(data) {
                                        var result = JSON.parse(data);
                                        $.messager.alert("提示",result.msg);
                                        closeWindow("uploadExcelWindow");
                                        getData(1, pageSize);
                                    },
                                    error:function(data,status,e){
                                        $.messager.alert("异常","error!---"+ e);
                                    }
                                });
                            }else{
                                closeWindow("uploadExcelWindow");
                                getData(1, pageSize);
                            }
                        });
                    }

                } else {

                    $.messager.alert("提示",result.msg);
                }
            },
            error:function(data,status,e){

                $.messager.alert("异常","error!---"+ e);
            }
        });
        return false;
    }


    function uploadImageFunction(obj){

        obj.disabled = true;
        //5 秒后设为有效
        setInterval(function() {obj.disabled = false;}, 5000);

        var uploadImage = $("#uploadImageFile").val();
        if(isEmpty(uploadImage)){
            $.messager.alert("提示","请选选择上传的图片!");
            return;
        }

        if(!/\.(bmp|png|jpg|jpeg|BMP|PNG|JPG|JPEG)$/.test(uploadImage)){

            $("#uploadImageFile").val("");
            $.messager.alert("错误","图片只能是bmp,png,gif,jpeg,jpg格式");

            return;
        }

        $.ajaxFileUpload({
            url: "${ctxA}/finance/offline/repayment/uploadOfflineImage",
            type: 'post',
            secureuri: false,
            fileElementId: "uploadImageFile",
            dataType: 'json',
            success: function (data, status){
                var result = JSON.parse(data);

                $("#repaymentImg").attr("src",result.imageUrl);

                $("#uploadImageInput").val(result.imageUrl);

                closeWindow('uploadImageWindow');

                $.messager.alert("上传成功!",result.msg);
            },
            error:function(data,status,e){

                $.messager.alert("异常","error!---"+ e);
            }
        });
    }


</script>


</html>
