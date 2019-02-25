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
	        url:'${ctxA}/csc/customer/customerList',
	        data:{'userName':$('#userName').val(),'realName':$('#realName').val(),'page':pageNum,'pagesize':pageSize},
	        dataType:'json',  
	        success:function(data){
	        	$('#tt').datagrid('loaded');
	        	if(data.code==1){
	        		$('#tt').datagrid('loadData', data.list); 
	        	}else{
                    top.layer.alert(data.msg,{icon: 5});
	        	}
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	$('#tt').datagrid('loaded');
                top.layer.alert("查询失败",{icon: 5});
	        }
	    });
	}
	
	$('#search').click(function(){
		getData(1,pageSize);
	});
});

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
	<shiro:hasPermission name="csc:customer:view">
		opStr+='<a class="si-option-a" href="${ctxA}/csc/customer/form?id='+row.id+'">查看详情</a>';
	</shiro:hasPermission>
	return opStr;
}
</script>
</head>
<body>
<div class="breadcrumb form-search" style="margin-bottom:0;">
	<ul class="ul-form">
		<li>
			<label>用户账号：</label>
			<input id="userName" class="input-large" type="text" value="" maxlength="50" />
		</li>
		<li>
			<label>用户姓名：</label>
			<input id="realName"  class="input-large" type="text" value="" maxlength="50" />
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
		data-options="idField:'id',singleSelect:true,striped:true,fit:true,fitColumns:true,pagination:true">
		<thead>
			<tr>
				<th  data-options="field:'id',align:'center',halign:'center'">用户ID</th>
				<th  data-options="field:'userName',align:'center',halign:'center'">用户账号</th>
				<th data-options="field:'realName',align:'center',halign:'center'">用户姓名</th>
				<th  data-options="field:'lastLoginTime',align:'center',halign:'center'">最近登录时间</th>
				<th data-options="field:'lastLoginSys',align:'center',halign:'center'">客户端系统</th>
				<th data-options="field:'lastLoginEdition',align:'center',halign:'center'">客户端版本</th>
				<th data-options="field:'lastLoginModel',align:'center',halign:'center'">手机型号</th>
				<th data-options="field:'option',align:'left',halign:'center',fixed:true,formatter:optionformater">操作</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>