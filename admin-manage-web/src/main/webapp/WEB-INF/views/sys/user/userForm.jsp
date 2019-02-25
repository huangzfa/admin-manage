<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,validation"/>
<!--  -->
<style type="text/css">
	input[disabled], select[disabled], textarea[disabled], input[readonly], select[readonly], textarea[readonly]{
		background-color:#eee;
	}
</style>
<script type="text/javascript">
$(function(){
	$("#organName").focus();
	$("#inputForm").validate({
		rules: {
			loginName:{
				required:true,
				remote:{
					type:"POST",
					url:"${ctxA}/sys/user/checkLoginName",
					data:{
						opId:function(){return $("#opId").val();},
						loginName:function(){return $("#loginName").val();}
					} 
				},
				alnum:true
			},
			loginPwd:{
				required:true
			},
			operatorState:{
				required:true
			}
		},
		messages: {
			loginName:{required : "必填信息",remote: "用户名已存在"},
			loginPwd:{required : "必填信息"},
			operatorState:{required:"必填信息"}
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
  <li><a href="${ctxA}/sys/user/list?selectOrganId=${subject.selectOrganId}">用户列表</a></li>
  <li class="active">
  	<shiro:hasPermission name="sys:user:edit">
  		<a href="javascript:void(0);">${not empty subject.subjectId?'修改':'添加'}用户</a>
  	</shiro:hasPermission>
  	<shiro:lacksPermission name="sys:user:edit">
  		<a href="javascript:void(0);">查看用户</a>
  	</shiro:lacksPermission>
  </li>
</ul>
<div class="si-warp">
	<br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="operator" action="${ctxA}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="opId"/>
		<form:hidden path="organId"/>
		<form:hidden path="selectOrganId"/>
		<div class="control-group">
			<label class="control-label">所属组织：</label>
			<div class="controls">
				<form:input path="organName" htmlEscape="false" maxlength="50" readonly="true" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账号：</label>
			<div class="controls">
				<c:if test="${empty operator.opId}">
				<form:input path="loginName" htmlEscape="false" class="input-xlarge"/>
				</c:if>
				<c:if test="${not empty operator.opId}">
				<form:input path="loginName" htmlEscape="false" readonly="true" class="input-xlarge"/>
				</c:if>
				<span class="help-inline"><font color="red">*</font> 登录账号，只能包括英文字母和数字</span>
			</div>
		</div>

		<div class="control-group">
	        <label class="control-label">姓名：</label>
	        <div class="controls">
	        	<form:input path="realName" htmlEscape="false" maxlength="50" class="input-xlarge"/>
	        </div>
	    </div>
	    <div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
                <form:radiobuttons path="operatorState" items="${cfg:getDictList('state')}" itemLabel="dicCode" itemValue="dicVal" htmlEscape="false"/>
                <span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
	    <div class="form-actions">
	    	<shiro:hasPermission name="sys:user:edit">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/sys/user/list?selectOrganId=${subject.selectOrganId}'"/>
		</div>
	</form:form>
</div>
</body>
</html>