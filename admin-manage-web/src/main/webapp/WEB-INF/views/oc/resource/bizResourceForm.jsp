<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,select2,validation,ajaxfileupload,ocupload"/>
    <!--  -->
    <style type="text/css">
        .upload_button{list-style:none}
        .controls input{
            width: 30%;
        }
    </style>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/oc/resource/list">运营资源列表</a></li>
    <li class="active">
        <shiro:hasPermission name="oc:resource:edit">
            <a href="javascript:void(0);">${not empty resource.id?'修改':'添加'}资源</a>
        </shiro:hasPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <form:form id="resourceForm" modelAttribute="resource"   action="${ctxA}/oc/resource/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty resource.id?resource.id:''}">
        <div class="control-group">
            <label class="control-label">资源名称：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写资源名称" type="text" name="name" id="name" maxlength="64" value="${resource.name}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">主类型：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写主类型" type="text" name="type" id="type" maxlength="64" value="${resource.type}" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">附类型：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写附类型" type="text" name="secType" id="secType" maxlength="64" value="${resource.secType}" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">描述：</label>
            <div class="controls">
                <textarea id="des" name="des" style="width: 30%">${resource.des}</textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">整型配置值：</label>
            <div class="controls">
                <input type="text" class="form-control" placeholder="整型配置值,非必填" type="text" name="intValue" id="intValue" value="${resource.intValue}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">长整型配置值：</label>
            <div class="controls">
                <input type="text" class="form-control" placeholder="长整型配置值,非必填" type="text" name="longValue" id="longValue" value="${resource.longValue}" onkeyup='this.value=this.value.replace(/[^0-9]/g,"")' />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">字符串配置值：</label>
            <div class="controls">
                <input type="text" class="form-control" placeholder="字符串配置值,非必填" type="text" name="stringValue" id="stringValue" maxlength="255" value="${resource.stringValue}" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">字符串1配置值：</label>
            <div class="controls">
                <input type="text" class="form-control" placeholder="字符串1配置值,非必填" type="text" name="stringValue1" id="stringValue1" maxlength="255" value="${resource.stringValue1}" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">字符串2配置值：</label>
            <div class="controls">
                <input type="text" class="form-control" placeholder="字符串2配置值,非必填" type="text" name="stringValue2" id="stringValue2" maxlength="255" value="${resource.stringValue2}" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">非整数数值配置值：</label>
            <div class="controls">
                <input type="text" class="form-control" placeholder="非整数数值配置值,非必填,小数后保留4位小数" type="text" name="decimalValue" id="decimalValue"  value="${resource.decimalValue}" onkeyup='this.value=this.value.toString().match(/^\d+(?:\.\d{0,4})?/)'/>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="oc:resource:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="javascript:history.back(-1)"/>
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
        var form=$("#resourceForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/oc/resource/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }
</script>
</html>