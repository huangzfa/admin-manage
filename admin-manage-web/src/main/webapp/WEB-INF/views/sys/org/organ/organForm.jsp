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
	$("#organName").focus();
	$("#inputForm").validate({
		rules: {
			organName:{
				required:true
			},
			organCode:{
				required:true,
				remote:{
					type:"POST",
					url:"${ctxA}/sys/organ/checkOrganCode",
					data:{
						organId:function(){return $("#organId").val();},
						organCode:function(){return $("#organCode").val();}
					} 
				},
				alnum:true
			}
		},
		messages: {
			organName:{required : "必填信息"},
			organCode:{required : "必填信息",remote: "组织编码已存在"}
		},
		submitHandler: function(form){
			if($("#parentOrganId").val() == ""){
				top.$.jBox.tip("请选择上级组织");
				return;
			}
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
  <li><a href="${ctxA}/sys/organ/list?selectOrganId=${organ.selectOrganId}">组织列表</a></li>
  <li class="active">
  	<shiro:hasPermission name="sys:organ:edit">
  		<a href="javascript:void(0);">${not empty organ.organId?'修改':'添加'}组织</a>
  	</shiro:hasPermission>
  	<shiro:lacksPermission name="sys:organ:edit">
  		<a href="javascript:void(0);">查看组织</a>
  	</shiro:lacksPermission>
  </li>
</ul>
<div class="si-warp">
	<br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="organ" action="${ctxA}/sys/organ/save" method="post" class="form-horizontal">
		<form:hidden path="organId"/>
		<form:hidden path="parentOrganId"/>
		<form:hidden path="selectOrganId"/>
		<div class="control-group">
			<label class="control-label">上级组织：</label>
			<div class="controls">
				<form:input path="parent.organName" htmlEscape="false" maxlength="50" readonly="true" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">组织编码：</label>
			<div class="controls">
				<form:input path="organCode" htmlEscape="false" class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">组织名称：</label>
			<div class="controls">
				<form:input path="organName" htmlEscape="false" maxlength="50" class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
	        <label class="control-label">组织类型：</label>
	        <div class="controls">
	        	<form:select path="organTypeId" cssStyle="width:285px;">
					<c:forEach items="${organTypes}" var="o">
						<form:option value="${o.organTypeId}">${o.organTypeName}</form:option>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> 组织类型：根据组织规则而来${not empty organ.organId?'，修改操作不可以更改组织类型':''}</span>
	        </div>
	    </div>
	    <div class="form-actions">
	    	<shiro:hasPermission name="sys:organ:edit">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/sys/organ/list?selectOrganId=${organ.selectOrganId}'"/>
		</div>
	</form:form>
</div>
</body>
</html>