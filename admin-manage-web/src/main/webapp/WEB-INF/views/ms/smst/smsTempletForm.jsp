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
            smsUserfulCode:{
              required:true
            },
            templetCode:{
              required:true
            },
            templetContent:{
              required:true
            },
            limitTime:{
              required:true
            },
            desc:{
              required:true
            }
          },
          messages: {
            platform:{required : "必填信息"},
            smsUserfulCode:{required : "必填信息"},
            templetCode:{required : "必填信息"},
            templetContent:{required : "必填信息"},
            limitTime:{required : "必填信息"},
            desc:{required : "必填信息"},
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
    <li><a href="${ctxA}/ms/smst/list?id=${object.id}">短信模板列表</a></li>
    <li class="active">
        <shiro:hasPermission name="ms:smst:edit">
            <a href="javascript:void(0);">${not empty object.id?'修改':'添加'}短信模板</a>
        </shiro:hasPermission>
        <shiro:lacksPermission name="ms:smst:edit">
            <a href="javascript:void(0);">查看短信模板</a>
        </shiro:lacksPermission>
    </li>
</ul>
<div class="si-warp">
    <br/>
    <sys:message content="${message}"/>
    <form:form id="inputForm" modelAttribute="smsTemplet" action="${ctxA}/ms/smst/save" method="post" class="form-horizontal">
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
            <label class="control-label">短信类型编码：</label>
            <div class="controls">
                <form:select path="smsUserfulCode" cssStyle="width:285px;">
                    <c:forEach items="${cfg:getDictList('smsUserfulCode')}" var="o">
                        <form:option value="${o.dicVal}">${o.dicCode}</form:option>
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">模板编码：</label>
            <div class="controls">
                <c:choose>
                    <c:when test="${smsTemplet.templetCode != null}">
                        <form:input path="templetCode" htmlEscape="false" maxlength="50" class="input-xlarge" readonly="true" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
                    </c:when>
                    <c:otherwise>
                        <form:input path="templetCode" htmlEscape="false" maxlength="50" class="input-xlarge" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
                    </c:otherwise>
                </c:choose>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">模板内容：</label>
            <div class="controls">
                <form:textarea path="templetContent" htmlEscape="false"  class="input-xxlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">模板替换参数：</label>
            <div class="controls">
                <form:input path="templetParams" htmlEscape="false" maxlength="50" class="input-xlarge"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">次数限制：</label>
            <div class="controls">
                <form:input path="limitTime" htmlEscape="false" maxlength="10" class="input-xlarge" onkeyup="this.value=this.value.replace(/\D/g, '')"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">模板用途说明：</label>
            <div class="controls">
                <form:input path="desc" htmlEscape="false" maxlength="50" class="input-xlarge"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <div class="form-actions">
            <shiro:hasPermission name="ms:smst:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/ms/smst/list?id=${object.id}'"/>
        </div>
    </form:form>


</div>
</body>
</html>