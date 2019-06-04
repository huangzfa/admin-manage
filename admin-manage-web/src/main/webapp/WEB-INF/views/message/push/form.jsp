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
    <li><a href="${ctxA}/message/push/list">推送渠道列表</a></li>
    <li class="active">
        <shiro:hasPermission name="message:push:edit">
            <a href="javascript:void(0);">${not empty channel.id?'修改':'添加'}渠道</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="pushForm" modelAttribute="push"   action="${ctxA}/message/push/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty push.id?push.id:''}">
        <div class="control-group">
            <label class="control-label">app key：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写appKey" type="text" name="appKey" id="appKey" maxlength="32" value="${push.appKey}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">账号：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写账号" type="text" name="apiAccount" id="apiAccount" maxlength="32" value="${push.apiAccount}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">密码：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写密码" type="text" name="apiPwd" id="apiPwd" maxlength="32" value="${push.apiPwd}"></input>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="message:push:edit">
                <a id="btnSubmit" class="btn btn-primary" onclick="save()" >保存</a>
            </shiro:hasPermission>
            &nbsp;<a id="btnCancel" class="btn" type="button" onclick="window.location.href='${ctxA}/message/push/list'">返回</a>
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
        var form=$("#pushForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/message/push/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>