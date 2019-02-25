<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,validation,ueditor,97Date,layer,webuploader,resStore"/>
<!--  -->
<style type="text/css">
.thumbImgBox{
	position:relative;
	padding: 2px;
    border: 1px solid #E8E2D9;
    float: left;
    margin-right: 15px;
}
.thumbImg{
	width: 160px;
    height: 100px;
}
.btn {
    outline: 0 none !important;
}
</style>
<script type="text/javascript">
$(function(){
	$("#cityId").focus();
	$("#inputForm").validate({
		rules: {
			cityId:{
				required:true,
				digits:true
			},
			sort:{
				required:true,
				digits:true
			}
		},
		messages: {
			cityId:{required : "必填信息",digits:"只能输入数字"},
			sort:{required:"必填信息",digits:"只能输入数字"}
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
  <li><a href="${ctxA}/operation/searchCity/list">城市列表</a></li>
  <li class="active">
  	<shiro:hasPermission name="operation:searchCity:edit">
  		<a href="javascript:void(0);">${not empty article.artId?'修改':'添加'}城市</a>
  	</shiro:hasPermission>
  	<shiro:lacksPermission name="operation:searchCity:edit">
  		<a href="javascript:void(0);">查看城市</a>
  	</shiro:lacksPermission>
  </li>
</ul>
<div class="si-warp">
	<br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="searchCity" action="${ctxA}/operation/searchCity/save" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">城市编号：</label>
			<div class="controls">
				<form:input path="cityId" htmlEscape="false" maxlength="50" class="input-xlarge" autocomplete="off"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否热门：</label>
			<div class="controls">
				<form:checkbox path="isHot"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="50" class="input-small" autocomplete="off"/>
				<span class="help-inline"><font color="red">*</font>排列顺序，按升序</span>
			</div>
		</div>
		
	    <div class="form-actions">
	    	<shiro:hasPermission name="operation:searchCity:edit">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/operation/searchCity/list'"/>
		</div>
	</form:form>
</div>
</body>
</html>