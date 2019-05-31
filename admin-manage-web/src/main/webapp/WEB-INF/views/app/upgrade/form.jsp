<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,validation"/>
    <!--  -->
    <style type="text/css">
    </style>
</head>
<style>
    .update{
        padding: 9px 15px;
        font-size: 12px;
        border-radius: 3px;
        display: inline-block;
        line-height: 1;
        white-space: nowrap;
        cursor: pointer;
        background: #006dcc;
        border: 1px solid #dcdfe6;
        color: #fff;
        -webkit-appearance: none;
        text-align: center;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        outline: 0;
        margin: 0;
        -webkit-transition: .1s;
        transition: .1s;
        font-weight: 500;
        padding: 9px 10px;
        font-size: 10px;
        border-radius: 4px;
    }

    .imgPic{
        width:48px;
        height:48px;
        display:block;
        float:left;
    }
    .thumbImgBox{
        position:relative;
        padding: 2px;
        border: 1px solid #E8E2D9;
        float: left;
        margin-right: 15px;
        line-height:48px;
    }
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/app/upgrade/list?productId=${upgrade.productId}">APP升级列表</a></li>
    <li class="active">
        <shiro:hasPermission name="app:upgrade:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}APP升级配置</a>
        </shiro:hasPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <form:form id="upgradeForm" modelAttribute="upgrade"  action="${ctxA}/app/upgrade/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" id="id" value="${not empty upgrade.id?upgrade.id:''}">
        <input type="hidden" id="appselectId" value="${upgrade.appId}">
        <div class="control-group">
            <label class="control-label">产品：</label>
            <div class="controls">
                <select  name="productId" id="productId" class="selectpicker show-tick form-control valid" descripe="产品未选择，请全部配置完成后保存" style="width: 15%;" onchange="productChange()">
                    <option value="">请选择产品</option>
                    <c:forEach items="${productList}" var="product">
                        <option value="${product.id}" ${not empty upgrade && upgrade.productId==product.id?"selected":''}>${product.productName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">应用：</label>
            <div class="controls">
                <select  name="appId" id="appId" class="selectpicker show-tick form-control valid" descripe="应用未选择，请全部配置完成后保存" style="width: 15%;" >

                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">版本号：</label>
            <div class="controls">
                <input type="text" class="form-control valid" type="text" placeholder="版本号，必填"  maxlength="11" descripe="版本号未填,请全部配置完成后保存"  name="versionNumber" id="versionNumber" value="${upgrade.versionNumber}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">版本名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" type="text" placeholder="版本名，必填"  maxlength="32" descripe="版本名未填,请全部配置完成后保存"  name="versionName" id="versionName" value="${upgrade.versionName}" onkeyup="value=value.replace(/[^\d^\.]+/g,'')" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">状态：</label>
            <div class="controls">
                <select  name="state" id="state" class="selectpicker show-tick form-control valid"  style="width: 15%;" >
                    <c:forEach items="${stateList}" var="state">
                        <option value="${state.dicVal}"  ${not empty upgrade && upgrade.state==state.dicVal?"selected":''}>${state.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">版本描述：</label>
            <div class="controls">
                <textarea class="form-control valid" descripe="版本描述为空,请全部配置完成后保存"   type="text"  maxlength="50"  name="versionRemark" id="versionRemark">${upgrade.versionRemark}</textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">下载地址：</label>
            <div class="controls">
                <input type="text"   class="form-control valid" type="text" placeholder="下载地址，必填"  descripe="下载地址为空,请全部配置完成后保存"  name="apkUrl" id="apkUrl" value="${upgrade.apkUrl}"  />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">升级范围：</label>
            <div class="controls">
                <select  name="upgradeRange" id="upgradeRange" class="selectpicker show-tick form-control valid"  style="width: 15%;" >
                    <c:forEach items="${upgradeRangeList}" var="upgradeRange">
                        <option value="${upgradeRange.dicVal}"  ${not empty upgrade && upgrade.upgradeRange==upgradeRange.dicVal?"selected":''}>${upgradeRange.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">类型：</label>
            <div class="controls">
                <select  name="appOsType" id="appOsType" class="selectpicker show-tick form-control valid"  style="width: 15%;">
                    <c:forEach items="${osList}" var="os">
                        <option value="${os.dicVal}" ${not empty upgrade && upgrade.appOsType == os.dicVal?"selected":''}>${os.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否强制升级：</label>
            <div class="controls">
                <select  name="isForce" id="isForce" class="selectpicker show-tick form-control"  style="width: 15%;">
                        <c:forEach items="${isForceList}" var="isForce">
                        <option value="${isForce.dicVal}" ${not empty upgrade && upgrade.isForce == isForce.dicVal?"selected":''}>${isForce.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否静默升级：</label>
            <div class="controls">
                <select  name="isSilence" id="isSilence" class="selectpicker show-tick form-control"  style="width: 15%;">
                    <c:forEach items="${isSilenceList}" var="isSilence">
                        <option value="${isSilence.dicVal}" ${not empty upgrade && upgrade.isSilence == isSilence.dicVal?"selected":''}>${isSilence.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">部分升级所需的最小版本：</label>
            <div class="controls">
                <input type="text" class="form-control" type="text" placeholder="必填"  maxlength="11" descripe="部分升级所需的最小版本未填,请全部配置完成后保存"  name="minVersionNumber" id="minVersionNumber" value="${upgrade.minVersionNumber}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">部分升级所需的最大版本：</label>
            <div class="controls">
                <input type="text" class="form-control" type="text" placeholder="必填"  maxlength="11" descripe="部分升级所需的最大版本未填,请全部配置完成后保存"  name="maxVersionNumber" id="maxVersionNumber" value="${upgrade.maxVersionNumber}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' />
            </div>
        </div>

        <div class="form-actions">
            <shiro:hasPermission name="app:upgrade:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/app/upgrade/list?productId=${upgrade.productId}'"/>
        </div>
    </form:form>


</div>
</body>
<script>
    var appList = [];
    $(function () {
        var appLists = '${appList}';
        appList = eval("("+appLists+")");
        productChange();
    })
    function save(){
        var bool = true;
        /*******  验证表单必填项目   ****************/
        $(".valid").each(function() {
            var descripe  = $(this).attr("descripe");
            if( $(this).val()=="" && descripe!=""){
                top.layer.alert(descripe, {icon: 5});
                bool = false;
                return false;
            }
        })
        if( !bool ){
            return false;
        }
        if ($("#upgradeRange").val() == '1'){
            if ($("#minVersionNumber").val() == ''){
                top.layer.alert($("#minVersionNumber").attr("descripe"), {icon: 5});
                return false;
            }
            if ($("#maxVersionNumber").val() == ''){
                top.layer.alert($("#maxVersionNumber").attr("descripe"), {icon: 5});
                return false;
            }
            if ($("#maxVersionNumber").val() <$("#minVersionNumber").val() ){
                top.layer.alert("最大版本应该大于最小版本", {icon: 5});
                return false;
            }
        }
        $("#btnSubmit").attr("disabled",true);
        var form=$("#upgradeForm");
        var action = form[0].action;
        var productId = $("#productId").val()
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/app/upgrade/list?productId="+productId;
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

    function  productChange() {
        $("#appId").html("");
        var productId = $("#productId").val();
        $("#appId").append("<option value=''>请选择应用</option>");
        for(var i = 0;i <appList.length ;i++){
            if(appList[i].productId == productId || productId == ""){
                $("#appId").append("<option value='"+appList[i].id+"'>"+appList[i].appName+"</option>");
            }
        }
        $("#appId").val($("#appselectId").val());
    }

</script>
</html>