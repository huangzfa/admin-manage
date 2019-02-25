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
			organTypeCode:{
				required:true,
				alnum:true
			},
			organTypeName:{
				required:true
			}
		},
		messages: {
			organTypeCode:{required : "必填信息"},
			organTypeName:{required:"必填信息"}
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
<body>
<ul class="nav nav-tabs">
  <li><a href="${ctxA}/sys/organType/list">组织类型列表</a></li>
  <li class="active">
  	<shiro:hasPermission name="sys:organType:edit">
  		<a href="javascript:void(0);">${not empty organType.organTypeId?'修改':'添加'}组织类型</a>
  	</shiro:hasPermission>
  	<shiro:lacksPermission name="sys:organType:edit">
  		<a href="javascript:void(0);">查看组织类型</a>
  	</shiro:lacksPermission>
  </li>
	
</ul>
<div class="si-warp">
	<br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="organType" action="${ctxA}/sys/organType/save" method="post" class="form-horizontal">
		<form:hidden path="organTypeId"/>
		<div class="control-group">
			<label class="control-label">组织类型名称：</label>
			<div class="controls">
				<form:input path="organTypeName" htmlEscape="false" maxlength="50" class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">组织类型代码：</label>
			<div class="controls">
				<form:input path="organTypeCode" htmlEscape="false" maxlength="50" class="input-xlarge" readonly="${organType.isSystem}"/>
				<span class="help-inline"><font color="red">*</font><c:if test="${organType.isSystem}">系统数据编码不能修改</c:if></span>
			</div>
		</div>
	    <div class="form-actions">
	    	<shiro:hasPermission name="sys:organType:edit">
	    	<c:if test="${!organType.isSystem}">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</c:if>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/sys/organType/list'"/>
		</div>
	</form:form>
</div>
</body>
</html>