<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <title></title>
    <sys:jscss jscss="jquery1.11.3,webfont,bootstrap,si,css,easyui"/>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/message/sms/list">短信渠道列表</a></li>
    <li class="active">
        <shiro:hasPermission name="message:sms:edit">
            <a href="javascript:void(0);">${not empty channel.id?'修改':'添加'}渠道</a>
        </shiro:hasPermission>
    </li>
</ul>

<div class="si-warp">
    <br/>
    <form:form id="channelForm" modelAttribute="channel"   action="${ctxA}/message/sms/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${not empty channel.id?channel.id:''}">
        <input type="hidden" id="channelName" name="channelName" value="">
        <input type="hidden" id="state" name="state" value="1">
        <div class="control-group">
            <label class="control-label">选择应用：</label>
            <div class="controls">
                <select  id="appKey" name="appKey" class="selectpicker show-tick form-control" descripe="请选择应用">
                    <c:forEach items="${appList}" var="app">
                        <option value="${app.appKey}" ${channel.appKey == app.appKey?'checked':''}>${app.appName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">渠道名称：</label>
            <div class="controls">
                <select  id="channelCode" name="channelCode" class="selectpicker show-tick form-control" descripe="请选择渠道">
                    <option value="chuanglan" ${channel.channelCode == 'chuanglan'?'selected':''}>创蓝</option>
                    <option value="dahan" ${channel.channelCode == 'dahan'?'selected':''}>大汉</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">开通类别：</label>
            <div class="controls">
                <select  id="businessCode" name="businessCode" class="selectpicker show-tick form-control" descripe="请选择类别">
                    <option value="NORMAL" ${channel.businessCode == 'NORMAL'?'selected':''}>常规</option>
                    <option value="MARKETING" ${channel.businessCode == 'MARKETING'?'selected':''}>营销</option>
                    <option value="COLLECTION" ${channel.businessCode == 'COLLECTION'?'selected':''}>催收</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">请求地址：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写请求地址" type="text" name="apiUrl" id="apiUrl" maxlength="32" value="${channel.apiUrl}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">签名：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写账号" type="text" name="smsSign" id="smsSign" maxlength="32" value="${channel.smsSign}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">账号：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写账号" type="text" name="apiAccount" id="apiAccount" maxlength="32" value="${channel.apiAccount}"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">密码：</label>
            <div class="controls">
                <input type="text" class="form-control valid" descripe="请填写密码" type="text" name="apiPwd" id="apiPwd" maxlength="32" value="${channel.apiPwd}"></input>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="merchant:list:edit">
                <input id="btnSubmit" class="btn btn-primary" onclick="save()" value="保 存" style="width: 50px;"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/message/sms/list'"/>
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
        $("#channelName").val($("#channelCode :selected").text());
        $("#btnSubmit").attr("disabled",true);
        var form=$("#channelForm");
        var action = form[0].action;
        var data = form.serialize();
        jQuery.post(action,data, function(data) {
            $("#btnSubmit").attr("disabled",false);
            if (data.code ==1) {
                top.layer.alert("操作成功", {
                    icon: 6,
                    end: function(){
                        window.location.href="${ctxA}/message/sms/list";
                    }
                });
            } else {
                top.layer.alert(data.msg, {icon: 5});
            }
        }, "json");
    }

</script>
</html>