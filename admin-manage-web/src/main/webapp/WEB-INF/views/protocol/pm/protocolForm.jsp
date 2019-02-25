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
            protocolName:{
              required:true,
            },
            protocolType:{
              required:true
            },
            protocolUrl:{
              required:true
            }
          },
          messages: {
            protocolName:{required : "必填信息"},
            protocolType:{required : "必填信息"},
            protocolUrl:{required : "必填信息"}
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
    <li><a href="${ctxA}/protocol/pm/list?id=${object.id}">协议列表</a></li>
    <li class="active">
        <shiro:hasPermission name="protocol:pm:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}协议</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="protocol:pm:edit">
            <a href="javascript:void(0);">查看协议</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="protocol" action="${ctxA}/protocol/pm/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <div class="control-group">
            <label class="control-label">协议名称：</label>
            <div class="controls">
                <form:input path="protocolName" htmlEscape="false" maxlength="20" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>


        <div class="control-group">
            <label class="control-label">协议类型：</label>
            <div class="controls">
                <form:select path="protocolType" cssStyle="width:285px;">
                    <c:forEach items="${cfg:getDictList('protocolType')}" var="o">
                        <form:option value="${o.dicVal}">${o.dicCode}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">协议Url：</label>
            <div class="controls">
                <form:input path="protocolUrl" htmlEscape="false" maxlength="200" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>



        <div class="form-actions">
            <shiro:hasPermission name="protocol:pm:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/protocol/pm/list?id=${object.id}'"/>
        </div>
    </form:form>


</div>
</body>
</html>