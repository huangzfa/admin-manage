<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,validation"/>
<!--  -->
<style type="text/css">
.head_img{
	width: 115px;
	height: 115px;
	margin: 0 auto;
	border-radius:60px;
	border: 5px solid transparent;
	overflow: hidden;
	cursor: pointer;
}
</style>
<script type="text/javascript">
$(function(){
	
});
</script>
</head>
<body>
<!--  -->
<ul class="nav nav-tabs">
  <li class="active"><a href="javascript:void(0);">个人信息</a></li>
</ul>

<div class="si-warp">
	<br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="operator" action="${ctxA}/sys/user/modifyUserInfo" method="post" class="form-horizontal">
		<form:hidden path="opId"/>
		<div class="control-group">
			<label class="control-label">所属组织：</label>
			<div class="controls">
				<form:input path="organName" htmlEscape="false" maxlength="50" readonly="true" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
	        <label class="control-label">账号：</label>
	        <div class="controls">
	        	<form:input path="loginName" htmlEscape="false" maxlength="50" class="input-xlarge"/>
	        </div>
	    </div>
		<div class="control-group">
	        <label class="control-label">姓名：</label>
	        <div class="controls">
	        	<form:input path="realName" htmlEscape="false" maxlength="50" class="input-xlarge"/>
	        </div>
	    </div>
		<div class="control-group">
			<label class="control-label">上次登录：</label>
			<div class="controls">
				<label class="lbl">IP： ${operator.loginIp} &nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${operator.loginTime}" type="both" dateStyle="full"/></label>
			</div>
		</div>
	</form:form>
</div>
</body>
</html>