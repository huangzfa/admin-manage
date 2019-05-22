<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui,97Date"/>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/message/templet/list">短信模板列表</a></li>
    <li class="active">
        <shiro:hasPermission name="message:sms:edit">
            <a href="javascript:void(0);">${not empty channel.id?'修改':'添加'}渠道</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="templetForm" modelAttribute="push"   action="${ctxA}/message/templet/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty templet.id?templet.id:''}">
        <input type="hidden" name="appKey" value="${not empty templet.appKey?templet.appKey:''}">
        <div class="control-group">
            <label class="control-label">模板编号：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写编号" type="text" name="code" id="code" maxlength="32" value="${templet.code}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">模板内容：</label>
            <div class="controls">
                <input type="text" class="form-control valid" style="width: 40%" descripe="请填写模板内容" type="text" name="content" id="apiAccount" maxlength="200" value="${templet.content}"></input>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="templet:list:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/message/templet/list'"/>
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
        var form=$("#templetForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/message/templet/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>