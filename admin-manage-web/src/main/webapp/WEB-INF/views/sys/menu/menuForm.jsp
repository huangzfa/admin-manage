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
	$("#menuName").focus();
	$("#inputForm").validate({
		rules: {
			menuName:{
				required:true
			},
			state:{
				required:true
			},
			menuSort:{
				required:true,
				digits:true
			}
		},
		messages: {
			menuName:{required : "必填信息"},
			state:{required:"必填信息"},
			menuSort:{required:"必填信息",digits:"只能输入整数"}
		},
		submitHandler: function(form){
			if($("#parentId").val() == ""){
				top.$.jBox.tip("请选择上级菜单");
				//top.layer.msg('请选择上级菜单', {icon: 8});
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
<ul class="nav nav-tabs" style="margin-bottom: 5px;">
  <li><a href="${ctxA}/sys/menu/list">菜单列表</a></li>
  <li class="active">
  	<shiro:hasPermission name="sys:menu:edit">
  		<a href="javascript:void(0);">${not empty menu.menuId?'修改':'添加'}菜单</a>
  	</shiro:hasPermission>
  	<shiro:lacksPermission name="sys:menu:edit">
  		<a href="javascript:void(0);">查看菜单</a>
  	</shiro:lacksPermission>
  </li>
</ul>
<div class="si-warp">
	<br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="menu" action="${ctxA}/sys/menu/save" method="post" class="form-horizontal">
		<form:hidden path="menuId"/>
		<div class="control-group">
			<label class="control-label">上级菜单：</label>
			<div class="controls">
				<sys:treeSelect id="parentId" name="parentId" value="${menu.parentId}" labelName="parentMenuName" labelValue="${menu.parent.menuName}"
					title="菜单" url="${ctxA}/sys/menu/treeData" extId="${menu.menuId}" cssStyle="width:223px;"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="menuName" htmlEscape="false" maxlength="50" class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
	        <label class="control-label">类型：</label>
	        <div class="controls">
	        	<form:select path="menuType" cssStyle="width:285px;" >
					<c:forEach items="${cfg:getDictList('menuType')}" var="o">
						<form:option value="${o.dicVal}">${o.dicCode}</form:option>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> 权限项：菜单页面中的操作项，如增删改查按钮、链接等</span>
	        </div>
	    </div>
	    <div class="control-group">
			<label class="control-label">权限标识：</label>
			<div class="controls">
				<form:input path="authTag" htmlEscape="false" class="input-xxlarge"/>
				<span class="help-inline">控制器中定义的权限标识，如：@RequiresPermissions("权限标识")</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">菜单标记：</label>
			<div class="controls">
				<form:input path="menuTag" htmlEscape="false" readonly="true" class="input-xlarge"/>
				<span class="help-inline">菜单的唯一标记,系统自动生成</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">链接：</label>
			<div class="controls">
				<form:textarea path="menuUrl" htmlEscape="false" class="input-xxlarge"/>
				<span class="help-inline">点击菜单跳转的页面url</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目标：</label>
			<div class="controls">
				<form:input path="menuOpenTarget" htmlEscape="false" maxlength="10" class="input-small"/>
				<span class="help-inline">链接地址打开的目标窗口</span>
			</div>
		</div>
	    <div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
                <form:radiobuttons path="state" items="${cfg:getDictList('state')}" itemLabel="dicCode" itemValue="dicVal" htmlEscape="false"/>
                <span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图标：</label>
			<div class="controls">
				<sys:iconSelect id="menuIcon" name="menuIcon" value="${menu.menuIcon}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="menuSort" htmlEscape="false" maxlength="50" class="input-small" autocomplete="off"/>
				<span class="help-inline"><font color="red">*</font>排列顺序，按升序</span>
			</div>
		</div>
		<div class="control-group">
	        <label class="control-label">打开方式：</label>
	        <div class="controls">
	        	<form:select path="menuOpenType" cssStyle="width:285px;">
					<c:forEach items="${cfg:getDictList('menuOpenType')}" var="o">
						<form:option value="${o.dicVal}">${o.dicCode}</form:option>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
	        </div>
	    </div>
		<div class="control-group">
	        <label class="control-label">所属系统：</label>
	        <div class="controls">
	        	<form:select path="systemType" cssStyle="width:285px;">
					<c:forEach items="${cfg:getDictList('systemType')}" var="o">
						<form:option value="${o.dicVal}">${o.dicCode}</form:option>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
	        </div>
	    </div>
	    <div class="form-actions">
	    	<shiro:hasPermission name="sys:menu:edit">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</div>
</body>
</html>