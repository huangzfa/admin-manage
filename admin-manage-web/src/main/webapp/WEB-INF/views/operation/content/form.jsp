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
	$("#mainTitle").focus();
	$("#inputForm").validate({
		rules: {
			mainTitle:{
				required:true
			},
			subTitle:{
				required:true
			},
			url:{
				required:true
			},
			img:{
				required:true
			},
			contentTypeZd:{
				required:true
			}
		},
		messages: {
			mainTitle:{required : "必填信息"},
			subTitle:{required:"必填信息"},
			url:{required:"必填信息"},
			img:{required:"必填信息"},
			contentTypeZd:{required:"必填信息"}
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
	//从媒体库中选择
	$('#fileManager').resStore({
		mode: 'image',
		imageManagerUrl: '/a/plugin/fileManager/list?action=imageList',
		uploadUrl: '/a/plugin/fileManager/upload?action=uploadimage',
		acceptExtensions: 'png,jpg,jpeg,gif,bmp',
		fileMaxSize: 2048000,
		confirmSelect:function(url){
			$('#img').val(url);
			$('#thumbImg').attr('src',url);
		}
	});
	$('#clearArtImg').click(function(){
		$('#img').val("");
		$('#thumbImg').attr('src','');
	});
});
</script>
</head>
<body>
<ul class="nav nav-tabs">
  <li><a href="${ctxA}/operation/content/list">内容列表</a></li>
  <li class="active">
  	<shiro:hasPermission name="operation:content:edit">
  		<a href="javascript:void(0);">${not empty article.artId?'修改':'添加'}内容</a>
  	</shiro:hasPermission>
  	<shiro:lacksPermission name="operation:content:edit">
  		<a href="javascript:void(0);">查看内容</a>
  	</shiro:lacksPermission>
  </li>
</ul>
<div class="si-warp">
	<br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="content" action="${ctxA}/operation/content/save" method="post" class="form-horizontal">
		<form:hidden path="contentId"/>
		<div class="control-group">
			<label class="control-label">主标题：</label>
			<div class="controls">
				<form:input path="mainTitle" htmlEscape="false" class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">副标题：</label>
			<div class="controls">
				<form:input path="subTitle" htmlEscape="false" class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">h5页面URL：</label>
			<div class="controls">
                <form:input path="url" htmlEscape="false" placeholder="h5页面URL" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容类型：</label>
			<div class="controls">
                <form:select path="contentTypeZd" cssStyle="width:285px;" >
					<c:forEach items="${cfg:getDictList('contentType')}" var="o">
						<form:option value="${o.dicVal}">${o.dicCode}</form:option>
					</c:forEach>
				</form:select>
                <span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题图片：</label>
			<div class="controls">
				<form:hidden path="img" htmlEscape="false"/>
				<div class="thumbImgBox">
					<c:if test="${not empty content.img}">
					<img id="thumbImg" class="thumbImg" src="${content.img}"/>
					</c:if>
					<c:if test="${empty content.img}">
					<img id="thumbImg" class="thumbImg" />
					</c:if>
				</div>
				<input id="fileManager" class="btn" type="button" value="选择图片"/>
				<input id="clearArtImg" class="btn" type="button" value="清除图片"/>
			</div>
		</div>
		
	    <div class="form-actions">
	    	<shiro:hasPermission name="operation:content:edit">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/operation/content/list'"/>
		</div>
	</form:form>
</div>
</body>
</html>