<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<title></title>
<sys:jscss jscss="jquery,webfont,bootstrap,si,css,easyui" />
<!--  -->
<style type="text/css">
</style>
<script type="text/javascript">
$(function(){
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
	        url:'${ctxA}/sys/role/roleList',  
	        data:'roleName='+$('#roleName').val()+'&page='+pageNum+'&pagesize='+pageSize,
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
		opStr='<a class="si-title-a" href="${ctxA}/sys/role/form?roleId='+row.roleId+'">'+value+'</a>';
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
	var opStr='';
	<shiro:hasPermission name="sys:role:edit">
		opStr+='<a class="si-option-a" href="${ctxA}/sys/role/form?roleId='+row.roleId+'">修改</a>';
		opStr+='<a class="si-option-a" href="${ctxA}/sys/role/delete?roleId='+row.roleId+'" onclick="return confirmx(\'确定要删除该角色吗？\', this.href)">删除</a>';
	</shiro:hasPermission>
	return opStr;
}
</script>
</head>
<body>
	<ul class="nav nav-tabs" style="margin-bottom:3px;">
		<li class="active"><a href="javascript:void(0);">角色列表</a></li>
		<shiro:hasPermission name="sys:role:edit">
		<li><a href="${ctxA}/sys/role/form">添加角色</a></li>
		</shiro:hasPermission>
	</ul>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>角色名称：</label>
			<input id="roleName" placeholder="请输入角色名称" class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li class="btns">
			<input id="search" class="btn btn-primary" type="submit" value="查询" />
		</li>
		<li class="clearfix"></li>
	</ul>
</div>
<div class="si-warp" style="top:95px;">
	<sys:message content="${message}" isShowBox="false" />
	<table id="tt" class="easyui-datagrid"
		data-options="idField:'roleId',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'roleName',width:250,align:'center',halign:'center',fixed:true,formatter:nameformater">角色名称</th>
				<th data-options="field:'roleState',width:45,align:'center',halign:'center',fixed:true,formatter:stateformater">状态</th>
				<th data-options="field:'remark',width:100,align:'center',halign:'center'">说明</th>
				<th data-options="field:'option',width:180,align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>