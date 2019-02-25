<%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>逾期减免</title>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,ajaxfileupload"/>
</head>


<body>

<div class="breadcrumb form-search" style="height: 10%">
    <form id="searchOfflineForm">
        <ul class="ul-form">
            <li>
                <label>用户账号：</label>
                <input id="userName" name="userName" class="input-large" value="" type="text" value="" maxlength="50"/>
            </li>
            <li>
                <label>借款流水号：</label>
                <input id="borrowNo" name="borrowNo" class="input-large" value="" type="text" value="" maxlength="50"/>
            </li>
            <li>
                &nbsp;&nbsp;&nbsp;&nbsp; <input id="search" class="btn btn-primary" type="button" value="查询"/>
            </li>
        </ul>
    </form>
</div>
<div class="si-warp" style="position: inherit;height: 85%">
    <sys:message content="${message}" isShowBox="false"/>
    <table id="overduelist" title="Overdue list" class="easyui-datagrid"
           data-options="singleSelect:true,rownumbers:true,toolbar:'toolbar',singleSelect:true,striped:true,fit:true,pagination:true">
        <thead>
        <tr>
            <th data-options="field:'borrowNo',width:150,align:'center',halign:'center'">借款流水号</th>
            <th data-options="field:'productName',width:120,align:'center',halign:'center'">产品名称</th>
            <th data-options="field:'userName',width:120,align:'center',halign:'center'">用户账号</th>
            <th data-options="field:'userRealName',width:120,align:'center',halign:'center'">用户姓名</th>
            <th data-options="field:'billAmount',width:120,align:'center',halign:'center'">当期到期应还</th>
            <th data-options="field:'realRepayAmount',width:120,align:'center',halign:'center'">当期到期实际应还</th>
            <th data-options="field:'remainderAmount',width:120,align:'center',halign:'center'">当期剩余应还</th>
            <th data-options="field:'sumRepayOverdueAmount',width:120,align:'center',halign:'center'">当期已还逾期费</th>
            <th data-options="field:'waitOverdueAmount',width:120,align:'center',halign:'center'">当期未还逾期费</th>
            <th data-options="field:'derateOverdue',width:120,align:'center',halign:'center'">当期已减免金额</th>
            <th data-options="field:'option',width:150,align:'center',halign:'center',fixed:true,formatter:optionformater">
                操作
            </th>
        </tr>
        </thead>
    </table>
</div>

<div class="easyui-window" id="derateOverdueWindow" title="逾期减免"  style="width:60%;height:45%;padding:2px;" closed="true">
    <table style="margin:5px;height:70px;">
        <input name="derateBorrowId" id="derateBorrowId" value="" type="hidden" />
        <input name="derateBillId" id="derateBillId" value="" type="hidden" />
        <tr>
            <td>用户姓名:</td>
            <td><span id="derateUserName"></span></td>
        </tr>
        <tr>
            <td>手机号:</td>
            <td><span id="derateUserPhone"></span></td>
        </tr>
        <tr>
            <td>减免金额:</td>
            <td><input type="number" value="" id="derateOverdue" name="derateOverdue" max="10000" min="0" /></td>
        </tr>
    </table>

    <div style="text-align:center;clear:both;margin:5px;">
        <button type="button" id="uploadImage" class="easyui-linkbutton" onclick="derateOverdue(this)"  data-options="iconCls:'icon-ok'" >减免逾期费</button>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-bind="click:closeImportClick" href="javascript:closeWindow('derateOverdueWindow')">关闭</a>
    </div>
</div>

</body>
<script type="text/javascript">

    $(function () {
        $('#derateOverdueWindow').window({
            onBeforeClose:function(){
                // 清空内容
                $("#derateUserName").html("");
                $("#derateUserPhone").html("");
                $("#derateBorrowId").val("");
                $("#derateBillId").val("");

            }
        });
    })

    function getData() {
        $('#overduelist').datagrid('loading');
        hjnUtils.ajax({
            type: 'post',
            url: '${ctxA}/finance/overdue/derateoverdue/getData',
            data: $("#searchOfflineForm").serialize() ,
            dataType: 'json',
            success: function (data) {
                $('#overduelist').datagrid('loaded');

                if (data.code == 1) {

                    $('#overduelist').datagrid('loadData', data.bills);

                } else {

                    top.$.jBox.tip(data.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('#overduelist').datagrid('loaded');
            }

        });
    }

    $('#search').click(function () {
        var userName = $("#userName").val();
        var borrowNo = $("#borrowNo").val();
        if(isEmpty(userName) && isEmpty(borrowNo)){
            $.messager.alert("提示","用户账号和借款编号必须填写一个!");
            return;
        }

        getData();
    });


    function optionformater(value, row, index) {
        var opStr = '';
        <shiro:hasPermission name="finance:offline:repayment:list:edit">
            opStr += '<a class="si-option-a" href="javascript:openDerateWindow(\'derateOverdueWindow\','+row.id+','+row.borrowId+',\''+row.userName+'\',\''+row.userRealName+'\')">减免逾期费</a>';
        </shiro:hasPermission>
        return opStr;
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

    function openDerateWindow(win,billId,borrowId,userName,realName){
        $("#derateUserName").html(realName);
        $("#derateUserPhone").html(userName);
        $("#derateBorrowId").val(borrowId);
        $("#derateBillId").val(billId);

        openUploadWindow(win);

    }

    function closeWindow(win){

        $("#"+win).window('close');
    }

    function openUploadWindow(obj){

        $('#'+obj).window('open');
    }

    function derateOverdue(obj){
        obj.disabled = true;
        //5 秒后设为有效
        setInterval(function() {obj.disabled = false;}, 5000);
        var borrowId = $("#derateBorrowId").val();
        var billId = $("#derateBillId").val();
        var derateOverdue = $("#derateOverdue").val();

        if (isEmpty(borrowId) || isEmpty(billId)){
            $.messager.alert("提示","系统参数异常,请刷新界面重试!");
            return;
        }

        if (isEmpty(derateOverdue) ){
            $.messager.alert("提示","请先填写逾期减免金额!");
            return;
        }


        jQuery.ajax({
            url: "${ctxA}/finance/overdue/derateoverdue",
            type:"POST",
            data:{
                "borrowId":borrowId,
                "billId":billId,
                "derateoverdue":derateOverdue
            },
            success:  function(data) {
                var result = JSON.parse(data);
                $.messager.alert("提示",result.msg,'info',function(){
                    closeWindow("derateOverdueWindow");
                    getData();
                });
            },
            error:function(data,status,e){
                $.messager.alert("异常","error!---"+ e);
            }
        });

    }

</script>


</html>
