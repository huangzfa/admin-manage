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
	var selectOrganId="${selectOrganId}";
	var opId="${opId}";
	
	$('#tt').datagrid({onLoadSuccess:function(data){
		if(data){
			$.each(data.rows, function(index, item){
				if(item.checked){
					$('#tt').datagrid('checkRow', index);
				}
			});
		}
	}});
	$('#tt').datagrid('loading');
	function getData(){
		hjnUtils.ajax({  
	        type:'post',      
	        url:'${ctxA}/sys/user/queryAssignRoles?opId='+opId+'&roleName='+$('#roleName').val(),  
	        dataType:'json',  
	        success:function(data){
	        	$('#tt').datagrid('loaded');
	        	if(data.code==1){
	        		$('#tt').datagrid('loadData', data.list); 
	        	}else{
	        		top.$.jBox.tip(data.msg);
	        	}
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	$('#tt').datagrid('loaded');
	        }
	    });
	}
	//初始化数据
	getData();
	$('#search').click(function(){
		getData();
	});
	$('#assignRoles').click(function(){
		loading('正在提交，请稍等...');
		$('#assignRoles').attr("disabled",true);
		var checkedItems = $('#tt').datagrid('getChecked');
		var roleIds = [];
		$.each(checkedItems, function(index, item){
			roleIds.push(item.roleId);
		});       
		var selectRoleIds=roleIds.join(",");
		//alert(">"+selectRoleIds+"<");
		hjnUtils.ajax({  
	        type:'post',      
	        url:'${ctxA}/sys/user/assignRoles?opId='+opId+'&selectRoleIds='+selectRoleIds,  
	        dataType:'json',  
	        success:function(data){
	        	$('#assignRoles').attr("disabled",false);
	        	top.$.jBox.tip(data.msg);
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	$('#assignRoles').attr("disabled",false);
	        	top.$.jBox.tip("分配角色失败!");
	        }
	    });
	});
});
</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
  <li><a href="${ctxA}/sys/user/list?selectOrganId=${selectOrganId}">用户列表</a></li>
  <li class="active">
  	<shiro:hasPermission name="sys:user:assignRoles">
  		<a href="javascript:void(0);">分配角色</a>
  	</shiro:hasPermission>
  	<shiro:lacksPermission name="sys:user:assignRoles">
  		<a href="javascript:void(0);">查看角色</a>
  	</shiro:lacksPermission>
  </li>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>账号：${operator.loginName}</li>
		<li style="margin-left: 50px;">姓名：${operator.realName}</li>
		<li style="margin-left: 50px;">所在组织：${operator.organName}</li>
	</ul>
	<ul class="ul-form">
		<li>
			<label>角色名称：</label>
			<input id="roleName" placeholder="请输入角色名称" class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="button" value="查询" />
			<shiro:hasPermission name="sys:user:assignRoles">
			<input id="assignRoles" class="btn btn-primary" type="button" value="保存角色分配"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctxA}/sys/user/list?selectOrganId=${subject.selectOrganId}'"/>
		</li>
		<li class="clearfix"></li>
	</ul>
</div>
<div class="si-warp" style="top:130px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		data-options="idField:'roleId',singleSelect:false,striped:true,fit:true,fitColumns:true,checkOnSelect:true,selectOnCheck:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true,width:80"></th>
				<th data-options="field:'roleName',width:180">角色名称</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>