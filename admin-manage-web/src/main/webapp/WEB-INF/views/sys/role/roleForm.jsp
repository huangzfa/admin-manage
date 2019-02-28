<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui,select2,validation,zTree"/>
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
$(function(){
	$("#inputForm").validate({
		rules: {
			roleName:{
				required:true,
				alnumAndCn:true
			},
			roleState:{
				required:true
			}
		},
		messages: {
			roleName:{required : "必填信息"},
			roleState:{required:"必填信息"}
		},
		submitHandler: function(form){
			var ids = [], nodes = tree.getCheckedNodes(true);
			for(var i=0; i<nodes.length; i++) {
				var nd=nodes[i];
				ids.push(nd.id);
			}
			$("#menuIds").val(ids);
            var ids = [];
            $("#inputForm input:checkbox").each(function(){
                if($(this).prop("checked")==true){
                    ids.push($(this).val())
				}
			})
			if( ids.length == 0){
                alert("请选择产品");
                return false;
			}
			$("#roleProductIds").val(ids.join(","));
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
	var setting = {
			check:{
				enable:true,
				nocheckInherit:true,
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			view:{
				selectedMulti:false
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			},
			callback:{
				beforeClick:function(id, node){
					tree.checkNode(node, !node.checked, true, true);
					return false;
				},
				beforeExpand:function(treeId, node){
					tree.expandNode(node, true, true, false, false);
					return false;
				}
			}};
	
	// 用户-菜单
	var zNodes=[
			<c:forEach items="${menuList}" var="menu">
			{id:"${menu.menuId}",pId:"${menu.parentId}",name:"${menu.parentId == 0?'权限列表':menu.menuName}",menuType:"${menu.menuType}",icon:"${menu.parentId == 0?'/static/img/icons/house.png':menu.menuType eq 'mo'?'/static/img/icons/menuOption.png':''}"},
            </c:forEach>
			];
	// 初始化树结构
	var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
	// 默认折叠全部节点
	//tree.expandAll(false);
	// 默认选择节点
	var ids = "${role.menuIds}".split(",");
	for(var i=0; i<ids.length; i++) {
		var node = tree.getNodeByParam("id", ids[i]);
		if(node){
			try{
				tree.checkNode(node, true, false);
				tree.expandNode(node, true, false, false, false);
			}catch(e){}
		}
	}
	var rootNode = tree.getNodeByParam("pId", 0);
	if(rootNode){
		if(rootNode){
			try{
				tree.expandNode(rootNode, true, false, false, false);
			}catch(e){}
		}
	}
});
</script>
</head>
<body>
<ul class="nav nav-tabs">
  <li><a href="${ctxA}/sys/role/list">角色列表</a></li>
  <li class="active">
  	<shiro:hasPermission name="sys:role:edit">
  		<a href="javascript:void(0);">${not empty role.roleId?'修改':'添加'}角色</a>
  	</shiro:hasPermission>
  	<shiro:lacksPermission name="sys:role:edit">
  		<a href="javascript:void(0);">查看角色</a>
  	</shiro:lacksPermission>
  </li>
</ul>
<div class="si-warp">
	<br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="role" action="${ctxA}/sys/role/save" method="post" class="form-horizontal">
		<form:hidden path="roleId"/>
		<input type="hidden" name="roleProductIds" id="roleProductIds">
		<div class="control-group">
			<label class="control-label">角色名称：</label>
			<div class="controls">
				<form:input path="roleName" htmlEscape="false" maxlength="50" class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
	    <div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
                <form:radiobuttons path="roleState" items="${cfg:getDictList('state')}" itemLabel="dicCode" itemValue="dicVal" htmlEscape="false"/>
                <span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品/数据权限：</label>
			<div class="controls">
				<div class="form-group">
					<c:forEach items="${roleData}" var="data">
						<label class="checkbox-inline">
							<input type="checkbox" checked="${data.checked}" value="${data.productId}" role_product_id="${data.id}" name="roleData">${data.productName}
						</label>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">角色授权:</label>
			<div class="controls">
				<div id="menuTree" class="ztree" style="margin-top:3px;float:left;"></div>
				<form:hidden path="menuIds"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="3" maxlength="1000" class="input-xxlarge "/>
			</div>
		</div>
	    <div class="form-actions">
	    	<shiro:hasPermission name="sys:role:edit">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/sys/role/list'"/>
		</div>
	</form:form>
</div>
</body>
</html>