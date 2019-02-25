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
            platform:{
              required:true,
            },
            jpushChannelName:{
              required:true
            },
            jpushChannelCode:{
              required:true
            },
            appkey:{
              required:true
            },
            secret:{
              required:true
            }
          },
          messages: {
            platform:{required : "必填信息"},
            jpushChannelName:{required : "必填信息"},
            jpushChannelCode:{required : "必填信息"},
            appkey:{required : "必填信息"},
            secret:{required : "必填信息"}
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
    <li><a href="${ctxA}/ms/jc/list?id=${object.id}">推送渠道列表</a></li>
    <li class="active">
        <shiro:hasPermission name="ms:jc:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}推送渠道</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="ms:jc:edit">
            <a href="javascript:void(0);">查看推送渠道</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="jpushChannel" action="${ctxA}/ms/jc/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <div class="control-group">
            <label class="control-label">平台：</label>
            <div class="controls">
                <form:select path="platform" cssStyle="width:285px;">
                    <c:forEach items="${cfg:getDictList('platform')}" var="o">
                        <form:option value="${o.dicVal}">${o.dicCode}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">渠道名称：</label>
            <div class="controls">
                <form:input path="jpushChannelName" htmlEscape="false" maxlength="50" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">渠道编码：</label>
            <div class="controls">
                <c:choose>
                    <c:when test="${jpushChannel.jpushChannelCode != null}">
                        <form:input path="jpushChannelCode" htmlEscape="false" maxlength="50" class="input-xlarge" readonly="true" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
                    </c:when>
                    <c:otherwise>
                        <form:input path="jpushChannelCode" htmlEscape="false" maxlength="50" class="input-xlarge" readonly="false" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
                    </c:otherwise>
                </c:choose>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>
        <%--<div class="control-group">--%>
            <%--<label class="control-label">渠道编码：</label>--%>
            <%--<div class="controls">--%>
                <%--<form:input path="jpushChannelCode" htmlEscape="false" maxlength="50" class="input-xlarge" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>--%>
                <%--<span class="help-inline"><font color="red">*</font></span>--%>
            <%--</div>--%>
        <%--</div>--%>

        <div class="control-group">
            <label class="control-label">极光appkey：</label>
            <div class="controls">
                <form:input path="appkey" htmlEscape="false" maxlength="50" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">极光secret：</label>
            <div class="controls">
                <form:input path="secret" htmlEscape="false" maxlength="50" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="form-actions">
            <shiro:hasPermission name="ms:jc:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/ms/jc/list?id=${object.id}'"/>
        </div>
    </form:form>


</div>
</body>
</html>