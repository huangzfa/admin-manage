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
    <li><a href="${ctxA}/app/examine/list?productId=${appExamine.productId}">APP审核列表</a></li>
    <li class="active">
        <shiro:hasPermission name="app:examine:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}APP审核配置</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="app:examine:edit">
            <a href="javascript:void(0);">查看菜单</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <form:form id="appExamineForm" modelAttribute="appExamine"  action="${ctxA}/app/examine/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" id="id" value="${not empty appExamine.id?appExamine.id:''}">
        <input type="hidden" name="productId" value="${not empty appExamine.productId?appExamine.productId:''}">
        <input type="hidden" name="appId" value="${not empty appExamine.appId?appExamine.appId:''}">
        <div class="control-group">
            <label class="control-label">产品：</label>
            <div class="controls">
                <select  name="product" id="product" class="selectpicker show-tick form-control valid" descripe="产品未选择，请全部配置完成后保存" style="width: 15%;" onchange="productChange()">
                    <option value="">请选择产品</option>
                    <c:forEach items="${productList}" var="product">
                        <option value="${product.id}" ${not empty appExamine && appExamine.productId==product.id?"selected":''}>${product.productName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">应用：</label>
            <div class="controls">
                <select  name="app" id="app" class="selectpicker show-tick form-control valid" descripe="应用未选择，请全部配置完成后保存" style="width: 15%;" onchange="appChange()">
                    <option value="" pid = "">请选择应用</option>
                    <c:forEach items="${appList}" var="app">
                        <option value="${app.id}" pid = "${app.productId}" ${not empty appExamine && appExamine.appId==app.id?"selected":''}>${app.appName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">系统：</label>
            <div class="controls">
                <select  name="osType" id="osType" class="selectpicker show-tick form-control valid" descripe="系统未选择，请全部配置完成后保存" style="width: 15%;">
                    <option value="">请选择系统</option>
                    <c:forEach items="${osList}" var="os">
                        <option value="${os.dicVal}" ${not empty appExamine && appExamine.appOsType == os.dicVal?"selected":''}>${os.dicCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>、
        <div class="control-group">
            <label class="control-label">应用市场渠道：</label>
            <div class="controls">
                <select  name="channel" id="channel" class="selectpicker show-tick form-control valid" descripe="渠道未选择，请全部配置完成后保存" style="width: 15%;">
                    <option value="">请选择渠道</option>
                    <c:forEach items="${channelList}" var="channel">
                        <option value="${channel.id}" ${not empty appExamine && appExamine.channelId==channel.id?"selected":''}>${channel.channelName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">版本号：</label>
            <div class="controls">
                <input type="text" class="form-control valid" type="text"  maxlength="11" placeholder="请输入版本号（格式：175）"  descripe="版本号未填写或格式填写有误，请全部配置完成后保存"  name="versionNumber" id="versionNumber" value="${appExamine.versionNumber}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' ></input>
            </div>
        </div>

        <div class="form-actions">
            <shiro:hasPermission name="app:examine:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/app/examine/list?productId=${appExamine.productId}'"/>
        </div>
    </form:form>


</div>
</body>
<script>
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
        $("#btnSubmit").attr("disabled",true);
        var form=$("#appExamineForm");
        var action = form[0].action;
        var productId = $("#product").val()
        var data = {"id":$("#id").val(),"productId":productId,"appId":$("#app").val()
            ,"appOsType":$("#osType").val(),"channelId":$("#channel").val(),"versionNumber":$("#versionNumber").val()}

        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/app/examine/list?productId="+productId;
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }
    function  productChange() {
       selectChange();
       $("#app").val("");
    }
    function selectChange() {
        var productNow = $("#product").val()
        if (productNow == ''){
            $("#app option").each(function() {
                $(this).show();
            })
        } else{
            $("#app option").each(function() {
                var pid = $(this).attr("pid")
                if (pid != '') {
                    if (productNow != pid) {
                        $(this).hide();
                    } else {
                        $(this).show();
                    }
                }
            })
        }
    }
    function appChange() {
        var pid = $("#app option:selected").attr("pid")
        if (pid != '') {
            $("#product").val(pid)
        }
        selectChange();
    }
</script>
</html>