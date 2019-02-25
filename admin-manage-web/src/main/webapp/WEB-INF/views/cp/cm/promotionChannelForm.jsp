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
            name:{
              required:true,
            },
            channelCode:{
              required:true
            },
            channelType:{
              required:true
            },
            styleId:{
              required:true
            },
            shortUrl:{
              required:true
            }
          },
          messages: {
            name:{required : "必填信息"},
            channelCode:{required : "必填信息"},
            channelType:{required : "必填信息"},
            styleId:{required : "必填信息"},
            shortUrl:{required : "必填信息"}
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
    <li><a href="${ctxA}/cp/cm/list?id=${object.id}">推广渠道列表</a></li>
    <li class="active">
        <shiro:hasPermission name="cp:cm:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}推广渠道</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="cp:cm:edit">
            <a href="javascript:void(0);">查看推广渠道</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="promotionChannelVo" action="${ctxA}/cp/cm/save" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <div class="control-group">
            <label class="control-label">渠道名称：</label>
            <div class="controls">
                <form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">渠道编码：</label>
            <div class="controls">
                <form:input path="channelCode" htmlEscape="false" maxlength="10" class="input-xlarge" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">渠道类型：</label>
            <div class="controls">
                <form:select path="channelType" cssStyle="width:285px;">
                    <c:forEach items="${cfg:getDictList('channelType')}" var="o">
                        <form:option value="${o.dicVal}">${o.dicCode}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">渠道样式：</label>
            <div class="controls">
                <form:select path="styleId" cssStyle="width:285px;">
                    <c:forEach items="${promotionChannelVo.channelStyleList}" var="o">
                        <form:option value="${o.id}">${o.name}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">H5页面链接：</label>
            <div class="controls">
                <form:input path="shortUrl" htmlEscape="false" maxlength="200" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>


        <div class="form-actions">
            <shiro:hasPermission name="cp:cm:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/cp/cm/list?id=${object.id}'"/>
        </div>
    </form:form>


</div>
</body>
</html>