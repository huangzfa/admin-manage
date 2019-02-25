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
    <script type="text/javascript">
           $(function(){
             $("#inputForm").validate({
               rules: {
                 appId:{
                   required:true,
                 },
                 appSystemType:{
                   required:true
                 },
                 versionNumber:{
                   required:true
                 },
                 channelId:{
                   required:true
                 }
               },
               messages: {
                 appId:{required : "必填信息"},
                 appSystemType:{required : "必填信息"},
                 channelId:{required : "必填信息"},
                 versionNumber:{required : "必填信息"}
               },
               submitHandler: function(form){
                 loading('正在提交，请稍等...');
                 form.submit();
               },
               errorPlacement: function(error, element) {
                 if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                   error.appendTo(element.parent().parent());
                 } else {
                   error.insertAfter(element);
                 }
               }
             });
           });
    </script>
</head>
<style>
</style>

<body>
<ul class="nav nav-tabs">
    <li><a href="${ctxA}/operat/appexam/list?id=${object.id}">APP审核管理</a></li>
    <li class="active">
        <shiro:hasPermission name="operat:appexam:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}升级配置</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="operat:appexam:edit">
            <a href="javascript:void(0);">查看升级配置</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="appExamineVo" action="${ctxA}/operat/appexam/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <div class="control-group">
            <label class="control-label">应用：</label>
            <div class="controls">
                <form:select path="appId" cssStyle="width:285px;">
                    <c:forEach items="${appExamineVo.appList}" var="o">
                        <form:option value="${o.id}">${o.appName}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">所属系统：</label>
            <div class="controls">
                <form:select path="appSystemType" cssStyle="width:285px;">
                    <c:forEach items="${cfg:getDictList('appSystemType')}" var="o">
                        <form:option value="${o.dicVal}">${o.dicCode}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">渠道：</label>
            <div class="controls">
                <form:select path="channelId" cssStyle="width:285px;">
                    <c:forEach items="${appExamineVo.channelList}" var="o">
                        <form:option value="${o.id}">${o.name}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">版本号：</label>
            <div class="controls">
                <form:input path="versionNumber" htmlEscape="false" maxlength="50" class="input-xlarge" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="form-actions">
            <shiro:hasPermission name="operat:appexam:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="开启审核"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/operat/appexam/list?id=${object.id}'"/>
        </div>
    </form:form>


</div>
</body>
</html>