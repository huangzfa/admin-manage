<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui"/>
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
var selectOrganId="${selectOrganId}";
$(function(){
	$('#toUserForm').click(function(){
		if(window.parent.treeObj.getSelectedNodes().length>0){
			window.location="${ctxA}/sys/user/form?selectOrganId="+selectOrganId;
		}else{
			top.$.jBox.tip("请选择组织");
		}
	});
	
	var pageSize=${cfg:getPageSize()};
	var pageList=[pageSize,30,50];
	var pager=$('#tt').datagrid('getPager');
	pager.pagination({ 
	    pageSize: pageSize,//每页显示的记录条数，默认为10 
	    pageList: pageList,//可以设置每页记录条数的列表 
	    layout:['list','sep','first','prev','links','next','last','sep','manual'],
	    beforePageText: '',
	    afterPageText: ' 共 {pages} 页', 
	    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    onSelectPage:function(pageNumber, pageSize){
	    	pageSize=pageSize;
	        //alert("pageNumber:"+pageNumber+";pageSize:"+pageSize);
	    	getData(pageNumber,pageSize);
		}
	});
	$('.datagrid-pager .pagination-num').hide();
	//加载第一页数据
	getData(1,pageSize);
	
	function getData(pageNum, pageSize){
		$('#tt').datagrid('loading');
		hjnUtils.ajax({  
	        type:'post',      
	        url:'${ctxA}/sys/user/userList',  
	        data:'roleName='+$("#roleName").val()+'&selectOrganId='+selectOrganId+'&loginName='+$('#loginName').val()+'&realName='+$('#realName').val()+'&page='+pageNum+'&pagesize='+pageSize,
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
	
	$('#search').click(function(){
		getData(1,pageSize);
	});
});
function nameformater(value,row,index){
	var opStr='';
	if(row.profileTypeZd!='sa'&&row.profileTypeZd!='co'){
		opStr='<a class="si-title-a" href="${ctxA}/sys/user/form?selectOrganId='+selectOrganId+'&opId='+row.opId+'">'+value+'</a>';
	}else{
		opStr=value;
	}
	return opStr;
}
function stateformater(value,row,index){
	if(value=='10'){
		return "启用";
	}else if(value=='20'){
		return "停用";
	}
	return '未知';
}
function optionformater(value,row,index){
	var aln="${aLoginName}";
	var opStr='';
	<shiro:hasPermission name="sys:user:edit">
	if(aln!=row.loginName){
		<shiro:hasPermission name="sys:user:assignRoles">
		opStr+='<a class="si-option-a" href="${ctxA}/sys/user/toAssignRoles?selectOrganId='+selectOrganId+'&opId='+row.opId+'">分配角色</a>';
		</shiro:hasPermission>
		opStr+='<a class="si-option-a" href="${ctxA}/sys/user/form?selectOrganId='+selectOrganId+'&opId='+row.opId+'">修改</a>';
		opStr+='<a class="si-option-a" href="${ctxA}/sys/user/delete?selectOrganId='+selectOrganId+'&opId='+row.opId+'" onclick="return confirmx(\'确定要删除该账号吗？\', this.href)">删除</a>';
	}
	</shiro:hasPermission>
	return opStr;
}
</script>
</head>
<body>
<ul class="nav nav-tabs" style="margin-bottom:3px;">
  <li class="active"><a href="javascript:void(0);">账号列表</a></li>
  <shiro:hasPermission name="sys:user:edit">
  <li><a id="toUserForm" href="javascript:void(0);">添加账号</a></li>
  </shiro:hasPermission>
</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form" style="margin-right: 20px">
		<li>
			<label>账号：</label>
			<input id="loginName" placeholder="请输入账号" class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>姓名：</label>
			<input id="realName" placeholder="请输入姓名" class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>角色名称：</label>
			<input id="roleName" placeholder="请输入角色" class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
		<li class="clearfix"></li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false"/>
	<table id="tt" class="easyui-datagrid"
	        data-options="idField:'opId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">   
	    <thead>   
	        <tr>   
	            <th data-options="field:'loginName',width:180,align:'center',halign:'center',fixed:true,formatter:nameformater">账号</th>
	            <th data-options="field:'realName',width:120,align:'center',halign:'center',fixed:true">姓名</th>
	            <th data-options="field:'organName',width:180,align:'center',halign:'center',fixed:true">所属组织</th>
				<th data-options="field:'roleName',width:180,align:'center',halign:'center',fixed:true">所属角色</th>
				<th data-options="field:'operatorState',width:45,align:'center',halign:'center',fixed:true,formatter:stateformater">状态</th>
	            <th data-options="field:'loginIp',width:120,align:'center',halign:'center',fixed:true">最后登录IP</th>
	            <th data-options="field:'loginTime',width:160,align:'center',halign:'center',fixed:true">最后登录时间</th>
	            <th data-options="field:'option',width:180,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
	        </tr>
	    </thead>   
	</table>  
</div>
</body>
</html>