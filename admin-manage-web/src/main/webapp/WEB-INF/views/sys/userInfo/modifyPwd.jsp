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
	$("#oldPassword").focus();
	$("#inputForm").validate({
		rules: {
			oldPassword:{
				required:true,
				minlength:6,
				maxlength:32
			},
			newPassword:{
				required:true,
				minlength:6,
				maxlength:32
			},
			confirmNewPassword:{
				required:true,
				minlength:6,
				maxlength:32,
				equalTo:"#newPassword"
			}
		},
		messages: {
			oldPassword:{required : "必填信息"},
			newPassword:{required : "必填信息"},
			confirmNewPassword: {required : "必填信息",equalTo: "请输入与上面相同的密码"}
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
<!--  -->
<ul class="nav nav-tabs">
  <li class="active"><a href="javascript:void(0);">修改密码</a></li>
</ul>
<div class="si-warp">
	<br/>
	<form:form id="inputForm" modelAttribute="credential" action="${ctxA}/sys/user/modifyPwd" method="post" class="form-horizontal">
		<form:hidden path="opId"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">旧密码：</label>
			<div class="controls">
				<input id="oldPassword" name="oldPassword" type="password" value=""/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">新密码：</label>
			<div class="controls">
				<input id="newPassword" name="newPassword" type="password" value=""/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认新密码：</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value=""/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	    <div class="form-actions">
	    	<shiro:hasPermission name="sys:user:modifyPwd">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			</shiro:hasPermission>
		</div>
	</form:form>
</div>
</body>
</html>